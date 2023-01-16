package com.jiduck.simpleboard.service;

import com.jiduck.simpleboard.domain.Member;
import com.jiduck.simpleboard.domain.Post;
import com.jiduck.simpleboard.dto.PostDto;
import com.jiduck.simpleboard.repository.MemberRepository;
import com.jiduck.simpleboard.repository.PostRepository;
import com.jiduck.simpleboard.security.AuthenticationFacade;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    public Long createPost(Long memberId, PostDto postDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다. id = " + memberId));

        Post post = Post.createPost(member, postDto.getTitle(), postDto.getContent());
        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public void updatePost(Long postId, PostDto postDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 존재하지 않습니다. id = " + postId));

        post.updatePost(postDto.getTitle(), postDto.getContent());
    }

    public List<PostDto> findPosts() {
        return postRepository.findAllWithMember().stream()
                .map(post -> new PostDto(post))
                .collect(Collectors.toList());
    }

    public void checkAccessDeniedEx(Post post) {
        if (authenticationFacade.getPrincipalDetails().getId() != post.getMember().getId()) {
            String msg = "게시글 수정 권한이 없습니다. postId = " + post.getId() +
                    " loginMemberId = " + authenticationFacade.getPrincipalDetails().getId();
            throw new AccessDeniedException(msg);
        }
    }

}
