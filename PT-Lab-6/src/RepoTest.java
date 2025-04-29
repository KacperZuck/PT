import org.junit.Test;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class RepoTest {

    @Test
    public void testForRepo(){
        Respository respository = new Respository();
        Book b = new Book(1,"Dziady");

        //1
        respository.Save(b);
        assertThat(respository.getBook(1)).isPresent();

        //2
        respository.Save(new Book(2, "Test"));
        assertThatThrownBy(() -> respository.Save(new Book(2, "test2")))
                .isInstanceOf(IllegalArgumentException.class);

        //3
        respository.Save(new Book(3,"3"));
        respository.Delete(3);
        assertThat(respository.Delete(3)).isNotPresent();

        //4
        assertThatThrownBy(() -> respository.Delete(99))
                .isInstanceOf(IllegalArgumentException.class);

        // TESTY DLA KONTROLERA
    }

}
