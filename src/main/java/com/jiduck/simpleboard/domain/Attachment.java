package com.jiduck.simpleboard.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String originalFilename;

    private String storedFilename;

    private String extension;

    private Long size;

    private String contentType;

    private LocalDateTime createDate;

    @Builder
    public Attachment(Post post, String originalFilename, String storedFilename, String extension, Long size, String contentType, LocalDateTime createDate) {
        this.post = post;
        this.originalFilename = originalFilename;
        this.storedFilename = storedFilename;
        this.extension = extension;
        this.size = size;
        this.contentType = contentType;
        this.createDate = createDate;
    }
}
