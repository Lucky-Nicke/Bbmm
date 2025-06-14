package club.lanxige.bbmm.dao;

import club.lanxige.bbmm.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByIdCard(String idCard);
    boolean existsByPhone(String phone); // 新增方法
}