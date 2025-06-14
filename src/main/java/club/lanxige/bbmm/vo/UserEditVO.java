package club.lanxige.bbmm.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserEditVO {

    @NotNull(message = "用户ID不能为空")
    private Long id;

    private String username;

    private String password;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Pattern(regexp = "^\\d{17}[0-9Xx]$", message = "身份证号格式不正确")
    private String idCard;

    // 新增role字段
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRole() {
        return role;
    }
}
