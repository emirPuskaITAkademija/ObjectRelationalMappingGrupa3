package first.dao.json;

import first.dao.DAOException;
import first.dao.PersonDAO;
import first.model.Person;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * XML, JSON -> mnostvo klasa koje čitaju i pišu ove fajlove.
 * <p>
 * GSON -> Google .jar
 * <li>
 * JSON simple ; Java SE edicija nema neku standardnu klasu za snimanje persons
 * u JSON file. Postoje biblioteke koje nisu u sklopu Jave ali su napisane u
 * Javi.
 *
 * @author grupa 1
 */
public class JsonDao implements PersonDAO {

    private static final String FILENAME = "persons.json";

    @Override
    public List<Person> readPersons() throws DAOException {
        try (FileReader fileReader = new FileReader(FILENAME)) {
            JSONParser jSONParser = new JSONParser();
            JSONArray jSONArray = (JSONArray) jSONParser.parse(fileReader);
            List<Person> persons = new ArrayList<>();
            for (int i = 0; i < jSONArray.size(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                Person person = new Person();
                person.setNin(jSONObject.get("nin").toString());
                person.setName(jSONObject.get("name").toString());
                person.setSurname(jSONObject.get("surname").toString());
                person.setAge(Integer.parseInt(jSONObject.get("age").toString()));
                persons.add(person);
            }
            return persons;
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void writePersons(List<Person> persons) throws DAOException {
        if (persons == null || persons.isEmpty()) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        for (Person person : persons) {
            //JSONObject
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("nin", person.getNin());
            jSONObject.put("name", person.getName());
            jSONObject.put("surname", person.getSurname());
            jSONObject.put("age", person.getAge());
            jSONArray.add(jSONObject);
        }
        String formatiraniJSONString = jSONArray.toJSONString();
        try (FileWriter out = new FileWriter(FILENAME)) {
            out.write(formatiraniJSONString);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

}
