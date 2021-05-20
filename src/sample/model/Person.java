package sample.model;

import java.io.Serializable;

public class Person implements Serializable {

    private String name;
    private String civic;
    private int id;

    public Person(String name, String civic, int id) {
        this.name = name;
        this.civic = civic;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCivic() {
        return civic;
    }

    public void setCivic(String civic) {
        this.civic = civic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", civic='" + civic + '\'' +
                ", id=" + id +
                '}';
    }
}
