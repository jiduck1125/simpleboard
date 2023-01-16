package com.jiduck.simpleboard.dto;

import com.jiduck.simpleboard.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
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

    public MemberDto(Member member) {
        id = member.getId();
        username = member.getUsername();
        password = member.getPassword();
        email = member.getEmail();
        nickname = member.getNickname();
        imageUrl = member.getImageUrl();
        introduce = member.getIntroduce();
        createDate = member.getCreateDate();
        updateDate = member.getUpdateDate();
    }

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
