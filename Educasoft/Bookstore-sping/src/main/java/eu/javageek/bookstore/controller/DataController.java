package eu.javageek.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.javageek.bookstore.domain.Book;
import eu.javageek.bookstore.domain.Author;
import eu.javageek.bookstore.domain.Genre;
import eu.javageek.bookstore.domain.specification.BookSpecification;
import eu.javageek.bookstore.domain.specification.AuthorSpecification;
import eu.javageek.bookstore.domain.specification.GenreSpecification;
import eu.javageek.bookstore.repositories.BookRepository;
import eu.javageek.bookstore.repositories.AuthorRepository;
import eu.javageek.bookstore.repositories.GenreRepository;

@Controller
public class DataController {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	GenreRepository genreRepository;

	@GetMapping(path="/byAuthor")
	public @ResponseBody List<Book> getBooksByAuthor(@RequestParam Integer id) {

		Specification<Book> specification = BookSpecification.getBookByAuthor(authorRepository.findOne(id));
		List<Book> books = bookRepository.findAll(specification);

		return books;
	}

	@GetMapping(path="/byGenre")
	public @ResponseBody List<Book> getBooksByGenre(@RequestParam Integer id) {

		Specification<Book> specification = BookSpecification.getBookByGenre(genreRepository.findOne(id));
		List<Book> books = bookRepository.findAll(specification);

		return books;
	}

	@GetMapping(path="/genreByAuthor")
	public @ResponseBody List<Genre> getGenreByAuthor(@RequestParam Integer id) {

		Specification<Genre> specification = GenreSpecification.getGenreByAuthor(authorRepository.findOne(id));
		List<Genre> genres = genreRepository.findAll(specification);

		return genres;
	}

	@GetMapping(path="/genreWhichDefine")
	public @ResponseBody List<Genre> getGenreWhichDefineAtLeastNBook(@RequestParam Long  n) {

		Specification<Genre> specification = GenreSpecification.getGenreWhitcDefineAtLeastNBook(n);
		List<Genre> genres = genreRepository.findAll(specification);

		return genres;
	}

	@GetMapping(path="/booksWithAuthor")
	public @ResponseBody List<Book> getBookWithAuthor() {

		Specification<Book> specification = BookSpecification.getBookWithAuthor();
		List<Book> books = bookRepository.findAll(specification);

		return books;
	}

	@GetMapping(path="/authorsWithBook")
	public @ResponseBody List<Author> getAuthorsWithBook() {

		Specification<Author> specification = AuthorSpecification.getAuthorWithBook();
		List<Author> authors = authorRepository.findAll(specification);

		return authors;
	}

	@GetMapping(path="/add")
	public @ResponseBody String addNewBook(@RequestParam String name, 
			@RequestParam String author) {

		Author authorOfBook;

		if (author.chars().allMatch(Character::isDigit)) {
			authorOfBook = authorRepository.findOne(Integer.parseInt(author));
		} else {
			authorOfBook = new Author();
			authorOfBook.setName(author);
			authorRepository.save(authorOfBook);
		}

		Book book = new Book();
		book.setName(name);
		book.setAuthorOfBook(authorOfBook);
		bookRepository.save(book);

		return "Added";
	}

	@GetMapping(path="/deleteBook")
	public @ResponseBody String deleteBookById(@RequestParam Integer id) {
		if (bookRepository.exists(id)) {
			bookRepository.delete(id);
			return "Deleted";
		}
		return "Not found";
	}

	@GetMapping(path="/deleteAllBook")
	public @ResponseBody String deleteAll() {
		bookRepository.deleteAll();
		return "Deleted all";
	}

	@GetMapping(path="/allBooks")
	public @ResponseBody Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}
}
