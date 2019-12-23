package projekti;

/**
 *
 * @author Teemu-Petteri Remes, teemu.petteri.remes@gmail.com
 */
import projekti.Account;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NamedEntityGraph(name = "JpegObject.Owners",
        attributeNodes = {
            @NamedAttributeNode("owner")})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class JpegObject extends AbstractPersistable<Long> {

    @Lob
  //  @Basic(fetch = FetchType.LAZY)
   // @Type(type = "org.hibernate.type.BinaryType")
    private byte[] content;

    @ManyToMany(mappedBy = "pictures")
    private List<Account> owner = new ArrayList<>();

    private Long likes;

    private String story;

}
