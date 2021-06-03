package com.company.lesson3task1.service;

import com.company.lesson3task1.entity.Basket;
import com.company.lesson3task1.entity.Category;
import com.company.lesson3task1.entity.Product;
import com.company.lesson3task1.payload.ApiResponse;
import com.company.lesson3task1.payload.BasketDto;
import com.company.lesson3task1.repository.BasketRepository;
import com.company.lesson3task1.repository.CategoryRepository;
import com.company.lesson3task1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;


    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        return byId.isPresent()?new ApiResponse("found", true):new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     * @param category
     * @return ResponseEntity
     */
    public ApiResponse addCategory(Category category){
        if (categoryRepository.existsByName(category.getName())){
            return new ApiResponse("this category already exists", false);
        }
        Category category1 = new Category();
        category1.setName(category.getName());
        categoryRepository.save(category1);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     * @param category
     * @return ResponseEntity
     */
    public ApiResponse editCategory(Integer id, Category category){
        if (categoryRepository.existsByNameAndIdNot(category.getName(), id) || !categoryRepository.findById(id).isPresent()){
            return new ApiResponse("try again", false);
        }
        Category category1 = categoryRepository.findById(id).get();
        category1.setName(category.getName());
        categoryRepository.save(category1);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteCategory(Integer id){
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }
}
