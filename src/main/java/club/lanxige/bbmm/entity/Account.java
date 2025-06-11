package club.lanxige.bbmm.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Entity
@Table(name = "account", indexes = {
        @Index(name = "idx_username", columnList = "username"),
        @Index(name = "idx_id_card", columnList = "id_card")
})
public class Account {

    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 用户名（唯一）
    @Column(nullable = false, unique = true)
    private String username;

    // 密码
    @Column(nullable = false)
    private String password;

    // 手机号
    @Column(nullable = false)
    private String phone;

    // 身份证号（唯一）
    @Column(nullable = false, unique = true)
    private String idCard;

    // 用户角色（admin/user）
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('admin', 'user') DEFAULT 'user'")
    private Role role;

    // 最后登录时间
    private LocalDateTime lastLoginTime;

    // 创建时间（自动生成）
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;

    // 更新时间（自动生成）
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateTime;

    public Account() {
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public enum Role {
        admin, user
    }
}