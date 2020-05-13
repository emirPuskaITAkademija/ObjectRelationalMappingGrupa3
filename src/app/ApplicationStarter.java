package app;

import first.dao.PersistenceStrategy;
import first.dao.xml.XmlDAO;
import first.model.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import first.dao.PersonDAO;

public class ApplicationStarter {

    public static void main(String[] args) {
        //TRANZIJENTNOG u PERZISTENTNO...txt
        PersonDAO dao = new XmlDAO();
                //new SerializableDAO();
                //new FileDAO();
        PersistenceStrategy persistence = new PersistenceStrategy(dao);
        Person p1 = new Person("1", "Almir", "Mustafić", 20);
        Person p2 = new Person("2", "Zana", "Sujoldžić", 10);
        Person p3 = new Person("3", "Ivana", "Šuligoj", 13);
        Person p4 = new Person("4", "Adnan", "Husika", 50);
        Person p5 = new Person("5", "Osman", "Bučan", 60);
        Person p6 = new Person("6", "Adnan", "Ćenanović", 20);
        Person p7 = new Person("7", "Slavko", "Kosorić", 43);
        Person p8 = new Person("8", "Lejla", "Smailagić", 20);
        Person p9 = new Person("9", "Sabrija", "Mustafić", 30);

        List<Person> persons = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9));
 
        /*
        TRAJNO SAČUVATI ovih 9 objekata: tranzijentnog -> perzistentno
         */
        persistence.write(persons);
        /*
        ČITATI SNIMLJENE PODATKE odnosno prethodno snimljenih 9 objekata
         */
        List<Person> readedPersons = persistence.read();
        for (Person person : readedPersons) {
            System.out.println(person);
        }
        //readedPersons.stream().forEach(System.out::println);
    }
}
