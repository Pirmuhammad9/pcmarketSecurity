package com.company.lesson3task1.service;

import com.company.lesson3task1.entity.Attachment;
import com.company.lesson3task1.entity.AttachmentContent;
import com.company.lesson3task1.payload.ApiResponse;
import com.company.lesson3task1.payload.AttachmentContentDto;
import com.company.lesson3task1.repository.AttachmentContentRepository;
import com.company.lesson3task1.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentContentService {
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    public List<AttachmentContent> getAll(){
        return attachmentContentRepository.findAll();
    }
    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id){
        Optional<AttachmentContent> byId = attachmentContentRepository.findById(id);
        return byId.isPresent()?new ApiResponse("found", true):new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     * @param attachmentContentDto
     * @return ResponseEntity
     */
    public ApiResponse addAttachmentContent(AttachmentContentDto attachmentContentDto){
        boolean b = attachmentRepository.existsByName(attachmentContentDto.getName());
        if (b){
            return new ApiResponse("try again", false);
        }
        Attachment attachment1 = new Attachment();
        attachment1.setContentType(attachmentContentDto.getContentType());
        attachment1.setName(attachmentContentDto.getName());
        attachment1.setSize(attachmentContentDto.getSize());
        attachmentRepository.save(attachment1);
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setAttachment(attachment1);
        attachmentContent.setBytes(attachmentContentDto.getBytes());
        attachmentContentRepository.save(attachmentContent);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     * @param attachmentContentDto
     * @return ResponseEntity
     */
    public ApiResponse editAttachment(Integer id, AttachmentContentDto attachmentContentDto){
        Optional<AttachmentContent> byId = attachmentContentRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("try again", false);
        }
        Attachment attachment1 = new Attachment();
        attachment1.setContentType(attachmentContentDto.getContentType());
        attachment1.setName(attachmentContentDto.getName());
        attachment1.setSize(attachmentContentDto.getSize());
        attachmentRepository.save(attachment1);
        AttachmentContent attachmentContent = byId.get();
        attachmentContent.setAttachment(attachment1);
        attachmentContent.setBytes(attachmentContentDto.getBytes());
        attachmentContentRepository.save(attachmentContent);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteAttachmentContent(Integer id){
        if (attachmentContentRepository.existsById(id)){
            attachmentContentRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }
}
