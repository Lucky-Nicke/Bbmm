package club.lanxige.bbmm.service.impl;

import club.lanxige.bbmm.dao.AccountRepository;
import club.lanxige.bbmm.dto.LoginRequest;
import club.lanxige.bbmm.dto.LoginResponse;
import club.lanxige.bbmm.entity.Account;
import club.lanxige.bbmm.service.LoginService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginServiceImpl implements LoginService {

    private final AccountRepository accountRepository;

    public LoginServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        // 查找用户
        Account account = accountRepository.findByUsername(request.getUsername())
                .orElse(null);

        // 用户不存在
        if (account == null) {
            return LoginResponse.builder()
                    .message("用户不存在")
                    .success(false)
                    .build();
        }

        // 密码错误
        if (!account.getPassword().equals(request.getPassword())) {
            return LoginResponse.builder()
                    .message("用户名或密码错误")
                    .success(false)
                    .build();
        }

        // 更新最后登录时间
        account.setLastLoginTime(LocalDateTime.now());
        accountRepository.save(account);

        // 根据角色生成重定向URL和角色标识
        String redirectUrl;
        String role;

        // 关键修改：将ADMIN改为admin
        if (account.getRole() == Account.Role.admin) {
            redirectUrl = "/bbmm/bbmm_html/admin/admin-frame.html";
            role = "ADMIN";
        } else {
            redirectUrl = "/bbmm/bbmm_html/user/user-frame.html";
            role = "USER";
        }

        return LoginResponse.builder()
                .message("登录成功")
                .success(true)
                .redirectUrl(redirectUrl)
                .role(role)
                .data(LoginResponse.UserInfo.builder()
                        .id(account.getId())
                        .username(account.getUsername())
                        .build())
                .build();
    }
}