package com.sh.pettopia.choipetsitter.repository;

import com.sh.pettopia.choipetsitter.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,String > {
    Order findOrderByPartnerOrderId(String partnerOrderId);

    Order deleteOrderByPartnerOrderId(String partnerOrderId);

    List<Order> findAllByOrderByPayDate();
}
