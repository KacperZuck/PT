import org.junit.Test;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
public class ConTest {

    @Test
    public void TestsForCon(){


        Respository repo = mock(Respository.class);
        Controler ctrl = new Controler(repo);
        Book b = new Book(10, "Mockito");

        //1
        assertThat(ctrl.addBook(b)).isEqualTo("Done");
        verify(repo).save(any());
    }

}
