package pl.isa.biblioteka;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
public class IndexController {

/*    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }*/
//    @GetMapping("/")
//    public ModelAndView index(Authentication authentication){
//        Optional<? extends GrantedAuthority> admin = authentication.getAuthorities().stream().filter(user -> user.getAuthority().equals("ROLE_ADMIN")).findFirst();
//        if (admin.isPresent()){
//            return new ModelAndView("administration");
//        }
//        return new ModelAndView("index");
//    }

//    @GetMapping("/")
//    String login() {
//        return "index";
//    }

    @GetMapping("/")
    public String customLogin(Principal principal, Model model) {
        String user = principal.getName();

        model.addAttribute("user", user);
        return "index";
    }



    @GetMapping("/administration")
    public ModelAndView administration(){
        return new ModelAndView("administration");
    }

    @GetMapping("/template")
    public ModelAndView template(){
        return new ModelAndView("template");
    }
}
