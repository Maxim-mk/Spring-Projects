package maven.spring.com.repositories;

import maven.spring.com.models.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    @NotNull
    Page<Book> findAll(@NotNull Pageable pageable);

    public List<Book> findByTitleStartingWith(@NotNull String title);

}
