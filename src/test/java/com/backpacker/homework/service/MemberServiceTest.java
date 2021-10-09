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

/*    @Test
    @Commit
    public void 회원가입(){
        //given
        MemberSaveDto memberSaveDto = MemberSaveDto.builder()
                .name("name")
                .nickname("cuya")
                .email("abc@abc.com")
                .gender("M")
                .phone("01099998888")
                .password("password")
                .build();

        //when
        Long saveId = memberService.join(memberSaveDto);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(memberSaveDto.getName()).isEqualTo(findMember.getName());

    }*/

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
    }
}
