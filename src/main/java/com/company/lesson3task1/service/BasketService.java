package com.company.lesson3task1.service;

import com.company.lesson3task1.entity.Attachment;
import com.company.lesson3task1.entity.AttachmentContent;
import com.company.lesson3task1.entity.Basket;
import com.company.lesson3task1.entity.Product;
import com.company.lesson3task1.payload.ApiResponse;
import com.company.lesson3task1.payload.AttachmentContentDto;
import com.company.lesson3task1.payload.BasketDto;
import com.company.lesson3task1.repository.AttachmentContentRepository;
import com.company.lesson3task1.repository.AttachmentRepository;
import com.company.lesson3task1.repository.BasketRepository;
import com.company.lesson3task1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketService {
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    ProductRepository productRepository;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    public List<Basket> getAll(){
        return basketRepository.findAll();
    }
    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id){
        Optional<Basket> byId = basketRepository.findById(id);
        return byId.isPresent()?new ApiResponse("found", true):new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     * @param basketDto
     * @return ResponseEntity
     */
    public ApiResponse addBasket(BasketDto basketDto){
        List<Product> allById = productRepository.findAllById(basketDto.getProducts());
        Basket basket = new Basket();
        basket.setPrice(basketDto.getPrice());
        basket.setProduct(allById);
        basketRepository.save(basket);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     * @param basketDto
     * @return ResponseEntity
     */
    public ApiResponse editBasket(Integer id, BasketDto basketDto){
        if (!basketRepository.existsById(id)){
            return new ApiResponse("try again", false);
        }
        List<Product> allById = productRepository.findAllById(basketDto.getProducts());
        Basket basket = basketRepository.findById(id).get();
        basket.setPrice(basketDto.getPrice());
        basket.setProduct(allById);
        basketRepository.save(basket);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteBasket(Integer id){
        if (basketRepository.existsById(id)){
            basketRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }
}
