
package restcontroller;

/**
 *
 * @author Teemu-Petteri Remes, teemu.petteri.remes@gmail.com
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import projekti.Message;
import projekti.service.MessageService;



//  @RestController annotation gives @ResponseBody and (consumes="application/json", produces="application/json") to every @Post and @Get-mappings.
@RestController
public class MessageRestController {
    
    
    @Autowired
    MessageService messageService;
    
  @GetMapping("/rest/messages")
  public List<Message> getMessages() {
      return messageService.list();
  }
  
 //because of "@Rescontroller, has @ResponseBody and (consumes="application/json", produces="application/json") on @GetMapping
  @GetMapping("/rest/messages/{id}")
  public Message getMessage(@PathVariable Long id) {
      return messageService.getOne(id);
  }

  @DeleteMapping("/rest/messages/{id}")
  public Message deleteMessage(@PathVariable Long id) {
      Message message = messageService.getOne(id);
      messageService.delete(id);
      return message;
  }
  
  @PostMapping("/books")
  public Message postMessage(@RequestBody Message message) {
      return messageService.saveRest(message);
  }

}
