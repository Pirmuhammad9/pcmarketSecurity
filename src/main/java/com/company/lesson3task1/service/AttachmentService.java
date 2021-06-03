package com.company.lesson3task1.service;

import com.company.lesson3task1.entity.Address;
import com.company.lesson3task1.entity.Attachment;
import com.company.lesson3task1.payload.ApiResponse;
import com.company.lesson3task1.repository.AddressRepository;
import com.company.lesson3task1.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    public List<Attachment> getAll(){
        return attachmentRepository.findAll();
    }
    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id){
        Optional<Attachment> byId = attachmentRepository.findById(id);
        return byId.isPresent()?new ApiResponse("found", true):new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     * @param attachment
     * @return ResponseEntity
     */
    public ApiResponse addAttachment(Attachment attachment){
        boolean b = attachmentRepository.existsByName(attachment.getName());
        if (b){
            return new ApiResponse("try again", false);
        }
        Attachment attachment1 = new Attachment();
        attachment1.setContentType(attachment.getContentType());
        attachment1.setName(attachment.getName());
        attachment1.setSize(attachment.getSize());
        attachmentRepository.save(attachment1);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     * @param attachment
     * @return ResponseEntity
     */
    public ApiResponse editAttachment(Integer id, Attachment attachment){
        boolean b = attachmentRepository.existsByNameAndIdNot(attachment.getName(), id);
        Optional<Attachment> byId = attachmentRepository.findById(id);
        if (b || !byId.isPresent()){
            return new ApiResponse("try again", false);
        }
        Attachment attachment1 = byId.get();
        attachment1.setContentType(attachment.getContentType());
        attachment1.setName(attachment.getName());
        attachment1.setSize(attachment.getSize());
        attachmentRepository.save(attachment1);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteAddress(Integer id){
        if (attachmentRepository.existsById(id)){
            attachmentRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }
}
