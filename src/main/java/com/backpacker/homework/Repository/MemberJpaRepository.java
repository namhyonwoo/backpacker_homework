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

public class MemberJpaRepository {

    private final EntityManager em;

    @Autowired
    public MemberJpaRepository(EntityManager em) {
        this.em = em;
    }

    public Page<MemberResponseDto> findAllWithOrder(Pageable pageable, String filterName, String filterValue) {

        String defaultQuery = "SELECT member.*, RECENT_ORDERS.uid orderUid, RECENT_ORDERS.order_number orderNumber, RECENT_ORDERS.owner_uid orderOwnerUid, RECENT_ORDERS.product_name orderProductName, RECENT_ORDERS.created_at orderCreatedAt FROM member LEFT OUTER JOIN (SELECT * FROM orders WHERE (owner_uid, created_at) IN (SELECT owner_uid, MAX(created_at) AS created_at FROM orders GROUP BY owner_uid)) AS RECENT_ORDERS ON member.uid = RECENT_ORDERS.owner_uid";
        String conditionQuery = " WHERE member.:filterName = ':filterValue'";
        String limitQuery = " LIMIT :offset, :size";
        String query = "";

        if (filterName != null && (filterName.equals("email") || filterName.equals("name"))) {
            conditionQuery = conditionQuery.replace(":filterName", filterName).replace(":filterValue", filterValue);
            query = defaultQuery + conditionQuery + limitQuery;
        } else {
            query = defaultQuery + limitQuery;
        }

        Query nativeQuery = em.createNativeQuery(query)
                .setParameter("offset", (int) pageable.getOffset())
                .setParameter("size", pageable.getPageSize());

        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<MemberResponseDto> result = jpaResultMapper.list(nativeQuery, MemberResponseDto.class);

        Long count = ((BigInteger) em.createNativeQuery("SELECT COUNT(*) FROM member LEFT OUTER JOIN (SELECT * FROM orders WHERE (owner_uid, created_at) IN (SELECT owner_uid, MAX(created_at) AS created_at FROM orders GROUP BY owner_uid)) AS RECENT_ORDERS ON member.uid = RECENT_ORDERS.owner_uid").getSingleResult()).longValue();

        return new PageImpl<MemberResponseDto>(result, pageable, count);
    }

}
