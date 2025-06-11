package club.lanxige.bbmm.dao;

import club.lanxige.bbmm.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // 根据用户名查找账户
    Optional<Account> findByUsername(String username);

    // 检查用户名是否已存在
    boolean existsByUsername(String username);

    // 检查身份证号是否已存在
    boolean existsByIdCard(String idCard);
}
