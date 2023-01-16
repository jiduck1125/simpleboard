package com.jiduck.simpleboard.controller;

import com.jiduck.simpleboard.domain.Post;
import com.jiduck.simpleboard.dto.PostDto;
import com.jiduck.simpleboard.repository.PostRepository;
import com.jiduck.simpleboard.security.AuthenticationFacade;
import com.jiduck.simpleboard.service.AttachmentService;
import com.jiduck.simpleboard.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final AttachmentService attachmentService;
    private final PostRepository postRepository;

    private final AuthenticationFacade authenticationFacade;

    @GetMapping("/posts")
    public String list(Model model) {
        List<PostDto> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "post/list";
    }

    @GetMapping("/post")
    public String createForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "post/createForm";
    }

    @PostMapping("/post")
    public String create(@Valid PostForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "post/createForm";
        }

        PostDto postDto = new PostDto();
        postDto.setTitle(form.getTitle());
        postDto.setContent(form.getContent());

        Long memberId = authenticationFacade.getPrincipalDetails().getId();
        Long postId;

        try {
            postId = postService.createPost(memberId, postDto);

            for (MultipartFile attachedFile : form.getAttachedFiles()) {
                attachmentService.createAttachment(postId, attachedFile);
            }

        } catch (IllegalStateException e) {
            result.addError(new ObjectError("globalError", e.getMessage()));
            return "post/createForm";
        }
        return "redirect:/posts/" + postId;
    }

    @GetMapping("/posts/{postId}")
    public String detailForm(@PathVariable("postId") Long postId, Model model) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다. id = " + postId));

        PostForm form = new PostForm();
        form.setId(post.getId());
        form.setTitle(post.getTitle());
        form.setContent(post.getContent());

        model.addAttribute("form", form);
        return "post/detailForm";
    }

    @GetMapping("/posts/{postId}/update")
    public String updateForm(@PathVariable("postId") Long postId, Model model) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다. id = " + postId));

        PostForm form = new PostForm();
        form.setId(post.getId());
        form.setTitle(post.getTitle());
        form.setContent(post.getContent());

        model.addAttribute("postForm", form);
        return "post/updateForm";
    }

    @PostMapping("/posts/{postId}/update")
    public String update(@PathVariable("postId") Long postId, @Valid PostForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "post/updateForm";
        }

        PostDto postDto = new PostDto();
        postDto.setTitle(form.getTitle());
        postDto.setContent(form.getContent());
        postService.updatePost(postId, postDto);
        return "redirect:/posts/" + postId;
    }

}
