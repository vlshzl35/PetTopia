package com.sh.pettopia.choipetsitter.repository;

import com.sh.pettopia.choipetsitter.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,String > {
    Order findByPartnerOrderId(String partnerOrderId);

    Order deleteOrderByPartnerOrderId(String partnerOrderId);
}
