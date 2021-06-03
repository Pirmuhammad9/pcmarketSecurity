package com.company.lesson3task1.controller;

import com.company.lesson3task1.entity.Address;
import com.company.lesson3task1.payload.ApiResponse;
import com.company.lesson3task1.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    @GetMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN', 'OPERATOR')")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(addressService.getAll());
    }

    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN', 'OPERATOR')")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Integer id){
        ApiResponse response = addressService.getOne(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    /**
     * This function is used to add new address into database
     * @param address
     * @return ResponseEntity
     */
    @PostMapping
    @PreAuthorize(value = "hasAnyRole('MODERATOR','SUPER_ADMIN', 'OPERATOR')")
    public ResponseEntity<?> addAnswer(@RequestBody Address address){
        ApiResponse apiResponse = addressService.addAddress(address);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    /**
     * This function is used to edit address by id;
     * @param address
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('MODERATOR','SUPER_ADMIN')")
    public ResponseEntity<?> editAddress(@PathVariable Integer id, @RequestBody Address address){
        ApiResponse apiResponse = addressService.editAddress(id, address);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
