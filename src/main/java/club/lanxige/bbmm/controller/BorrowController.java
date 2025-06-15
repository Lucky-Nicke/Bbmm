package club.lanxige.bbmm.controller;

import club.lanxige.bbmm.entity.Borrow;
import club.lanxige.bbmm.entity.Book;
import club.lanxige.bbmm.service.BorrowService;
import club.lanxige.bbmm.dto.LayuiBorrowBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/borrowBook")
    public Map<String, Object> borrowBook(@RequestBody Borrow borrow) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            // 校验字段
            StringBuilder missingFields = new StringBuilder();
            if (borrow.getBookTitle() == null || borrow.getBookTitle().isEmpty()) {
                missingFields.append("book_title,");
            }
            if (borrow.getIsbn() == null || borrow.getIsbn().isEmpty()) {
                missingFields.append("isbn,");
            }
            if (borrow.getBorrower() == null || borrow.getBorrower().isEmpty()) {
                missingFields.append("borrower,");
            }
            if (borrow.getIdCard() == null || borrow.getIdCard().isEmpty()) {
                missingFields.append("id_card,");
            }
            if (borrow.getPhone() == null || borrow.getPhone().isEmpty()) {
                missingFields.append("phone,");
            }
            if (borrow.getBorrowTime() == null) {
                missingFields.append("borrow_time,");
            }
            if (borrow.getReturnTime() == null) {
                missingFields.append("return_time,");
            }

            if (missingFields.length() > 0) {
                result.put("message", "缺少必要字段: " + missingFields.substring(0, missingFields.length() - 1));
                result.put("success", false);
                return result;
            }

            borrowService.borrowBook(borrow);
            result.put("message", "借阅成功");
            result.put("success", true);
            result.put("data", new HashMap<>());
        } catch (Exception e) {
            result.put("message", "借阅失败: " + e.getMessage());
            result.put("success", false);
        }
        return result;
    }

    @GetMapping("/borrowListBook/{username}")
    public LayuiBorrowBookResponse listBorrowBooks(@PathVariable String username) {
        List<Borrow> borrowList = borrowService.getBorrowListByUsername(username);
        LayuiBorrowBookResponse response = new LayuiBorrowBookResponse();
        response.setCode(0);
        response.setMsg("");
        response.setCount(borrowList.size());
        response.setData(borrowList);
        return response;
    }

    @PostMapping("/returnBook")
    public Map<String, Object> returnBook(@RequestBody Map<String, Object> params) {
        Map<String, Object> result = new LinkedHashMap<>();  // 修改此处
        try {
            // 校验字段
            StringBuilder missingFields = new StringBuilder();
            if (params.get("id") == null) {
                missingFields.append("id,");
            }
            if (params.get("book_title") == null) {
                missingFields.append("book_title,");
            }
            if (params.get("isbn") == null) {
                missingFields.append("isbn,");
            }
            if (params.get("borrower") == null) {
                missingFields.append("borrower,");
            }
            if (params.get("id_card") == null) {
                missingFields.append("id_card,");
            }
            if (params.get("phone") == null) {
                missingFields.append("phone,");
            }

            if (missingFields.length() > 0) {
                result.put("message", "缺少必要字段: " + missingFields.substring(0, missingFields.length() - 1));
                result.put("success", false);
                return result;
            }

            Long id = Long.valueOf(params.get("id").toString());
            borrowService.returnBook(id);
            result.put("message", "归还成功");
            result.put("success", true);
            result.put("data", new HashMap<>());
        } catch (Exception e) {
            result.put("message", "归还失败: " + e.getMessage());
            result.put("success", false);
        }
        return result;
    }

    @GetMapping("/searchBook")
    public Map<String, Object> searchBook(@RequestParam String isbn) {
        Map<String, Object> result = new LinkedHashMap<>();  // 修改此处
        try {
            Book book = borrowService.searchBookByIsbn(isbn);
            if (book != null) {
                result.put("message", "查询成功");
                result.put("success", true);
                result.put("data", List.of(book));
            } else {
                result.put("message", "未找到图书");
                result.put("success", false);
                result.put("data", List.of());
            }
        } catch (Exception e) {
            result.put("message", "查询失败: " + e.getMessage());
            result.put("success", false);
        }
        return result;
    }
}