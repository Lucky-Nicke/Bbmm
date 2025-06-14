package club.lanxige.bbmm.service.impl;

import club.lanxige.bbmm.dao.AccountRepository;
import club.lanxige.bbmm.dto.ApiResponse;
import club.lanxige.bbmm.dto.LayuiResponse;
import club.lanxige.bbmm.dto.UserDTO;
import club.lanxige.bbmm.entity.Account;
import club.lanxige.bbmm.service.UserService;
import club.lanxige.bbmm.vo.UserAddVO;
import club.lanxige.bbmm.vo.UserEditVO;
import club.lanxige.bbmm.vo.UserDeleteVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public LayuiResponse<List<UserDTO>> getUserList() {
        List<Account> accountList = accountRepository.findAll();
        List<UserDTO> userDTOList = accountList.stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());

        // 明确指定泛型类型
        return LayuiResponse.<List<UserDTO>>success(userDTOList, accountList.size());
    }

    @Override
    @Transactional
    public ApiResponse<Void> addUser(UserAddVO userAddVO) {
        // 检查用户名是否重复
        if (accountRepository.existsByUsername(userAddVO.getUsername())) {
            return ApiResponse.error("用户名重复");
        }

        // 检查手机号是否重复
        if (accountRepository.existsByPhone(userAddVO.getPhone())) {
            return ApiResponse.error("手机号重复");
        }

        // 检查身份证号是否重复
        if (accountRepository.existsByIdCard(userAddVO.getIdCard())) {
            return ApiResponse.error("身份证重复");
        }

        Account account = new Account();
        BeanUtils.copyProperties(userAddVO, account);
        account.setIdCard(userAddVO.getIdCard());

        // 处理role字段，支持前端传递
        if (userAddVO.getRole() != null) {
            try {
                account.setRole(Account.Role.valueOf(userAddVO.getRole().toLowerCase()));
            } catch (IllegalArgumentException e) {
                // 无效角色时使用默认值user
                account.setRole(Account.Role.user);
            }
        } else {
            account.setRole(Account.Role.user);
        }

        account.setCreateTime(LocalDateTime.now());
        account.setUpdateTime(LocalDateTime.now());

        accountRepository.save(account);
        return ApiResponse.success("添加成功");
    }

    @Override
    @Transactional
    public ApiResponse<Void> editUser(UserEditVO userEditVO) {
        // 检查用户是否存在
        Optional<Account> optionalAccount = accountRepository.findById(userEditVO.getId());
        if (optionalAccount.isEmpty()) {
            return ApiResponse.error("没有此用户");
        }

        Account account = optionalAccount.get();

        // 检查用户名是否重复（排除自身）
        if (!account.getUsername().equals(userEditVO.getUsername()) &&
                accountRepository.existsByUsername(userEditVO.getUsername())) {
            return ApiResponse.error("用户名重复");
        }

        // 检查手机号是否重复（排除自身）
        if (!account.getPhone().equals(userEditVO.getPhone()) &&
                accountRepository.existsByPhone(userEditVO.getPhone())) {
            return ApiResponse.error("手机号重复");
        }

        // 检查身份证号是否重复（排除自身）
        if (!account.getIdCard().equals(userEditVO.getIdCard()) &&
                accountRepository.existsByIdCard(userEditVO.getIdCard())) {
            return ApiResponse.error("身份证重复");
        }

        BeanUtils.copyProperties(userEditVO, account);
        account.setIdCard(userEditVO.getIdCard());

        // 处理编辑时的role字段，使用小写user
        if (userEditVO.getRole() != null) {
            try {
                account.setRole(Account.Role.valueOf(userEditVO.getRole().toLowerCase()));
            } catch (IllegalArgumentException e) {
                // 无效角色时不修改
            }
        }

        account.setUpdateTime(LocalDateTime.now());

        accountRepository.save(account);
        return ApiResponse.success("编辑成功");

    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteUser(UserDeleteVO userDeleteVO) {
        // 检查用户是否存在
        if (!accountRepository.existsById(userDeleteVO.getId())) {
            return ApiResponse.error("没有此用户");
        }

        accountRepository.deleteById(userDeleteVO.getId());
        return ApiResponse.success("删除成功");
    }
}