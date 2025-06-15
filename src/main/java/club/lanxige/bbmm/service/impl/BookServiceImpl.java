package club.lanxige.bbmm.service.impl;

import club.lanxige.bbmm.dto.*;
import club.lanxige.bbmm.entity.Book;
import club.lanxige.bbmm.dao.BookRepository;
import club.lanxige.bbmm.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public LayuiBookResponse<BookDTO> getBookList() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOs = new ArrayList<>();

        for (Book book : books) {
            BookDTO bookDTO = new BookDTO();
            BeanUtils.copyProperties(book, bookDTO);
            bookDTOs.add(bookDTO);
        }

        return LayuiBookResponse.success(bookDTOs, bookDTOs.size());
    }

    @Override
    public ApiResponse<Object> addBook(BookForm bookForm) {
        // 检查图书名是否已存在
        if (bookRepository.existsByTitle(bookForm.getTitle())) {
            return ApiResponse.error("图书名重复");
        }

        // 新增：检查ISBN是否已存在
        if (bookRepository.existsByIsbn(bookForm.getIsbn())) {
            return ApiResponse.error("ISBN已存在");
        }

        Book book = new Book();
        BeanUtils.copyProperties(bookForm, book);
        book.setCreateTime(LocalDateTime.now());
        book.setUpdateTime(LocalDateTime.now());

        bookRepository.save(book);
        return ApiResponse.success("添加成功");
    }

    @Override
    public ApiResponse<Object> editBook(Long id, BookForm bookForm) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            return ApiResponse.error("没有此图书");
        }

        Book book = optionalBook.get();

        // 检查图书名是否已存在（排除当前图书）
        if (bookRepository.existsByTitleAndIdNot(bookForm.getTitle(), id)) {
            return ApiResponse.error("图书名重复");
        }

        // 新增：检查ISBN是否已被其他图书使用
        if (bookRepository.existsByIsbnAndIdNot(bookForm.getIsbn(), id)) {
            return ApiResponse.error("ISBN已被其他图书使用");
        }

        BeanUtils.copyProperties(bookForm, book);
        book.setUpdateTime(LocalDateTime.now());

        bookRepository.save(book);
        return ApiResponse.success("编辑成功");
    }

    @Override
    public ApiResponse<Object> deleteBook(BookDeleteForm deleteForm) {
        Long id = deleteForm.getId();
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            return ApiResponse.error("没有此图书");
        }

        bookRepository.deleteById(id);
        return ApiResponse.success("删除成功");
    }
}