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

    private String imageUrl;

    private String introduce;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .nickname(nickname)
                .imageUrl(imageUrl)
                .introduce(introduce)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}
