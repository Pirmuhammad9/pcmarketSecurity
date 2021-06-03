package com.company.lesson3task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentContentDto {
    private Integer Id;
    private byte[] bytes;
    private String name;
    private Double size;
    private String contentType;
}
