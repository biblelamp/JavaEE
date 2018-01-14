package spring.service;

import java.util.List;

import spring.domain.Category;

public interface CategoryService {
    public Category get(Long id);
    public List<Category> getAll();
    public void save(Category category);
    public void remove(Category category);
}