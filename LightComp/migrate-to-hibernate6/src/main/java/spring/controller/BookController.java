package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.domain.Book;
import spring.repository.BookRepository;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/next")
    public String nextBook() {
        Book book = new Book();
        book.setName("next book");
        book = bookRepository.save(book);

        return "Next book #" + String.valueOf(book.getBookId());
    }
}
