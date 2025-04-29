import java.util.*;

public class Respository {

    private final Map<Integer,Book> books = new HashMap<>();

    public void Save(Book book){
        if(books.containsKey(book.getID())){
            throw new IllegalArgumentException("Ksiazka juz istnieje");
        }
        books.put(book.getID(),book);
        System.out.println("Dodano nowa ksiazke: " + book.getName());
    }

    public Optional<Book> getBook(int id){
//        if(books.containsKey(book.getID())){
//            return book;
//        }
        return Optional.ofNullable(books.get(id));
    }

    public void Delete(int id){
        if( !books.containsKey(id)){
            throw new IllegalArgumentException("Ksiazka nie istnieje");
        }
        System.out.println("Usunieto ksiazke: " + books.get(id));
        books.remove(id);
    }

}
