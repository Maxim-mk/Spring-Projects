package maven.spring.com.services;

import maven.spring.com.models.Book;
import maven.spring.com.models.Person;
import maven.spring.com.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    public Page<Book> index(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book newBook) {
        Book bookToUpdate = findOne(id);
        newBook.setId(id);
        newBook.setOwner(bookToUpdate.getOwner());
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
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(null);
            book.setTakenAt(null);
        });
    }

    @Transactional
    public void assign(int id, Person newPerson) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(newPerson);
            book.setTakenAt(new Date());
        });
    }

    public List<Book> findBookByFirstLetters(String letters) {
        return booksRepository.findByTitleStartingWith(letters);
    }


}
