package projekti.service;

/**
 *
 * @author Teemu-Petteri Remes, teemu.petteri.remes@gmail.com
 */
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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

@Service
public class JpegService {

    @Autowired
    private JpegObjectRepository jpegRepository;

    @Autowired
    private AccountService accountService;

    public Long getCount() {
        return jpegRepository.count();

    }

    public String add(MultipartFile file) throws IOException {
        if (!file.getContentType().equals("image/jpeg")) {
            return "error";
        }

        JpegObject jpegObject = new JpegObject();
        jpegObject.setContent(file.getBytes());
        jpegRepository.save(jpegObject);
        return "ok";

    }

    public byte[] getContent(Long id) {
        return jpegRepository.getOne(id).getContent();

    }

   
    public List<JpegObject> findByOwner(String username) {
        
        return jpegRepository.findByOwner(accountService.findByUserName(username));
    }

    public void addOmaPicture(MultipartFile file, String handle, String story) throws IOException {
        JpegObject jpegObject = new JpegObject();
        jpegObject.setContent(file.getBytes());
        jpegObject.setLikes(0L);
        jpegObject.setStory(story);
        jpegObject = jpegRepository.save(jpegObject);

        Account account = accountService.findByUserName(handle);
        account.getPictures().add(jpegObject);

        jpegRepository.save(jpegObject);
    }

    public void save(JpegObject kuva) {
        jpegRepository.save(kuva);
    }
}

