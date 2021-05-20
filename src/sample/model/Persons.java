package sample.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class Persons {
    @SerializedName("results")
    ArrayList<Person> persons;

    public Persons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "person=" + persons +
                '}';
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }


}
