
package projekti.controller;

/**
 *
 * @author Teemu-Petteri Remes, teemu.petteri.remes@gmail.com
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.service.AccountService;


@Controller
public class SearchController {
    
    @Autowired
    private AccountService accountService;
    
    @GetMapping("/search")
    private String searchUser (Model model, @RequestParam String search) {
            
        model.addAttribute("user", accountService.findByUserName(search));
        model.addAttribute("account", accountService.findByAccountName(search));
        model.addAttribute("users", accountService.list());

        return "search";
    }
}
