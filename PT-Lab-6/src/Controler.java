
import java.util.*;
public class Controler {

    private final Respository res;

    public Controler(Respository res) {
        this.res = res;
    }

    public void addBook(Book book){
        try{
            res.Save(new Book(book.getID(),book.getName()));
            System.out.println( "Done");
        }catch (IllegalArgumentException e ){
            System.out.println( "Error in adding");
        }
    }

    public void getBook(int id){
        Optional<Book> book = res.getBook(id);
        System.out.println( book.map(b -> "Book: " + b.getName()).orElse("Not found"));
        //return book.map(b -> "Book: " + b.getName()).orElse("not found");

    }

    public void deleteBook(int id){
        try{
            res.Delete(id);
            System.out.println( "Done deleting");
        }catch (IllegalArgumentException e ){
            System.out.println( "Not fund");
        }
    }

}
