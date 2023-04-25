package de.thb.Meetup.controller;


import de.thb.Meetup.dto.CategoryDto;
import de.thb.Meetup.dto.CategoryDto;
import de.thb.Meetup.entity.Category;
import de.thb.Meetup.entity.Category;
import de.thb.Meetup.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/category")
public class CategoryControllerTest {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public List<EntityModel<CategoryDto>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();

        List<CategoryDto> categoryDtoList = categories
                .stream()
                .map(CategoryControllerTest::mapToCategoryDto
                )
                .collect(Collectors.toList());

        List<EntityModel<CategoryDto>> entityModels = new ArrayList<>();

        for(CategoryDto categoryDto: categoryDtoList) {
            entityModels.add(EntityModel.of(categoryDto));
        }

        return entityModels;
    }


    @GetMapping("/{id}")
    public EntityModel<CategoryDto> getById(@PathVariable("id") Long id) {
        Optional<Category> categoryOpt = categoryService.getCategoryById(id);

        if (categoryService.getCategoryById(id).isPresent()) {
            CategoryDto categoryDto = categoryOpt.map(CategoryControllerTest::mapToCategoryDto
            ).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found")
            );
            return EntityModel.of(categoryDto);


        } else {
            //könnte man noch einkürzen mit orElseThrow
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found");
        }
    }

    public static CategoryDto mapToCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
