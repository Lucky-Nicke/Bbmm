package club.lanxige.bbmm.controller;

import club.lanxige.bbmm.dto.LayuiResponse;
import club.lanxige.bbmm.dto.ApiResponse;
import club.lanxige.bbmm.dto.UserDTO;
import club.lanxige.bbmm.service.UserService;
import club.lanxige.bbmm.vo.UserAddVO;
import club.lanxige.bbmm.vo.UserEditVO;
import club.lanxige.bbmm.vo.UserDeleteVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     */
    @GetMapping("/userList")
    public LayuiResponse<List<UserDTO>> getUserList() {
        return userService.getUserList();
    }

    /**
     * 添加用户
     */
    @PostMapping("/userAdd")
    public ApiResponse<Void> addUser(@RequestBody @Valid UserAddVO userAddVO) {
        return userService.addUser(userAddVO);
    }

    /**
     * 编辑用户
     */
    @PutMapping("/userEdit")
    public ApiResponse<Void> editUser(@RequestBody @Valid UserEditVO userEditVO) {
        return userService.editUser(userEditVO);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/userDel")
    public ApiResponse<Void> deleteUser(@RequestBody @Valid UserDeleteVO userDeleteVO) {
        return userService.deleteUser(userDeleteVO);
    }
}
