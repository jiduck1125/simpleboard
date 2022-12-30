package com.jiduck.simpleboard.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MemberForm {

    @NotBlank(message = "아이디는 필수 입력입니다.")
    @Length(min = 3, max = 20, message = "아이디는 3자 이상 20자 이하로 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Length(min = 3, max = 20, message = "비밀번호는 3자 이상 20자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String email;

    @NotBlank(message = "닉네임은 필수 입력입니다.")
    @Length(min = 3, max = 20, message = "닉네임은 3자 이상 20자 이하로 입력해주세요.")
    private String nickname;
}
