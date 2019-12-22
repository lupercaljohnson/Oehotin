package projekti.controller;

/**
 *
 * @author Teemu-Petteri Remes, teemu.petteri.remes@gmail.com
 */
import projekti.service.AccountService;
import projekti.service.MessageService;
import java.io.IOException;
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
import projekti.JpegObjectRepository;

@Controller
public class ToisenSivuController {

    @Autowired
    AccountService accountService;

    @Autowired
    MessageService messageService;

    @Autowired
    private JpegObjectRepository jpegRepository;

    @GetMapping(value = "{handle}/toisensivu")
    public String toisenSivuView(Model model, @PathVariable String handle) {

        String username = handle;

        List<JpegObject> kuvaLista = jpegRepository.findByOwner(accountService.findByUserName(username));

        int imageCount = kuvaLista.size();

        model.addAttribute("count", imageCount);

        model.addAttribute("message", username);
        model.addAttribute("handle", username);
        model.addAttribute("messages", this.messageService.list());
        model.addAttribute("pictures", kuvaLista);
        model.addAttribute("profilepic", (accountService.findByUserName(username)).getProfilePictureId());
        return "toisensivu";
    }

    @PostMapping("{handle}/likes/{id}")
    public String likesPicture(@PathVariable String handle, @PathVariable Long id) {
        JpegObject picture = jpegRepository.getOne(id);
        Long likes = picture.getLikes() + 1L;
        picture.setLikes(likes);
        jpegRepository.save(picture);

        String returnString = "redirect:/" + handle + "/toisensivu";
        return returnString;
    }

    @PostMapping("{handle}/seuraa")
    public String seuraaToista(@PathVariable String handle) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Account seurattava = accountService.findByUserName(username);
        Account seuraaja = accountService.findByUserName(handle);
        seurattava.getSeuraajat().add(seuraaja);
        accountService.saveAccount(seurattava);

        String returnString = "redirect:/" + handle + "/toisensivu";
        return returnString;
    }

    @GetMapping(path = "/toisensivu/{id}/content", produces = "image/jpeg")
    @ResponseBody
    public byte[] getContent(@PathVariable Long id) {
        return jpegRepository.getOne(id).getContent();

    }

}
