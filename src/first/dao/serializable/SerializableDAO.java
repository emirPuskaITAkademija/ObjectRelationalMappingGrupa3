package first.dao.serializable;

import first.dao.DAOException;
import first.model.Person;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import first.dao.PersonDAO;

public class SerializableDAO implements PersonDAO {

    private static final String FILENAME = "persons.dat";

    @Override
    public List<Person> readPersons() throws DAOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            List<Person> persons = (List<Person>) ois.readObject();
            return persons;
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

    @Override
    public void writePersons(List<Person> persons) throws DAOException {
        if (persons == null || persons.isEmpty()) {
            return;
        }
        try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            ous.writeObject(persons);
        } catch (Exception exception) {
            throw new DAOException(exception.getMessage());
        }
    }

}
