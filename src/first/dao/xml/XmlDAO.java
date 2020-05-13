package first.dao.xml;

import first.dao.DAOException;
import first.model.Person;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import first.dao.PersonDAO;

/**
 * <li>XMLDecoder
 * <li>XMLEncoder
 *
 */
public class XmlDAO implements PersonDAO {

    private static final String FILENAME = "persons.xml";

    @Override
    public List<Person> readPersons() throws DAOException {
        //Philip Milner -> XMLEncoder
        try (XMLDecoder xmlDekoder = new XMLDecoder(new FileInputStream(FILENAME))) {
            List<Person> persons = (List<Person>) xmlDekoder.readObject();
            return persons;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    @Override
    public void writePersons(List<Person> persons) throws DAOException {
        if (persons == null || persons.isEmpty()) {
            return;
        }
        //Philip Milner -> XMLEncoder
        try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(FILENAME))) {
            // xmlEncoder.writeObject(java.util.Arrays$ArrayList NEGO java.util.ArrayList)
            xmlEncoder.writeObject(persons);
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

}
