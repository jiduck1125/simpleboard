package com.jiduck.simpleboard.dto;

import com.jiduck.simpleboard.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostDto {

    private Long id;

    private MemberDto member;

    private String title;

    private String content;

    private Long viewCount;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public PostDto(Post post) {
        id = post.getId();
        title = post.getTitle();
        content = post.getContent();
        viewCount = post.getViewCount();
        createDate = post.getCreateDate();
        updateDate = post.getUpdateDate();
        member = new MemberDto(post.getMember());
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }

    public LocalDateTime getWriteDate() {
        return updateDate != null ? updateDate : createDate;
    }

}
