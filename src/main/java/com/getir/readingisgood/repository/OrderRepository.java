package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.Order;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

  List<Order> findAllByCustomerId(Long customerId);

  List<Order> findAllByCreatedDateBetween(Date start, Date end);
}
