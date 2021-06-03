package com.company.lesson3task1.service;

import com.company.lesson3task1.entity.Address;
import com.company.lesson3task1.payload.ApiResponse;
import com.company.lesson3task1.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    public List<Address> getAll(){
        return addressRepository.findAll();
    }
    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id){
        Optional<Address> byId = addressRepository.findById(id);
        return byId.isPresent()?new ApiResponse("found", true):new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     * @param address
     * @return ResponseEntity
     */
    public ApiResponse addAddress(Address address){
        boolean b = addressRepository.existsByRegion(address.getRegion());
        if (b){
            return new ApiResponse("try again", false);
        }
        Address address1 = new Address();
        address1.setDistrict(address.getDistrict());
        address1.setRegion(address.getRegion());
        address1.setStreet(address.getStreet());
        addressRepository.save(address1);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     * @param address
     * @return ResponseEntity
     */
    public ApiResponse editAddress(Integer id, Address address){
        boolean b = addressRepository.existsByRegionAndIdNot(address.getRegion(), id);
        Optional<Address> byId = addressRepository.findById(id);
        if (b || !byId.isPresent()){
            return new ApiResponse("try again", false);
        }
        Address address1 = byId.get();
        address1.setDistrict(address.getDistrict());
        address1.setRegion(address.getRegion());
        address1.setStreet(address.getStreet());
        addressRepository.save(address1);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteAddress(Integer id){
        if (addressRepository.existsById(id)){
            addressRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }
}
