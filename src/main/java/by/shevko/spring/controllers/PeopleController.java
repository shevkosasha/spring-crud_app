package by.shevko.spring.controllers;

import by.shevko.spring.dao.PersonDAO;
import by.shevko.spring.models.Person;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

  private final PersonDAO personDAO;

  public PeopleController(PersonDAO personDAO) {
    this.personDAO = personDAO;
  }

  @GetMapping
  public String index(Model model) {
    //        get all people and send to view
    model.addAttribute("people", personDAO.index());
    return "people/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable("id") int id, Model model) {
    //        get a person and send to view
    model.addAttribute("person", personDAO.getById(id));
    System.out.println(personDAO.getById(id).toString());
    return "people/person";
  }

  @GetMapping("/new")
  public String newPerson(@ModelAttribute("person") Person person) {
    //        model.addAttribute("person", new Person());
    return "people/new";
  }

  @GetMapping("/{id}/edit")
  public String edit(Model model, @PathVariable("id") int id) {
    model.addAttribute("person", personDAO.getById(id));

    return "people/edit";
  }

  @PostMapping()
  public String create(
      @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      System.out.println("there are some errors:\n" + bindingResult.getAllErrors());
      return "people/new";
    }
    System.out.println("everything is ok: " + person.toString());
    personDAO.save(person);
    return "redirect:/people";
  }

  @PatchMapping("/{id}")
  public String update(
      @ModelAttribute("person") @Valid Person person,
      BindingResult bindingResult,
      @PathVariable("id") int id) {

    if (bindingResult.hasErrors()) {
      return "people/edit";
    }

    personDAO.update(id, person);
    return "redirect:/people";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    personDAO.delete(id);
    return "redirect:/people";
  }
}
