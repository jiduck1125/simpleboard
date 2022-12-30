package com.jiduck.simpleboard.service;

import com.jiduck.simpleboard.domain.Member;
import com.jiduck.simpleboard.dto.MemberDto;
import com.jiduck.simpleboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long createMember(MemberDto memberDto) {

        validateDuplicateMember(memberDto);
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberDto.setCreateDate(LocalDateTime.now());
        Member member = memberRepository.save(memberDto.toEntity());
        return member.getId();
    }

    private void validateDuplicateMember(MemberDto memberDto) {
        Long usernameCount = memberRepository.countByUsername(memberDto.getUsername());
        if (usernameCount != 0) {
            throw new IllegalStateException("사용중인 아이디 입니다.");
        }

        Long emailCount = memberRepository.countByEmail(memberDto.getEmail());
        if (emailCount != 0) {
            throw new IllegalStateException("사용중인 이메일 입니다.");
        }

        Long nicknameCount = memberRepository.countByNickname(memberDto.getNickname());
        if (nicknameCount != 0) {
            throw new IllegalStateException("사용중인 닉네임 입니다.");
        }
    }
}
