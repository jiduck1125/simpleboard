package com.jiduck.simpleboard.dto;

import com.jiduck.simpleboard.domain.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String nickname;

    private String image_url;

    private String introduce;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .nickname(nickname)
                .image_url(image_url)
                .introduce(introduce)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}
