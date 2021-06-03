package com.company.lesson3task1.service;

import com.company.lesson3task1.entity.*;
import com.company.lesson3task1.payload.ApiResponse;
import com.company.lesson3task1.payload.ProductDto;
import com.company.lesson3task1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;


    /**
     * This function is used to get all addresses in repository
     *
     * @return ResponseEntity
     */
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    /**
     * This function is used to get address by id
     *
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        return byId.isPresent() ? new ApiResponse("found", true) : new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     *
     * @param productDto
     * @return ResponseEntity
     */
    public ApiResponse addProduct(ProductDto productDto) {
        Attachment attachment = new Attachment();
        attachment.setSize(productDto.getSize());
        attachment.setName(productDto.getAttachName());
        attachment.setContentType(productDto.getContentType());
        attachmentRepository.save(attachment);
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setAttachment(attachment);
        attachmentContent.setBytes(productDto.getBytes());
        attachmentContentRepository.save(attachmentContent);
        Category category = new Category();
        category.setName(productDto.getCatName());
        categoryRepository.save(category);
        Product product = new Product();
        product.setAttachment(attachment);
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setInfo(productDto.getInfo());
        product.setWarrenty(productDto.getWarrenty());
        product.setCategory(category);
        productRepository.save(product);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     *
     * @param productDto
     * @return ResponseEntity
     */
    public ApiResponse editProduct(Integer id, ProductDto productDto) {
        Attachment attachment = new Attachment();
        attachment.setSize(productDto.getSize());
        attachment.setName(productDto.getAttachName());
        attachment.setContentType(productDto.getContentType());
        attachmentRepository.save(attachment);
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setAttachment(attachment);
        attachmentContent.setBytes(productDto.getBytes());
        attachmentContentRepository.save(attachmentContent);
        Category category = new Category();
        category.setName(productDto.getCatName());
        categoryRepository.save(category);
        Product product = productRepository.findById(id).get();
        product.setAttachment(attachment);
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setInfo(productDto.getInfo());
        product.setWarrenty(productDto.getWarrenty());
        product.setCategory(category);
        productRepository.save(product);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     *
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }
}
