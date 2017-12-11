package dbase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dbase.domain.Author;
import dbase.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepo;

    @Transactional(readOnly=true)
    public Author get(Long id) {
        return authorRepo.findOne(id);
    }

    @Transactional(readOnly=true)
    public List<Author> getAll() {
        return authorRepo.findAll();
    }

    @Transactional
    public void save(Author author) {
        authorRepo.save(author);
    }

    @Transactional
    public void remove(Author author) {
        authorRepo.delete(author);
    }
}