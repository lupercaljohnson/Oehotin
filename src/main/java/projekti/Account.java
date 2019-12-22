package projekti;

/**
 *
 * @author Teemu-Petteri Remes, teemu.petteri.remes@gmail.com
 */
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends AbstractPersistable<Long> {

    private String accountname;
    private String username;
    private String password;
    //  private String handle;

    @ManyToMany
    private List<JpegObject> pictures = new ArrayList<>();

    @ManyToMany
    private List<Account> seuraajat = new ArrayList<>();

    private Long profilePictureId;

}
