package projekti;

/**
 *
 * @author Teemu-Petteri Remes, teemu.petteri.remes@gmail.com
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("*")
    public String helloWorld(Model model) {

        return "redirect:/omasivu";
    }

    @GetMapping("/contact")
    public String contaktos(){
        return "contact";
    }
}
