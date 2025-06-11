package club.lanxige.bbmm.service.impl;

import club.lanxige.bbmm.dto.RegisterRequest;
import club.lanxige.bbmm.dto.ApiResponse;
import club.lanxige.bbmm.entity.Account;
import club.lanxige.bbmm.dao.AccountRepository;
import club.lanxige.bbmm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Pattern CHINESE_NAME_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5]{2,10}$");

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ApiResponse<?> register(RegisterRequest request) {
        // 1. 检查用户名是否为中文
        if (!isChineseName(request.getUsername())) {
            return ApiResponse.error("用户名必须与身份证姓名一致");
        }

        // 2. 检查用户名是否已存在
        if (accountRepository.existsByUsername(request.getUsername())) {
            return ApiResponse.error("用户名已存在");
        }

        // 3. 检查身份证号是否已存在
        if (accountRepository.existsByIdCard(request.getIdCard())) {
            return ApiResponse.error("该身份证号已注册");
        }

        // 4. 创建新账户（明文密码）
        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(request.getPassword()); // 明文存储密码
        account.setPhone(request.getPhone());
        account.setIdCard(request.getIdCard());
        account.setRole(Account.Role.user); // 默认角色为普通用户

        // 5. 保存账户信息
        accountRepository.save(account);

        return ApiResponse.success("注册成功");
    }

    // 判断是否为中文姓名
    private boolean isChineseName(String name) {
        return CHINESE_NAME_PATTERN.matcher(name).matches();
    }
}
