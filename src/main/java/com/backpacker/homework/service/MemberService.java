package com.backpacker.homework.service;

import com.backpacker.homework.Repository.MemberJpaRepository;
import com.backpacker.homework.Repository.MemberRepository;
import com.backpacker.homework.controller.dto.MemberResponseDto;
import com.backpacker.homework.controller.dto.MemberSaveDto;
import com.backpacker.homework.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 가입
     */
    public Long join(MemberSaveDto memberSaveDto) {
        validateDuplicateMember(memberSaveDto);

        memberSaveDto.setPassword(passwordEncoder.encode(memberSaveDto.getPassword()));
        return memberRepository.save(memberSaveDto.toEntity()).getUid();
    }

    /**
     * 로그인
     */
    public Member login(String email, String password) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. email=" + email));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return member;
    }

    /**
     * 회원 조회
     */
    public Member findMember(Long uid) {
        Member member = memberRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. uid=" + uid));
        return member;
    }


    /**
     * 전체 회원 조회
     */
    public Page<MemberResponseDto> findMembers(Pageable pageable, String filterName, String filterValue) {
        //jpa를 사용하여 조회
        return memberJpaRepository.findAllWithOrder(pageable, filterName, filterValue);
    }


    private void validateDuplicateMember(MemberSaveDto memberSaveDto) {
        memberRepository.findByEmail(memberSaveDto.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });
    }
}
