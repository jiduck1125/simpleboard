package com.jiduck.simpleboard.service;

import com.jiduck.simpleboard.domain.Attachment;
import com.jiduck.simpleboard.domain.Post;
import com.jiduck.simpleboard.repository.AttachmentRepository;
import com.jiduck.simpleboard.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttachmentService {
    
    private final AttachmentRepository attachmentRepository;
    private final PostRepository postRepository;

    @Value("${attachment.upload-dir}")
    private String uploadDir;
    

    @Transactional
    public Long createAttachment(Long postId, MultipartFile multipartFile) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 존재하지 않습니다. id = " + postId));

        String originalFilename = multipartFile.getOriginalFilename();
        String storedFilename = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        Long attachmentId = null;
        try {
            saveFile(uploadDir, storedFilename, multipartFile);
            Attachment attachment = Attachment.builder()
                    .post(post)
                    .originalFilename(multipartFile.getOriginalFilename())
                    .storedFilename(storedFilename)
                    .extension(extension)
                    .size(multipartFile.getSize())
                    .contentType(multipartFile.getContentType())
                    .createDate(LocalDateTime.now())
                    .build();

            attachmentRepository.save(attachment);
            attachmentId = attachment.getId();

        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return attachmentId;
    }

    private void saveFile(String uploadDir, String saveFilename, MultipartFile multipartFile) throws IOException {

        File saveDir = new File(uploadDir);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        File saveFile = new File(saveDir, saveFilename);
        multipartFile.transferTo(saveFile);
    }
}
