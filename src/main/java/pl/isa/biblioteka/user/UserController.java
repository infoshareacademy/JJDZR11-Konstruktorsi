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

//import static pl.isa.biblioteka.user.PersonService.editUserId;
//import static pl.isa.biblioteka.user.PersonService.registerUserId;


@Controller
public class UserController {


    private final BookService bookService;
    private final PersonService personService;
    private final PersonDAO personDAO;

    public UserController(BookService bookService, PersonService personService, PersonDAO personDAO) {
        this.bookService = bookService;
        this.personService = personService;
        this.personDAO = personDAO;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        personDAO.delate(id);
        return "redirect:/usersList";
    }

    @GetMapping("/usersList")
    public String getUsers(Principal principal, Model model) {
        List<User> users = personDAO.findAll();
        Collections.sort(users, Comparator.comparing(User::getId));
        model.addAttribute("users", users);
        if (principal != null) {
            String user = principal.getName();
            model.addAttribute("user", user);
            return "usersList";
        } else return "usersList";
    }

    @GetMapping("/myBooks")
    public String myBooks(Principal principal, Model model) {
        List<User> users = PersonService.readUsers();
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
        User newUser = new User(login, password, firstName, secondName, email);
        String result = personService.registerUserId(newUser);
        model.addAttribute("mesage", result);
        PersonService.saveUsers();
        return "register";
    }

    @GetMapping("/edit/{id}")
    public String edit(Principal principal, @PathVariable("id") Integer id, Model model) {
        User userDTO = personDAO.findById(id);
        model.addAttribute("personDTO", userDTO);
        if (principal != null) {
            String user = principal.getName();
            model.addAttribute("user", user);
            return "edit";
        } else return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, @ModelAttribute PersonDTO personDTO) {
        User existUser = personDAO.findById(id);
        if (existUser != null) {
            existUser.setUsername(personDTO.getUsername());
            existUser.setPassword(personDTO.getPassword());
            existUser.setFirstName(personDTO.getFirstName());
            existUser.setSecondName(personDTO.getSecondName());
            existUser.setEmail(personDTO.getEmail());
            personDAO.editUserId(existUser);
        }
        return "redirect:/usersList";
    }
}