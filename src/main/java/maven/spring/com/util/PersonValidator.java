package maven.spring.com.util;


import maven.spring.com.models.Person;
import maven.spring.com.services.PeopleService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(@NotNull Object o, @NotNull Errors errors) {
        Person person = (Person) o;

        if (peopleService.getFullName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "Человек с таким ФИО уже есть");
        }

    }
}
