package hibernate.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Length;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

/**
CREATE TABLE book (
    book_id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
    );
*/

@Getter
@Setter
@NoArgsConstructor
@Indexed
@Entity
public class Book {

    public static final String NAME = "name";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;

    @FullTextField
    @Column(length = Length.LONG) // Hibernate long text field
    private String name;

    public Book(String name) {
        this.name = name;
    }
}
