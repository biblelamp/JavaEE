package eu.javageek.bookstore.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@ManyToOne(fetch=FetchType.EAGER) // not .LAZY
	private Author authorOfBook;

    private String name;

	@ManyToMany
    private List<Genre> genre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Author getAuthorOfBook() {
		return authorOfBook;
	}

	public void setAuthorOfBook(Author authorOfBook) {
		this.authorOfBook = authorOfBook;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Genre> getGenre() {
		return genre;
	}

	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}

}
