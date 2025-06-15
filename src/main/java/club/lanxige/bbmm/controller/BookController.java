package club.lanxige.bbmm.controller;

import club.lanxige.bbmm.dto.*;
import club.lanxige.bbmm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/bookList")
    public LayuiBookResponse<BookDTO> getBookList() {
        return bookService.getBookList();
    }

    @PostMapping("/bookAdd")
    public ApiResponse<?> addBook(@RequestBody BookForm bookForm) {
        return bookService.addBook(bookForm);
    }

    @PutMapping("/bookEdit/{id}")
    public ApiResponse<?> editBook(@PathVariable Long id, @RequestBody BookForm bookForm) {
        return bookService.editBook(id, bookForm);
    }

    @DeleteMapping("/bookDel")
    public ApiResponse<?> deleteBook(@RequestBody BookDeleteForm deleteForm) {
        return bookService.deleteBook(deleteForm);
    }
}
