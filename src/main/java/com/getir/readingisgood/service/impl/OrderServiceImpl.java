package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.converter.OrderDtoToModelConverter;
import com.getir.readingisgood.dto.OrderDto;
import com.getir.readingisgood.entity.*;
import com.getir.readingisgood.model.OrderDetail;
import com.getir.readingisgood.model.Response;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.repository.OrderRepository;
import com.getir.readingisgood.service.OrderService;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.hibernate.StaleObjectStateException;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final BookRepository bookRepository;

  private final OrderRepository orderRepository;

  private final CustomerRepository customerRepository;

  private final OrderDtoToModelConverter orderDtoToModelConverter;

  @Override
  @Retryable(include = {ConcurrencyFailureException.class, StaleObjectStateException.class},
      backoff = @Backoff(multiplier = 3), maxAttempts = 5)
  public Response create(OrderDto orderDto) {
    Response response = new Response();

    Customer customer = customerRepository.findById(orderDto.getCustomerId()).orElse(null);

    if(customer == null){
      log.info("Customer does not exists, id: {}", orderDto.getCustomerId());
      response.setMessage("Customer does not exists.");
      response.setSuccess(false);
      return response;
    }

    Long bookId = orderDto.getBookId();
    Book book = bookRepository.findById(bookId).orElse(null);

    if (book != null) {
      if (book.getQuantity() >= orderDto.getCount()) {
        orderRepository.save(Objects.requireNonNull(orderDtoToModelConverter.convert(orderDto)));
        book.setQuantity(book.getQuantity() - orderDto.getCount());
        bookRepository.save(book);
        response.setSuccess(true);
        response.setMessage("Order has been successfully created...");
        log.info("Order has been successfully created...");

      } else {
        log.info("Book does not have enough quantity, id: {}", bookId);
        response.setMessage("Book does not have enough quantity.");
        response.setSuccess(false);
      }
    } else {
      log.info("Book does not exists, id ={}", bookId);
      response.setMessage("Book does not exists.");
      response.setSuccess(false);
    }
    return response;
  }

  @Override
  public List<Order> getAllOrdersByCustomerId(Long customerId){
    return orderRepository.findAllByCustomerId(customerId);
  }

  @Override
  public List<Order> getAllByCreatedDateBetween(Date start, Date end){
    return orderRepository.findAllByCreatedDateBetween(start,end);
  }

  @Override
  public OrderDetail getOrderDetailById(Long orderId){
    OrderDetail orderDetail = new OrderDetail();
    Order order =  orderRepository.findById(orderId).orElse(null);
    if (order != null){
      Book book = bookRepository.findById(order.getBookId()).orElse(null);
      Customer customer  = customerRepository.findById(order.getCustomerId()).orElse(null);

      orderDetail.setOrder(order);
      orderDetail.setBook(book);
      orderDetail.setCustomer(customer);
    } else {
      log.info("Order does not exists, id: {}",orderId);
    }
    return orderDetail;
  }
}
