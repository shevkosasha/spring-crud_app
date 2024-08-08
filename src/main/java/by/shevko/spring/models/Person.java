package by.shevko.spring.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {

  private int id;

  @NotEmpty(message = "name should not be empty")
  @Size(min = 2, max = 30, message = "name size should be between 2...30 characters")
  private String name;

  @Min(value = 0, message = "age should be greater than 0")
  private int age;

  @Email(message = "email should be valid")
  @NotEmpty(message = "email should not be empty")
  private String email;

  public Person() {}

  public Person(int id, String name, int age, String email) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.email = email;
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

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  @Min(value = 0, message = "age should be greater than 0")
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Person{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", age="
        + age
        + ", email='"
        + email
        + '\''
        + '}';
  }
}
