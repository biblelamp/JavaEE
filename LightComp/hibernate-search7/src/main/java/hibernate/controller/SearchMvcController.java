package hibernate.controller;

import hibernate.controller.dto.BookDTO;
import hibernate.controller.dto.SearchDTO;
import hibernate.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchMvcController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String searchForm(Model model) {
        model.addAttribute("book", new BookDTO());
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("search", new SearchDTO());
        model.addAttribute("result", new ArrayList<>());
        return "searchForm";
    }

    @PostMapping("/add")
    public String addBook(Model model, @ModelAttribute("book") BookDTO book) {
        bookService.add(book);

        model.addAttribute("book", new BookDTO());
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("search", new SearchDTO());
        model.addAttribute("result", new ArrayList<>());
        return "searchForm";
    }

    @DeleteMapping("/delete")
    public String deleteEvent(Integer id) {
        bookService.remove(id);
        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchResult(Model model, @ModelAttribute("search") SearchDTO search) {
        model.addAttribute("book", new BookDTO());
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("search", search);

        List<BookDTO> result = bookService.search(search.getText());

        model.addAttribute("result", result);
        return "searchForm";
    }

}
