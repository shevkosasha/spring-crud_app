package by.shevko.spring.dao;

import by.shevko.spring.models.Person;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO {

  private static int people_count = 0;
  private List<Person> people;

  {
    people = new ArrayList<>();
    people.add(new Person(++people_count, "Tom"));
    people.add(new Person(++people_count, "Lex"));
    people.add(new Person(++people_count, "John"));
  }

  public List<Person> index() {
    return people;
  }

  public Person getById(int id) {
    return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
  }

  public void save(Person person) {
    person.setId(++people_count);
    people.add(person);
  }

  public void update(int id, Person person) {
    Person personToBeUpdated = getById(id);
    personToBeUpdated.setName(person.getName());
  }

  public void delete(int id) {
    people.removeIf(p -> p.getId() == id);
    System.out.println("people after delete: " + people);
  }
}
