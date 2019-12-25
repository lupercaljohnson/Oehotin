package projekti.service;

/**
 *
 * @author Teemu-Petteri Remes, teemu.petteri.remes@gmail.com
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.Message;
import projekti.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> list() {

        return messageRepository.findAll();
    }

    public List<Message> listPic(Long pictureId) {
        return messageRepository.findByPictureId(pictureId);
    }

    public Message saveRest(Message msg) {
        return messageRepository.save(msg);
    }

    public void save(Message msg) {
        messageRepository.save(msg);
    }
    
    public Message getOne(Long id){
        return messageRepository.getOne(id);
    }
    
    
    public void delete(Long id){
     messageRepository.deleteById(id);
    }
    
    public void deletemessages(Long id){
     List<Message> lista = messageRepository.findByPictureId(id);
     for (int i=0 ; i < lista.size() ; i++){
         messageRepository.delete(lista.get(i));
     }
    }
}
