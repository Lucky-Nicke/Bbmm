package club.lanxige.bbmm.service;

import club.lanxige.bbmm.entity.Borrow;
import club.lanxige.bbmm.entity.Book;

import java.util.List;

public interface BorrowService {
    void borrowBook(Borrow borrow);
    List<Borrow> getBorrowListByUsername(String username);
    void returnBook(Long id);
    Book searchBookByIsbn(String isbn);

    List<Borrow> getAllBorrowRecords();
}