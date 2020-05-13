package second;

import first.dao.PersistenceStrategy;
import first.dao.PersonDAO;
import first.dao.file.FileDAO;
import first.dao.json.JsonDao;
import first.dao.serializable.SerializableDAO;
import first.dao.xml.XmlDAO;
import first.model.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondApp {

    public static void main(String[] args) {
        PersonDAO dao = new JsonDao();
        //new XmlDAO();
        //new FileDAO();
        //new SerializableDAO();
        PersistenceStrategy persistenceStrategy = new PersistenceStrategy(dao);
        //tranzijentni objekti-> perzistentno
        //HARD kodirat podatke
        Person p1 = new Person("1", "Almir", "Mustafić", 20);
        Person p2 = new Person("2", "Zana", "Sujoldžić", 10);
        Person p3 = new Person("3", "Ivana", "Šuligoj", 13);
        Person p4 = new Person("4", "Adnan", "Husika", 50);
        Person p5 = new Person("5", "Osman", "Bučan", 60);
        Person p6 = new Person("6", "Adnan", "Ćenanović", 20);
        Person p7 = new Person("7", "Slavko", "Kosorić", 43);
        Person p8 = new Person("8", "Lejla", "Smailagić", 20);
        Person p9 = new Person("9", "Sabrija", "Mustafić", 30);
        Person p10 = new Person("10", "Elmedin", "Bešlagić", 20);
        //personList - tranzijentan objekat -> perzistentnim
        //java.util.List i tipa je java.util.Arrays$ArrayList
        //java.util.List i tipa je java.util.ArrayList
        List<Person> temporaryList = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
        //ne mozemo reci temporaryList.add ili temporaryList.remove
        List<Person> personList = new ArrayList<>(temporaryList);
        personList.add(p10);
        /*
        Trebamo prebaciti personList objekat iz tranzijentnog u perzistentno stanje
         */
        persistenceStrategy.write(personList);
        /*
        Učitam ponovno snimljeni objekat personList odnosno da njegovo stanje rekonstrišem
         */
        List<Person> učitanaListaOsoba = persistenceStrategy.read();
        System.out.println("Ako je sve kako treba: ");
        for (Person person : učitanaListaOsoba) {
            System.out.println(person);
        }
    }
}
