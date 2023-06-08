package pl.isa.biblioteka.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Path;
import java.util.List;

@Controller
public class UserController {


    private final Users users;

    private final Person person;

    public UserController(Users users, Person person) {
        this.users = users;
        this.person = person;
    }



    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @PostMapping("/register")
    public String add(@RequestParam String login, @RequestParam String password, @RequestParam String firstName, @RequestParam String secondName, @RequestParam String email, Model model) {
        Person newPerson = new Person(login, password, firstName, secondName, email);
        users.addUser(newPerson);
        List<Person> persons = PersonService.readUsers();
        model.addAttribute("persons", persons);
        return "register";

/*        Tool newTool = new Tool(name, new Tool.ToolSize(size, unit), actions, Path.of("/img/noImg.png"));
        toolService.add(newTool);
        List<Tool> tools = toolService.getTools();
        model.addAttribute("tools", tools);*/

    }

}
