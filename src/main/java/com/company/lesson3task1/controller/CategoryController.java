package com.company.lesson3task1.controller;

import com.company.lesson3task1.entity.Address;
import com.company.lesson3task1.entity.Category;
import com.company.lesson3task1.payload.ApiResponse;
import com.company.lesson3task1.service.AddressService;
import com.company.lesson3task1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    @GetMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN', 'OPERATOR')")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN', 'OPERATOR')")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Integer id){
        ApiResponse response = categoryService.getOne(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    /**
     * This function is used to add new address into database
     * @param category
     * @return ResponseEntity
     */
    @PostMapping
    @PreAuthorize(value = "hasAnyRole('MODERATOR','SUPER_ADMIN', 'OPERATOR')")
    public ResponseEntity<?> addCategry(@RequestBody Category category){
        ApiResponse apiResponse = categoryService.addCategory(category);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    /**
     * This function is used to edit address by id;
     * @param category
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('MODERATOR','SUPER_ADMIN')")
    public ResponseEntity<?> editCategory(@PathVariable Integer id, @RequestBody Category category){
        ApiResponse apiResponse = categoryService.editCategory(id, category);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    public ResponseEntity<?> deleteCategroy(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
