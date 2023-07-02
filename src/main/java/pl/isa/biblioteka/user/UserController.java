package pl.isa.biblioteka.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.isa.biblioteka.book.BookService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static pl.isa.biblioteka.user.PersonService.editUserId;


@Controller
public class UserController {

    private final BookService bookService;
    private final PersonService personService;

    public UserController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        personService.deleteById(id);
        return "redirect:/usersList";
    }

    @GetMapping("/usersList")
    public String getUsers(Principal principal, Model model) {
        List<Person> users = personService.getAllPerson();
        Collections.sort(users, Comparator.comparing(Person::getId));
        model.addAttribute("users", users);
        if (principal != null) {
            String user = principal.getName();
            model.addAttribute("user", user);
            return "usersList";
        } else return "usersList";
    }

    @GetMapping("/myBooks")
    public String myBooks(Principal principal, Model model) {
        List<Person> users = personService.getAllPerson();
        model.addAttribute("users", users);
        if (principal != null) {
            String user = principal.getName();
            model.addAttribute("user", user);
            return "myBooks";
        } else return "myBooks";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@RequestParam String login, @RequestParam String password, @RequestParam String firstName, @RequestParam String secondName, @RequestParam String email, Model model) {
        Person newPerson = new Person(login, password, firstName, secondName, email);
        String result = personService.registerUserId(newPerson);
        model.addAttribute("mesage", result);
        PersonService.saveUsers();
        return "register";
    }

    @GetMapping("/edit/{id}")
    public String edit(Principal principal, @PathVariable("id") Integer id, Model model) {
        PersonDTO personDTO = personService.findById(id);
        model.addAttribute("personDTO", personDTO);
        if (principal != null) {
            String user = principal.getName();
            model.addAttribute("user", user);
            return "edit";
        } else return "edit";
    }

    @PostMapping("/update")
    public String editUser(@ModelAttribute PersonDTO personDTO) {
        personService.updateUser(personDTO);
        return "redirect:/usersList";
    }



}