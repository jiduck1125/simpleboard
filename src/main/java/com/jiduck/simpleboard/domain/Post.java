package com.jiduck.simpleboard.domain;

import com.jiduck.simpleboard.dto.PostDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String content;

    private Long viewCount;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @Builder
    public Post(Member member, String title, String content, Long viewCount, LocalDateTime createDate, LocalDateTime updateDate) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static Post createPost(Member member, String title, String content) {
        return Post.builder()
                .member(member)
                .title(title)
                .content(content)
                .viewCount(0L)
                .createDate(LocalDateTime.now())
                .build();
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
        this.updateDate = LocalDateTime.now();
    }

}
