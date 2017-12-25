package dbase.service;

import java.util.List;

import dbase.domain.Author;

public interface AuthorService {
    public Author get(Long id);
    public List<Author> getAll();
    public void save(Author author);
    public void remove(Author author);
}