package club.lanxige.bbmm.service.impl;

import club.lanxige.bbmm.entity.Borrow;
import club.lanxige.bbmm.entity.Book;
import club.lanxige.bbmm.dao.BorrowRepository;
import club.lanxige.bbmm.dao.BookRepository;
import club.lanxige.bbmm.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Borrow> getAllBorrowRecords() {
        return borrowRepository.findAll();
    }

    @Override
    @Transactional
    public void borrowBook(Borrow borrow) {
        // 检查图书是否存在
        Optional<Book> bookOptional = bookRepository.findByIsbn(borrow.getIsbn());
        if (bookOptional.isEmpty()) {
            throw new RuntimeException("图书不存在");
        }
        // 检查图书是否可借（此处可添加库存逻辑）
        borrowRepository.save(borrow);
    }

    @Override
    public List<Borrow> getBorrowListByUsername(String username) {
        return borrowRepository.findByBorrower(username);
    }

    @Override
    @Transactional
    public void returnBook(Long id) {
        // 检查借阅记录是否存在
        Optional<Borrow> borrowOptional = borrowRepository.findById(id);
        if (borrowOptional.isEmpty()) {
            throw new RuntimeException("借阅记录不存在");
        }
        // 更新图书库存（此处可添加库存逻辑）
        borrowRepository.deleteById(id);
    }

    @Override
    public Book searchBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("图书不存在"));
    }
}