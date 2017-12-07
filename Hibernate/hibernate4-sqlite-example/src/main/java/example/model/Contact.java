package example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The persistent class for the contact db table
 */
@Entity
public class Contact {
    @Id
    private int id;
    private String name;
    private String email;

    public Contact() {}

    public Contact(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}