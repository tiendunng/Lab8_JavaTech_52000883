package tdtu.edu.vn.Lab08.controller;

import org.apache.coyote.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String readInformation(@RequestParam(value = "name") String name,
                                  @RequestParam(value = "age") int age,
                                  @RequestParam(value = "gender") String gender,
                                  RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("name", name);
        redirectAttributes.addAttribute("age", age);
        redirectAttributes.addAttribute("gender", gender);

        return "redirect:/information";
    }

    @GetMapping("/information")
    public String showInformation(@RequestParam(value = "name") String name,
                                  @RequestParam(value = "age") int age,
                                  @RequestParam(value = "gender") String gender,
                                  Model model) {

        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("gender", gender);

        return "information";
    }

    @GetMapping("/about")
    @ResponseBody
    public String about() {
        return "About this site";
    }
}
