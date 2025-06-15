package club.lanxige.bbmm.dao;

import club.lanxige.bbmm.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    // 根据借阅人查询所有借阅记录
    List<Borrow> findByBorrower(String borrower);

    // 根据ID删除借阅记录
    void deleteById(Long id);

    // 检查借阅记录是否存在
    boolean existsById(Long id);
}