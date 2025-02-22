package maven.spring.com.models;

import javax.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "Пустое имя")
    @Size(min = 2, max = 100, message = "Слишком длинное/короткое имя, пределы [2-100]")
    private String name;

    @Min(value = 1900, message = "Год должен быть от 1900")
    private int year;

    public Person() {
    }

    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
