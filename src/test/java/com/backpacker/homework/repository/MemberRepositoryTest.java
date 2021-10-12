package com.backpacker.homework.repository;

import com.backpacker.homework.Repository.MemberRepository;
import com.backpacker.homework.controller.dto.MemberSaveDto;
import com.backpacker.homework.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 모든_회원_조회(){
        //given
        List<Member> memberList = memberRepository.findAll();
        int count = memberList.size();

        MemberSaveDto memberDto =MemberSaveDto.builder()
                .name("name")
                .nickname("cuya")
                .email("abc11123123@abc.com")
                .gender("M")
                .phone("01099998888")
                .password("password!@#").build();
        memberRepository.save(memberDto.toEntity());

        //when
        List<Member> afterInsertMemberList = memberRepository.findAll();

        //then
        assertThat(count+1).isEqualTo(afterInsertMemberList.size());
    }
}
