package projekti.controller;

/**
 *
 * @author Teemu-Petteri Remes, teemu.petteri.remes@gmail.com
 */
import projekti.service.AccountService;
import projekti.service.JpegService;
import projekti.service.MessageService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import projekti.Account;
import projekti.JpegObject;

@Controller
public class OmaSivuController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private JpegService jpegService;

    @GetMapping("/omasivu")
    public String list(Model model) {
        model.addAttribute("message", "Edes jotain");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
      
        String omasivu = "redirect:/" + username + "/omasivu/1";

        return omasivu;
    }

    @GetMapping(value = "{handle}/omasivu/{id}")
    public String omaSivuView(Model model, @PathVariable String handle, @PathVariable Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        List<JpegObject> kuvaLista = new ArrayList<JpegObject>(10);
        kuvaLista = jpegService.findByOwner(username);
        Long pictureId = (Long) id;

        int imageCount = kuvaLista.size();
        Account oma = accountService.findByUserName(username);

        List<Account> seurattavat = oma.getSeuraajat();

        model.addAttribute("seurattavat", seurattavat);
        model.addAttribute("accounts", accountService.list());
        model.addAttribute("count", imageCount);
        model.addAttribute("message", username);
        model.addAttribute("handle", username);
        
        model.addAttribute("messages", this.messageService.list());
        model.addAttribute("pictures", kuvaLista);
        model.addAttribute("profilepic", (accountService.findByUserName(username)).getProfilePictureId());
        return "omasivu";
    }

   
    @PostMapping("{handle}/omasivu")
    public String addOmaPicture(@RequestParam("file") MultipartFile file, @PathVariable String handle, @RequestParam String story) throws IOException {
        if (!file.getContentType().equals("image/jpeg")) {
            return "redirect:/omasivu";
        }
        jpegService.addOmaPicture(file, handle, story);

        return "redirect:/omasivu";
    }

    @GetMapping(path = "/omasivu/{id}/content", produces = "image/jpeg")
    @ResponseBody
    public byte[] getContent(@PathVariable Long id) {
        return jpegService.getContent(id);
    }

    @PostMapping("{handle}/profiilikuva/{id}")
    public String selectProfilePicture(@PathVariable String handle, @PathVariable Long id) {
        Account account = accountService.findByUserName(handle);
        account.setProfilePictureId(id);
        accountService.saveAccount(account);
        return "redirect:/omasivu";
    }

    
}
