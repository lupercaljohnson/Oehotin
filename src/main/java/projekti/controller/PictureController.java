
package projekti.controller;
/**
 *
 * @author Teemu-Petteri Remes, teemu.petteri.remes@gmail.com
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projekti.service.JpegService;
import projekti.service.MessageService;



@Controller
public class PictureController {
    @Autowired 
    JpegService jpegService;
    
    @Autowired
    MessageService messageService;
    
    @GetMapping("/omasivu/{id}/picture")
    public String picture(Model model, @PathVariable Long id){
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("messages", this.messageService.list());
        model.addAttribute("message", username);
        model.addAttribute("picture", jpegService.getOne(id));
        return "picture";
    }        
    
    @PostMapping("/omasivu/{id}/picture")
    public String picture(@PathVariable Long id){
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        jpegService.delete(id, username);
        return "redirect:/omasivu";
    }    
}
