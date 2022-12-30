package com.jiduck.simpleboard.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, length = 20, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, length = 20)
    private String nickname;

    private String image_url;

    private String introduce;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @Builder
    public Member(String username, String password, String email, String nickname, String image_url, String introduce, LocalDateTime createDate, LocalDateTime updateDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.image_url = image_url;
        this.introduce = introduce;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
