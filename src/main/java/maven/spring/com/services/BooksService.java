package maven.spring.com.services;

import maven.spring.com.models.Book;
import maven.spring.com.models.Person;
import maven.spring.com.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> index() {
        return booksRepository.findAll();
    }

    public Book show(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book newBook) {
        newBook.setId(id);
        booksRepository.save(newBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.map(Book::getOwner);

    }

    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(book -> book.setOwner(null));
    }

    @Transactional
    public void assign(int id, Person newPerson) {
        booksRepository.findById(id).ifPresent(book -> book.setOwner(newPerson));
    }


}
