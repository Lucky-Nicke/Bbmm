package club.lanxige.bbmm.controller;

import club.lanxige.bbmm.entity.Borrow;
import club.lanxige.bbmm.service.BorrowService;
import club.lanxige.bbmm.dto.LayuiBorrowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminBorrowController {

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/borrowList")
    public LayuiBorrowResult<List<Borrow>> getBorrowList() {
        List<Borrow> borrowList = borrowService.getAllBorrowRecords();
        return LayuiBorrowResult.success(borrowList);
    }
}