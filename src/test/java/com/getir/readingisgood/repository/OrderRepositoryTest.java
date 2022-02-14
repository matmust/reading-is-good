package com.getir.readingisgood.repository;


import com.getir.readingisgood.entity.Order;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

  @Autowired
  private OrderRepository orderRepository;

  @Test
  public void should_find_all_by_customer_id(){
    Order order = new Order(1L,new Date(),1L,1L,1,true);

    orderRepository.save(order);

    List<Order> orderList = orderRepository.findAllByCustomerId(1L);

    assertThat(orderList).hasSize(1);
  }
}
