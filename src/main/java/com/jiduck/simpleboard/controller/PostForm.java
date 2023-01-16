package com.jiduck.simpleboard.controller;

import com.jiduck.simpleboard.dto.AttachmentDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostForm {

    private Long id;

    private Long memberId;

    @NotBlank(message = "제목은 필수 입력입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력입니다.")
    private String content;

    private String hashtag;

    private List<MultipartFile> attachedFiles = new ArrayList<>();

}
