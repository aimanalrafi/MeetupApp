package de.thb.Meetup.service;

import de.thb.Meetup.entity.Category;
import de.thb.Meetup.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

//    public Category getCategoryByIdTest(Long id) {return  categoryRepository.getCategoryByIdTestRep(id);}
}
