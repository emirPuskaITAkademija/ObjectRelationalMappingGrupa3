package first.dao.file;

import first.dao.DAOException;
import first.model.Person;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import first.dao.PersonDAO;

public class FileDAO implements PersonDAO {

    private static final String FILENAME = "persons.txt";

    @Override
    public List<Person> readPersons() throws DAOException {
        List<Person> persons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                //line = "23;Zana;Sujoldžić;13"
                //String[] fields = line.split(";");
                StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
                String nin = stringTokenizer.nextToken();
                String name = stringTokenizer.nextToken();
                String surname = stringTokenizer.nextToken();
                int age = Integer.parseInt(stringTokenizer.nextToken());
                Person person = new Person(nin, name, surname, age);
                persons.add(person);
            }
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
        return persons;
    }

    @Override
    public void writePersons(List<Person> persons) throws DAOException {
        if (persons == null || persons.isEmpty()) {
            return;
        }
        //println -> vaša omiljena metoda
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (Person person : persons) {
                //JMBG;name;surname;age
                writer.println(person.getNin() + ";"
                        + person.getName() + ";"
                        + person.getSurname() + ";"
                        + person.getAge());
            }
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

}
