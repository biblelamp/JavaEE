package dbase.service;

import java.util.List;

import dbase.domain.Category;

public interface CategoryService {
    public Category get(Long id);
    public List<Category> getAll();
    public void save(Category category);
    public void remove(Category category);
}