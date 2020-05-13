/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first;

import first.dao.PersistenceStrategy;
import first.dao.file.FileDAO;
import first.model.Person;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import first.dao.PersonDAO;

/**
 *
 * @author grupa 1
 */
public class PersonFrame extends JFrame {

    private JTextField ninField = new JTextField();
    private JTextField nameTextField = new JTextField();
    private JTextField surnameTextField = new JTextField();
    private JTextField ageTextField = new JTextField();
    private JButton addButton = new JButton("Add");
    private JButton saveButton = new JButton("Save");
    private List<Person> persons = new ArrayList<>();

    public PersonFrame() {
        setTitle("Persons");
        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        add(ninField);
        add(nameTextField);
        add(surnameTextField);
        add(ageTextField);
        AddButtonListener addActionListener = new AddButtonListener();
        addButton.addActionListener(addActionListener);
        add(addButton);
        SaveButtonListener saveButtonListener = new SaveButtonListener();
        saveButton.addActionListener(saveButtonListener);
        add(saveButton);

    }

    public void showFrame() {
        setSize(500, 500);
        pack();
        setVisible(true);
    }

    private class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nin = ninField.getText();
            String name = nameTextField.getText();
            String surname = surnameTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            Person p = new Person(nin, name, surname, age);
            persons.add(p);
            ninField.setText("");
            nameTextField.setText("");
            surnameTextField.setText("");
            ageTextField.setText("");
        }

    }

    private class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PersonDAO dao = new FileDAO();
            PersistenceStrategy persistence = new PersistenceStrategy(dao);
            persistence.write(persons);
        }

    }

    public static void main(String[] args) {

        PersonFrame personFrame = new PersonFrame();
        SwingUtilities.invokeLater(personFrame::showFrame);
    }
}
