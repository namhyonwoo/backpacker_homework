package com.backpacker.homework.Repository;

import com.backpacker.homework.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MemberSpringDataJpaRepository extends JpaRepository<Member, Long>, MemberRepository{

/*    @Query(
            value = "SELECT member.uid, member.name, RECENT_ORDERS.uid AS order_uid, RECENT_ORDERS.uid AS order_number " +
                    "FROM member " +
                    "LEFT OUTER JOIN " +
                    "(SELECT * " +
                    "FROM orders " +
                    "WHERE (owner_uid, created_at) IN " +
                    "(SELECT owner_uid, MAX(created_at) AS created_at FROM orders " +
                    "GROUP BY owner_uid)) AS RECENT_ORDERS " +
                    "ON member.uid = RECENT_ORDERS.owner_uid",
            countQuery = "SELECT COUNT(*) FROM member\n" +
                    "LEFT OUTER JOIN\n" +
                    "(SELECT * \n" +
                    "FROM orders\n" +
                    "WHERE (owner_uid, created_at) IN (\n" +
                    "\tSELECT owner_uid, MAX(created_at) AS created_at\n" +
                    "\tFROM orders\n" +
                    "\tGROUP BY owner_uid\n" +
                    ")) AS RECENT_ORDERS\n" +
                    "ON member.uid = RECENT_ORDERS.owner_uid",
            nativeQuery = true)
    Page<Member> findAllWithOrder(Pageable pageable);*/
}
