package by.shevko.spring.controllers;

import by.shevko.spring.dao.PersonDAO;
import by.shevko.spring.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model){
//        get all people and send to view
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
//        get a person and send to view
        model.addAttribute("person", personDAO.getById(id));
        System.out.println(personDAO.getById(id).toString());
        return "people/person";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
//        model.addAttribute("person", new Person());
        return "people/new_person";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people";
    }
}
