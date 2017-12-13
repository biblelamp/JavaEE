package dbase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dbase.domain.Category;
import dbase.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Transactional(readOnly=true)
    public Category get(Long id) {
        return categoryRepo.findOne(id);
    }

    @Transactional(readOnly=true)
    public List<Category> getAll() {
        return categoryRepo.findAll();
    }

    @Transactional
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Transactional
    public void remove(Category category) {
        categoryRepo.delete(category);
    }
}