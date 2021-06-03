package com.company.lesson3task1.controller;

import com.company.lesson3task1.entity.Address;
import com.company.lesson3task1.entity.Attachment;
import com.company.lesson3task1.payload.ApiResponse;
import com.company.lesson3task1.service.AddressService;
import com.company.lesson3task1.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    @GetMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN', 'OPERATOR')")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(attachmentService.getAll());
    }

    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN', 'OPERATOR')")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Integer id){
        ApiResponse response = attachmentService.getOne(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    /**
     * This function is used to add new address into database
     * @param attachment
     * @return ResponseEntity
     */
    @PostMapping
    @PreAuthorize(value = "hasAnyRole('MODERATOR','SUPER_ADMIN', 'OPERATOR')")
    public ResponseEntity<?> addAtatchment(@RequestBody Attachment attachment){
        ApiResponse apiResponse = attachmentService.addAttachment(attachment);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    /**
     * This function is used to edit address by id;
     * @param attachment
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('MODERATOR','SUPER_ADMIN')")
    public ResponseEntity<?> editAttachment(@PathVariable Integer id, @RequestBody Attachment attachment){
        ApiResponse apiResponse = attachmentService.editAttachment(id, attachment);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    public ResponseEntity<?> deleteAttachment(@PathVariable Integer id){
        ApiResponse apiResponse = attachmentService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
