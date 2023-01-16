package com.jiduck.simpleboard.dto;

import com.jiduck.simpleboard.domain.Attachment;
import com.jiduck.simpleboard.domain.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttachmentDto {

    private Long id;

    private Post post;

    private String originalFilename;

    private String storedFilename;

    private String extension;

    private Long size;

    private String contentType;

    private LocalDateTime createDate;

    public Attachment toEntity() {
        return Attachment.builder()
                .post(post)
                .originalFilename(originalFilename)
                .storedFilename(storedFilename)
                .extension(extension)
                .size(size)
                .contentType(contentType)
                .createDate(createDate)
                .build();
    }
}
