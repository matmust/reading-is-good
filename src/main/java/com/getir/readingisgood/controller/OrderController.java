package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.OrderDto;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.model.OrderDetail;
import com.getir.readingisgood.model.Response;
import com.getir.readingisgood.service.OrderService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping(value = "/api/order")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<OrderDetail> getById(@PathVariable Long id){
    OrderDetail orderDetail = orderService.getOrderDetailById(id) ;
    return new ResponseEntity<>(orderDetail,HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> create(@RequestBody OrderDto orderDto) {

    try {
      Response response = orderService.create(orderDto);
      if(response.isSuccess()){
        return new ResponseEntity<>(response.getMessage(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/get-all-by-customer-id")
  public ResponseEntity<List<Order>> getAllByCustomerId(@RequestParam Long customerId){
    List<Order> orderList = orderService.getAllOrdersByCustomerId(customerId);
    return new ResponseEntity<>(orderList,HttpStatus.OK);
  }

  @GetMapping(value = "/get-all-by-created-date-between")
  public ResponseEntity<List<Order>> getAllByCreatedDateBetween(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end){
    List<Order> orderList = orderService.getAllByCreatedDateBetween(start,end);
    return new ResponseEntity<>(orderList,HttpStatus.OK);
  }

}
