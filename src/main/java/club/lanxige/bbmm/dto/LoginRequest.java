package club.lanxige.bbmm.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    // 用户名（不能为空）
    @NotBlank(message = "用户名不能为空")
    private String username;

    // 密码（不能为空）
    @NotBlank(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}