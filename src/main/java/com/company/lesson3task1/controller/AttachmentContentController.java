package com.company.lesson3task1.controller;

import com.company.lesson3task1.entity.Attachment;
import com.company.lesson3task1.payload.ApiResponse;
import com.company.lesson3task1.payload.AttachmentContentDto;
import com.company.lesson3task1.service.AttachmentContentService;
import com.company.lesson3task1.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attachmentContent")
public class AttachmentContentController {
    @Autowired
    AttachmentContentService attachmentContentService;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    @GetMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN', 'OPERATOR')")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(attachmentContentService.getAll());
    }

    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN', 'OPERATOR')")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Integer id){
        ApiResponse response = attachmentContentService.getOne(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    /**
     * This function is used to add new address into database
     * @param attachmentContentDto
     * @return ResponseEntity
     */
    @PostMapping
    @PreAuthorize(value = "hasAnyRole('MODERATOR','SUPER_ADMIN', 'OPERATOR')")
    public ResponseEntity<?> addAtatchmentContent(@RequestBody AttachmentContentDto attachmentContentDto){
        ApiResponse apiResponse = attachmentContentService.addAttachmentContent(attachmentContentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    /**
     * This function is used to edit address by id;
     * @param attachmentContentDto
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('MODERATOR','SUPER_ADMIN')")
    public ResponseEntity<?> editAttachmentContent(@PathVariable Integer id, @RequestBody AttachmentContentDto attachmentContentDto){
        ApiResponse apiResponse = attachmentContentService.editAttachment(id, attachmentContentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    public ResponseEntity<?> deleteAttachmentContent(@PathVariable Integer id){
        ApiResponse apiResponse = attachmentContentService.deleteAttachmentContent(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
