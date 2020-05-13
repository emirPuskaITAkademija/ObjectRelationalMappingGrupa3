package first.model;

import java.io.Serializable;
//POJO klase i JAVA bean

public class Person implements Serializable {

    private String nin;
    private String name;
    private String surname;
    private int age;

    public Person() {
    }

    public Person(String nin, String name, String surname, int age) {
        this.nin = nin;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
