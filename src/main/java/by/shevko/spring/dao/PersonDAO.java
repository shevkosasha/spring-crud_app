package by.shevko.spring.dao;

import by.shevko.spring.models.Person;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO {

  private static int people_count = 0;
  private List<Person> personList;

  private static final String URL = "jdbc:mysql://localhost:3306/spring";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "admin";

  private static Connection connection;

  static {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
    } catch (ClassNotFoundException | NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public List<Person> index() {
    List<Person> people = new ArrayList<>();

    try {
      Statement statement = connection.createStatement();
      String SQL = "SELECT * FROM Person";
      ResultSet resultSet = statement.executeQuery(SQL);

      while(resultSet.next()) {
        Person person = new Person();

        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setEmail(resultSet.getString("email"));
        person.setAge(resultSet.getInt("age"));

        people.add(person);
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    personList = people;
    people_count = personList.size();
    return people;
  }

  public Person getById(int id) {
//    return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    return null;
  }

  public void save(Person person) {
//    person.setId(++people_count);
//    people.add(person);
    try {
      Statement statement = connection.createStatement();
      String SQL = "INSERT INTO Person VALUES(" + ++people_count + ",'" + person.getName() +
              "'," + person.getAge() + ",'" + person.getEmail() + "')";

      statement.executeUpdate(SQL);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public void update(int id, Person person) {
    Person personToBeUpdated = getById(id);

    personToBeUpdated.setName(person.getName());
    personToBeUpdated.setAge(person.getAge());
    personToBeUpdated.setEmail(person.getEmail());
  }

  public void delete(int id) {
//    people.removeIf(p -> p.getId() == id);
//    System.out.println("people after delete: " + people);
  }
}
