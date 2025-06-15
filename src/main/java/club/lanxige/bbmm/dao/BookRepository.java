package club.lanxige.bbmm.dao;

import club.lanxige.bbmm.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findById(Long id);
    List<Book> findAll();
    boolean existsByTitle(String title);
    boolean existsByTitleAndIdNot(String title, Long id);

    // 添加根据ISBN查询图书的方法
    Optional<Book> findByIsbn(String isbn);

    // 检查ISBN是否存在
    boolean existsByIsbn(String isbn);

    // 检查ISBN是否被其他图书使用
    boolean existsByIsbnAndIdNot(String isbn, Long id);
}