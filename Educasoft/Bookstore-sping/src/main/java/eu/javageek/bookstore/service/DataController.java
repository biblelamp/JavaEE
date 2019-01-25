package eu.javageek.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.javageek.bookstore.domain.Book;
import eu.javageek.bookstore.repositories.BookRepository;

@Controller
public class DataController {

	@Autowired
	BookRepository bookRepository;

	@GetMapping(path="/add")
	public @ResponseBody String addNewBook(@RequestParam String name) {
		Book book = new Book();
		book.setName(name);
		bookRepository.save(book);
		return "Added";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}
}
