package com.backpacker.homework.service;

import com.backpacker.homework.controller.dto.MemberSaveDto;
import com.backpacker.homework.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;


    @Test
    public void 회원가입() {
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

    @Test
    public void 로그인() {
        //given
        MemberSaveDto memberSaveDto = MemberSaveDto.builder()
                .name("name")
                .nickname("cuya")
                .email("avvc!s@abc.com")
                .gender("M")
                .phone("01099998888")
                .password("password!@#")
                .build();

        //when
        //then
        assertThatCode(() -> memberService.join(memberSaveDto))
                .doesNotThrowAnyException();


    }

}
