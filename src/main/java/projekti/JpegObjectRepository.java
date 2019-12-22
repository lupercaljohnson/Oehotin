package projekti;

/**
 *
 * @author Teemu-Petteri Remes, teemu.petteri.remes@gmail.com
 */
import projekti.JpegObject;
import projekti.Account;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpegObjectRepository extends JpaRepository<JpegObject, Long> {

    @EntityGraph(value = "JpegObject.Owners")
    public List<JpegObject> findByOwner(Account account);

}
