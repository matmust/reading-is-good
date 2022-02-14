package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.OrderDto;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.model.OrderDetail;
import com.getir.readingisgood.model.Response;
import java.util.Date;
import java.util.List;

public interface OrderService {
  Response create(OrderDto orderDto);

  List<Order> getAllOrdersByCustomerId(Long customerId);

  List<Order> getAllByCreatedDateBetween(Date start, Date end);

  OrderDetail getOrderDetailById(Long orderId);

}
