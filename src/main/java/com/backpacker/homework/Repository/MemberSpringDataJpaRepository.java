package com.backpacker.homework.Repository;

import com.backpacker.homework.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MemberSpringDataJpaRepository extends JpaRepository<Member, Long>, MemberRepository{

}
