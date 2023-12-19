package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
