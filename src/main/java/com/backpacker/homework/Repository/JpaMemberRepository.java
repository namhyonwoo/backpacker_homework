package com.backpacker.homework.Repository;

import com.backpacker.homework.controller.dto.MemberResponseDto;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;


public class JpaMemberRepository {

    //spring이 자동으로 만들어준다 jpa 디펜더시 추가하면
    private final EntityManager em;

    @Autowired
    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    public Page<MemberResponseDto> findAllWithOrder(Pageable pageable){
        Query nativeQuery  = em.createNativeQuery("SELECT member.*, RECENT_ORDERS.uid orderUid, RECENT_ORDERS.order_number orderNumber, RECENT_ORDERS.owner_uid orderOwnerUid, RECENT_ORDERS.product_name orderProductName, RECENT_ORDERS.created_at orderCreatedAt FROM member LEFT OUTER JOIN (SELECT * FROM orders WHERE (owner_uid, created_at) IN (SELECT owner_uid, MAX(created_at) AS created_at FROM orders GROUP BY owner_uid)) AS RECENT_ORDERS ON member.uid = RECENT_ORDERS.owner_uid");

        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<MemberResponseDto> result = jpaResultMapper.list(nativeQuery, MemberResponseDto.class);

        Long count = ((BigInteger)em.createNativeQuery("SELECT COUNT(*) FROM member LEFT OUTER JOIN (SELECT * FROM orders WHERE (owner_uid, created_at) IN (SELECT owner_uid, MAX(created_at) AS created_at FROM orders GROUP BY owner_uid)) AS RECENT_ORDERS ON member.uid = RECENT_ORDERS.owner_uid").getSingleResult()).longValue();

        return new PageImpl<MemberResponseDto>(result, pageable, count);
    }

}