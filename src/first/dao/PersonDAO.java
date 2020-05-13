package first.dao;

import first.model.Person;
import java.util.List;

public interface PersonDAO {

    /**
     * Ova metoda treba da pročita trajno snimljenje osobe i da nam vrati listu
     * takvih osoba.
     * <p>
     * @return list of persons
     * @throws DAOException
     */
    public List<Person> readPersons() throws DAOException;

    /**
     * Ova metoda treba da snimi osobe koje je dobila kroz parametar persons.
     * <p>
     * @param persons
     * @throws DAOException
     */
    public void writePersons(List<Person> persons) throws DAOException;
}
