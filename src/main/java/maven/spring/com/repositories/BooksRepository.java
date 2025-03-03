package maven.spring.com.repositories;

import maven.spring.com.models.Book;
import maven.spring.com.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

}
