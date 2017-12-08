package example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Table(name="person")
public class Person {

    @Id
    //@Column(name="id")
    int    id;
    //@Column(name="name")
    String name;

    public Person() {}

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Person {id = " + String.valueOf(id) + ", name = '" + name + "'}";
    }
}