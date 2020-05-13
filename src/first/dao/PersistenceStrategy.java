package first.dao;

import first.model.Person;
import java.util.List;

public class PersistenceStrategy {

    private final PersonDAO dao;

    public PersistenceStrategy(PersonDAO dao) {
        this.dao = dao;
    }

    public List<Person> read() {
        try {
            List<Person> persons = dao.readPersons();
            return persons;
        } catch (DAOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void write(List<Person> persons) {
        try {
            dao.writePersons(persons);
        } catch (DAOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
