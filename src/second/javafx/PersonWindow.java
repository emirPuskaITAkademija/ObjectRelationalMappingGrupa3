package second.javafx;

import first.dao.PersistenceStrategy;
import first.dao.PersonDAO;
import first.dao.json.JsonDao;
import first.dao.xml.XmlDAO;
import first.model.Person;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JButton;

/**
 * 1. JFrame-> content pane -> JPanel <li>
 * 2. UI controls(JButton, JLabel, JTextField... <li>
 * 3. LayoutManager(GridLayout, BorderLAyout...)
 *
 *
 * @author grupa 1
 */
public class PersonWindow extends Application {

    private final List<Person> personList = new ArrayList<Person>();
    private TextField ninTextField;
    private TextField nameTextField;
    private TextField surnameTextField;
    private TextField ageTextField;

    @Override
    public void start(Stage stage) {
        //stage -> scene
        stage.setTitle("Ekran za dodavanje osoba");

        //scena -> kontejner
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setHgap(10);
        Insets padding = new Insets(25, 25, 25, 25);
        gridPane.setPadding(padding);

        Label ninLabel = new Label("National identification number:");
        gridPane.add(ninLabel, 0, 0);
        ninTextField = new TextField();
        gridPane.add(ninTextField, 1, 0);

        Label nameLabel = new Label("Ime osobe:");
        gridPane.add(nameLabel, 0, 1);
        nameTextField = new TextField();
        gridPane.add(nameTextField, 1, 1);

        Label surnameLabel = new Label("Prezime osobe:");
        gridPane.add(surnameLabel, 0, 2);
        surnameTextField = new TextField();
        gridPane.add(surnameTextField, 1, 2);

        Label ageLabel = new Label("Starost osobe:");
        gridPane.add(ageLabel, 0, 3);
        ageTextField = new TextField();
        gridPane.add(ageTextField, 1, 3);

        Button addPersonButton = new Button("Dodaj osobu");
        AddEventHandler addEventHandler = new AddEventHandler();
        addPersonButton.setOnAction(addEventHandler);
        gridPane.add(addPersonButton, 0, 5);

        Button savePersonButton = new Button("Snimi osobu");
        SaveEventHandler saveEventHandler = new SaveEventHandler();
        savePersonButton.setOnAction(saveEventHandler);
        gridPane.add(savePersonButton, 1, 5);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }

    private class AddEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Person person = new Person(ninTextField.getText(),
                    nameTextField.getText(),
                    surnameTextField.getText(),
                    Integer.parseInt(ageTextField.getText()));
            personList.add(person);

            ninTextField.clear();
            nameTextField.clear();
            surnameTextField.clear();
            ageTextField.clear();
        }

    }

    private class SaveEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            PersonDAO personDao = new XmlDAO();
            PersistenceStrategy persistenceStrategy = new PersistenceStrategy(personDao);
            persistenceStrategy.write(personList);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
