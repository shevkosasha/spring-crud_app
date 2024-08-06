package by.shevko.spring.dao;

import by.shevko.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int people_count = 0;
    private List<Person> people;

    {
        people = List.of(
                new Person(++people_count, "Tom"),
                new Person(++people_count, "Lex"),
                new Person(++people_count, "John"),
                new Person(++people_count, "Dave")
        );
    }

    public List<Person> index() {
        return people;
    }

    public Person getById(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}
