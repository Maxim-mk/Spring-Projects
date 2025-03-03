package maven.spring.com.services;

import maven.spring.com.models.Book;
import maven.spring.com.models.Person;
import maven.spring.com.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> index() {
        return peopleRepository.findAll();
    }

    public Person show(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person newPerson) {
        newPerson.setId(id);
        peopleRepository.save(newPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(int id) {
        Person person = peopleRepository.findById(id).orElse(null);
        assert person != null;
        Hibernate.initialize(person.getBooks());
        return person.getBooks();
    }

    public Optional<Person> getFullName(String fullName) {
        return peopleRepository.findByName(fullName);
    }


}
