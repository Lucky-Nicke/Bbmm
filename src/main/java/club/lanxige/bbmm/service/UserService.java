package club.lanxige.bbmm.service;

import club.lanxige.bbmm.dto.ApiResponse;
import club.lanxige.bbmm.dto.LayuiResponse;
import club.lanxige.bbmm.dto.UserDTO;
import club.lanxige.bbmm.vo.UserAddVO;
import club.lanxige.bbmm.vo.UserEditVO;
import club.lanxige.bbmm.vo.UserDeleteVO;

import java.util.List;

public interface UserService {

    /**
     * 获取用户列表
     */
    LayuiResponse<List<UserDTO>> getUserList();

    /**
     * 添加用户
     */
    ApiResponse<Void> addUser(UserAddVO userAddVO);

    /**
     * 编辑用户
     */
    ApiResponse<Void> editUser(UserEditVO userEditVO);

    /**
     * 删除用户
     */
    ApiResponse<Void> deleteUser(UserDeleteVO userDeleteVO);
}
