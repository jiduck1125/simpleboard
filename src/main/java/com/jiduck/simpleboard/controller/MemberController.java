package com.jiduck.simpleboard.controller;

import com.jiduck.simpleboard.dto.MemberDto;
import com.jiduck.simpleboard.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "signup";
        }

        MemberDto memberDto = new MemberDto();
        memberDto.setUsername(form.getUsername());
        memberDto.setPassword(form.getPassword());
        memberDto.setEmail(form.getEmail());
        memberDto.setNickname(form.getNickname());

        try {
            memberService.createMember(memberDto);
        } catch (IllegalStateException e) {
            result.addError(new ObjectError("globalError", e.getMessage()));
            return "signup";
        }
        return "redirect:/";
    }
}
