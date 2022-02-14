package com.getir.readingisgood.converter;

import com.getir.readingisgood.dto.OrderDto;
import com.getir.readingisgood.entity.Order;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoToModelConverter  implements Converter<OrderDto, Order> {

  @Override
  public Order convert(OrderDto orderDto) {
    Order order = new Order();
    order.setBookId(orderDto.getBookId());
    order.setCustomerId(orderDto.getCustomerId());
    order.setCount(orderDto.getCount());
    order.setActive(true);
    order.setCreatedDate(new Date());

    return order;
  }
}
