package pl.isa.biblioteka;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.isa.biblioteka.book.ValueModel;

@Controller
public class IndexController {


    @GetMapping("/")
    String index(Model model, ValueModel valueModel){
        model.addAttribute("value", valueModel);
        return "index";
    }

    @GetMapping("/template")
    public ModelAndView template(){
        return new ModelAndView("template");
    }
}
