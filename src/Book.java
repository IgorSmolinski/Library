import java.lang.annotation.Retention;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

interface Rateable {

    int setRating(int rate);
}

interface Player {

    void play(DVDandCD item);     //Dlaczego oznacza mi obie metody play i stop
    void stop(DVDandCD item);     // Jako nigdy nie używane? Zostały nadpisane classie PlayerService

    default void insert(DVDandCD item){System.out.println("Opening CD/DVD");}
    default void eject(DVDandCD item){System.out.println("Ejecting CD/DVD");}
}


class RatingService  {

    public int rating(Rateable item, int note){   //Czy każdy obiekt DVD i Book są iteam-ami Rateable, do przegadania podczas spotkania!
        item.setRating(note);
        return note;
    }

}

class Rental {
    private int rent;


    public Rental(int i) {
        this.rent = i;
    }


    public int getRent() {
        return this.rent;
    }


    public void renting(HashMap example, Book item, Rental smth) {
        example.put(item, smth);
    }

    public void removeFromList(HashMap example, Book item, Rental smth) {
        example.remove(item, smth);
    }

    public void show(HashMap<Book,Rental> name) {

        for (Map.Entry<Book, Rental> entry : name.entrySet()) {
            System.out.println(entry.getKey().getName());

        }
    }

    public void removeFromListUsingTitle(HashMap<Book,Rental> name,String title){
        for (Map.Entry<Book, Rental> entry : name.entrySet()) {
            if (entry.getKey().getName()==title){
            System.out.println(entry.getKey());
            name.remove(entry.getKey());
            };

        }


    }

    public void removeFromListUsingId(HashMap<Book,Rental> name,int idNumber){
        for (Map.Entry<Book, Rental> entry : name.entrySet()) {
            if (entry.getKey().getId()==idNumber){
                System.out.println("This book that i want remove from list: ");
                System.out.println(entry.getKey().getName());
                System.out.println(entry.getKey().getAuthor());
                System.out.println(entry.getKey().getId());
                System.out.println(entry.getKey());
                //name.remove(entry.getKey());  //w metodzie removeFromListUsingTitle ten zapis działa i usuwa wpis z mapy
                                                //a używając jej tutaj, wywala mi pod koniec błąd.
            }

        }


    }


}

 class Product implements Rateable{
    private String name, author;
    private Integer id;
    private Integer note;

    public Product(String name, String author,  Integer id) {
        this.name=name;
        this.author = author;
        this.id = id;

    }

    @Override
    public int setRating(int note){
        this.note=note;
        return note;
    }

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Product product = (Product) o;
         return Objects.equals(name, product.name) &&
                 Objects.equals(author, product.author) &&
                 Objects.equals(id, product.id) &&
                 Objects.equals(note, product.note);
     }

     @Override
     public int hashCode() {

         return Objects.hash(name, author, id, note);
     }

     public String getName(){return this.name;};
    public String getAuthor(){return this.author;};
    public Integer getId(){return this.id;};
    public Integer getNote(){return this.note;};

}

class Book extends Product{
    private int pages;

    public Book(String name, String author, Integer id, int pages) {
        super(name, author, id);
        this.pages=pages;
    }

    public int getPages(){return this.pages;};
}


class DVDandCD extends Product{
    private String description;

    public DVDandCD(String name, String author, Integer id, String description){
        super(name, author,id);
        this.description=description;
    }

    public String getDescription() {return this.description;}

    }



class PlayerService implements Player{

    @Override
    public void play(DVDandCD example){
        System.out.println(example.getDescription());
        System.out.println("Start playing");

    }

    @Override
    public void stop(DVDandCD example){
        System.out.println(example.getDescription());
        System.out.println("Stop playing");

    }

}


class Aplication{


    public static void main(String[] args) {
        Book k1 = new Book("Podpalaczka", "Stephen King", 1, 458);
        Book k2 = new Book("Opiekunowie", "Dean Koontz", 2, 320);
        Book k3 = new Book ("Policja", "Jo Nesbo", 3, 450);
        Book k4 = new Book ("Potop", "Sienkiewicz", 4, 367);
        Book k5 = new Book ("Bastion", "Stephen King", 5, 967);
        Book k6 = new Book ("My", "Zamientin", 6, 240);
        Book k7 = new Book ("Wystarczy być", "Kosiński", 7, 550);
        DVDandCD i1 = new DVDandCD("12 małp", "Terry Giliam",8, "Więzień zostaje wysłany w przeszłość, aby zapobiec wybuchowi epidemii, która prawie zniszczyła ludzkość." );
        DVDandCD i2 = new DVDandCD("Donnie Darko", "Richard Kelly",9, "Donnie Darko, uważany za nastolatka z zaburzeniami osobowości, spotyka Franka - tajemniczą postać w kostiumie królika, która zaczyna manipulować jego życiem. " );
        DVDandCD i3 = new DVDandCD("Fight Club", "David Fincher",10, "Dwóch mężczyzn znudzonych rutyną zakłada klub, w którym co tydzień odbywają się walki na gołe pięści." );
        DVDandCD i4 = new DVDandCD("Big Fish", "Tim Burton",10, "Schorowany Edward Bloom słynął z nieprawdopodobnych opowieści. Jego syn próbuje dociec, ile w nich było prawdy." );
        DVDandCD i5 = new DVDandCD("Crash", "Paul Haggis",10, "W ciągu 36 godzin losy nieznanych sobie mieszkańców Los Angeles niespodziewanie się krzyżują." );

        PlayerService p1 = new PlayerService();
        Rental r1 = new Rental(1);
        Rental r2 = new Rental(0);


        System.out.println(k1.getName());
        System.out.println(k1.getAuthor());
        System.out.println(k1.getId());
        System.out.println(k1.getPages());
        System.out.println(k1.getNote());
        System.out.println(i1.getName());


    //Player
        p1.insert(i1);
        p1.play(i1);
        p1.stop(i1);
        p1.eject(i1);

    //Rating
        RatingService rateNote = new RatingService();
        rateNote.rating(k1 , 5 );
        System.out.println("&*&*&*&*&*&*&*&*&*&*&*&*&*&");
        System.out.println(k1.getNote());
        rateNote.rating(i1, 7);
        System.out.println(i1.getNote());
        k2.setRating(3);
        System.out.println(k2.getNote());


        HashMap<Book,Rental> MapOfBorrows= new HashMap<>();  //w tym przypadku nie lepiej było zrobić mapy<Book,RatingService>?


        //renting and giving back item
        r1.renting(MapOfBorrows, k1, r1);
        r1.renting(MapOfBorrows, k2, r1);
        r1.renting(MapOfBorrows, k3, r1);
        r1.renting(MapOfBorrows, k4, r1);
        r1.renting(MapOfBorrows, k5, r1);
        r1.renting(MapOfBorrows, k6, r1);
        r1.renting(MapOfBorrows, k7, r1);

        //Showing all items in map
        r1.show(MapOfBorrows);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        //Removing object from map
        r1.removeFromListUsingTitle(MapOfBorrows, "Potop");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        r1.show(MapOfBorrows);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        r1.removeFromListUsingId(MapOfBorrows,3);   // nie działa :(
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        r1.show(MapOfBorrows);


    }

}

