package com.company.lesson3task1.service;

import com.company.lesson3task1.entity.*;
import com.company.lesson3task1.payload.ApiResponse;
import com.company.lesson3task1.payload.OrderDto;
import com.company.lesson3task1.repository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    AddressRepository addressRepository;


    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    public List<Order> getAll(){
        return orderRepository.findAll();
    }
    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id){
        Optional<Order> byId = orderRepository.findById(id);
        return byId.isPresent()?new ApiResponse("found", true):new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     * @param orderDto
     * @return ResponseEntity
     */
    public ApiResponse addOrder(OrderDto orderDto){
        List<Product> allById = productRepository.findAllById(orderDto.getProducts());
        Basket basket = new Basket();
        basket.setPrice(orderDto.getPrice());
        basket.setProduct(allById);
        basketRepository.save(basket);
        Address address = new Address();
        address.setStreet(orderDto.getStreet());
        address.setRegion(orderDto.getRegion());
        address.setDistrict(orderDto.getDistrict());
        addressRepository.save(address);
        Order order = new Order();
        order.setAddress(address);
        order.setName(order.getName());
        order.setBasket(basket);
        order.setPhoneNumber(orderDto.getPhoneNumber());
        orderRepository.save(order);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     * @param orderDto
     * @return ResponseEntity
     */
    public ApiResponse editOrder(Integer id, OrderDto orderDto){
        List<Product> allById = productRepository.findAllById(orderDto.getProducts());
        Basket basket = new Basket();
        basket.setPrice(orderDto.getPrice());
        basket.setProduct(allById);
        basketRepository.save(basket);
        Address address = new Address();
        address.setStreet(orderDto.getStreet());
        address.setRegion(orderDto.getRegion());
        address.setDistrict(orderDto.getDistrict());
        addressRepository.save(address);
        Order order = orderRepository.findById(id).get();
        order.setAddress(address);
        order.setName(order.getName());
        order.setBasket(basket);
        order.setPhoneNumber(orderDto.getPhoneNumber());
        orderRepository.save(order);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteOrder(Integer id){
        if (orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }
}
