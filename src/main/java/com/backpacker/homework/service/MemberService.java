package com.backpacker.homework.service;

import com.backpacker.homework.Repository.JpaMemberRepository;
import com.backpacker.homework.Repository.MemberRepository;
import com.backpacker.homework.controller.dto.MemberResponseDto;
import com.backpacker.homework.controller.dto.MemberSaveDto;
import com.backpacker.homework.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final JpaMemberRepository jpaMemberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, JpaMemberRepository jpaMemberRepository) {
        this.memberRepository = memberRepository;
        this.jpaMemberRepository = jpaMemberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(MemberSaveDto memberSaveDto){
        validateDuplicateMember(memberSaveDto);
        return memberRepository.save(memberSaveDto.toEntity()).getUid();
    }

    /**
     * 회원 조회
     */
    public Member findMember(Long uid){
        Member member = memberRepository.findById(uid)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. uid=" + uid));
        return member;
    }

    /**
     * 전체 회원 조회
     */
    public Page<MemberResponseDto> findMembers(Pageable pageable, String filterName, String filterValue) {

            return jpaMemberRepository.findAllWithOrder(pageable, filterName, filterValue);

    }


    private void validateDuplicateMember(MemberSaveDto memberSaveDto) {
        memberRepository.findByEmail(memberSaveDto.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });
    }
}
