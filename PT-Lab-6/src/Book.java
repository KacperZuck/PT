public class Book {

    private int ID;
    private String Name;

    public Book (int ID, String Name){
        this.ID = ID;
        this.Name = Name;
    }

    public int getID() {
        return ID;
    }
    public String getID_S(){
        return Integer.toString(ID);
    }

    public String getName() {
        return Name;
    }

}
