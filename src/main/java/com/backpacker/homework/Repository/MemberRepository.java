package com.backpacker.homework.Repository;

import com.backpacker.homework.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long uid);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailAndPassword(String email, String password);
    List<Member> findAll();
    Page<Member> findAll(Pageable pageable);
    Page<Member> findByEmail(String Email, Pageable pageable);
    Page<Member> findByName(String Name, Pageable pageable);
}
