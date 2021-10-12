package com.backpacker.homework.service;

import com.backpacker.homework.Repository.MemberRepository;
import com.backpacker.homework.controller.dto.MemberSaveDto;
import com.backpacker.homework.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional //자동롤백
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void 회원가입(){
        //given
        MemberSaveDto memberSaveDto = MemberSaveDto.builder()
                .name("name")
                .nickname("cuya")
                .email("abc11123123@abc.com")
                .gender("M")
                .phone("01099998888")
                .password("password!@#")
                .build();

        //when
        Long saveId = memberService.join(memberSaveDto);

        //then
        Member findMember = memberService.findMember(saveId);
        assertThat(memberSaveDto.getName()).isEqualTo(findMember.getName());

    }
}
