package eu.javageek.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.javageek.bookstore.domain.Author;
import eu.javageek.bookstore.domain.Book;
import eu.javageek.bookstore.repositories.BookRepository;
import eu.javageek.bookstore.repositories.AuthorRepository;

@Controller
public class DataController {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorRepository authorRepository;

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

	@GetMapping(path="/delete")
	public @ResponseBody String deleteBookById(@RequestParam Integer id) {
		if (bookRepository.exists(id)) {
			bookRepository.delete(id);
			return "Deleted";
		}
		return "Not found";
	}

	@GetMapping(path="/deleteAll")
	public @ResponseBody String deleteAll() {
		bookRepository.deleteAll();
		return "Deleted all";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}
}
