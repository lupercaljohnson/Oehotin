package projekti.controller;

/**
 *
 * @author Teemu-Petteri Remes, teemu.petteri.remes@gmail.com
 */
import projekti.service.AccountService;
import projekti.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.Message;

@Controller

public class MessageController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public String view(Model model) {
        model.addAttribute("messages", this.messageService.list());
        return "messages";

    }

    @PostMapping("/messages")
    public String add(@RequestParam String content, @RequestParam Long pictureid) {

        if (content != null && !content.trim().isEmpty()) {

            Message msg = new Message();

            msg.setContent(content.trim());

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            String username = auth.getName();

            msg.setUser(accountService.findByUserName(username));
            msg.setPictureId(pictureid);
            messageService.save(msg);

        }

        return "redirect:/omasivu";

    }

    @PostMapping("/messages/{id}")
    public String addToPicture(@RequestParam String content, @PathVariable Long id) {

        if (content != null && !content.trim().isEmpty()) {

            Message msg = new Message();

            msg.setContent(content.trim());

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            String username = auth.getName();

            msg.setUser(accountService.findByUserName(username));
            msg.setPictureId(id);
            messageService.save(msg);

        }

        return "redirect:/omasivu";

    }

    @PostMapping("{handle}/messages/{id}")
    public String addToPictureToisenSivu(@RequestParam String content, @PathVariable String handle, @PathVariable Long id) {

        if (content != null && !content.trim().isEmpty()) {

            Message msg = new Message();

            msg.setContent(content.trim());

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            msg.setUser(accountService.findByUserName(username));
            msg.setPictureId(id);
            messageService.save(msg);

        }
        String returnString = "redirect:/" + handle + "/toisensivu";
        return returnString;

    }
}
