package club.lanxige.bbmm.service;

import club.lanxige.bbmm.dto.*;

import java.util.List;

public interface BookService {
    LayuiBookResponse<BookDTO> getBookList();
    ApiResponse<Object> addBook(BookForm bookForm);
    ApiResponse<Object> editBook(Long id, BookForm bookForm);
    ApiResponse<Object> deleteBook(BookDeleteForm deleteForm);
}
