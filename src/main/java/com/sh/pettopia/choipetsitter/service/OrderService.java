package com.sh.pettopia.choipetsitter.service;

import com.sh.pettopia.choipetsitter.entity.Order;
import com.sh.pettopia.choipetsitter.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order findOrderByPartnerOrderId(String partnerOrderId) {
        return orderRepository.findOrderByPartnerOrderId(partnerOrderId);
    }

    public List<Order> findAll()
    {
        return orderRepository.findAll();
    }
    public List<Order> findAllByOrderByPayDate(){
        return orderRepository.findAllByOrderByPayDate();
    }
}
