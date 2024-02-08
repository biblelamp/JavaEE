package hibernate.service;

import hibernate.controller.dto.BookDTO;
import hibernate.domain.Book;
import hibernate.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<BookDTO> search(String txtSearch) {
        SearchSession session = Search.session(entityManager);
        int from = 0;
        int count = 100;

        SearchResult<Book> result = session.search(Book.class)
                .where(f -> f.bool().with(b -> {
                    b.must(f.matchAll());
                    if (txtSearch != null) {
//                        b.must(f.match().field(Book.NAME).matching(txtSearch));
//                        b.must(f.wildcard().field(Book.NAME).matching("*" + txtSearch + "*"));
//                        List<String> tokens = analyze(txtSearch, new WhitespaceAnalyzer());
                        String[] tokens = txtSearch.split(" ");
                        for (String token : tokens) {
                            b.must(f.wildcard().field(Book.NAME).matching("*" + token + "*"));
                        }
                    }
                }))
                .fetch(from, count);

        List<BookDTO> books = new ArrayList<>((int) result.total().hitCount());
        result.hits().forEach(b -> books.add(BookDTO.getInstance(b)));

        return books;
    }

    public void add(BookDTO book) {
        bookRepository.save(new Book(book.getName()));
    }

    public List<BookDTO> findAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> result = new ArrayList<>(books.size());
        books.forEach(b -> result.add(BookDTO.getInstance(b)));
        return result;
    }

    public void remove(Integer bookId) {
        bookRepository.deleteById(bookId);
    }
}
