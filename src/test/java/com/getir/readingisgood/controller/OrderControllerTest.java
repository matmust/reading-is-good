package com.getir.readingisgood.controller;


import com.getir.readingisgood.config.security.SecurityProperties;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.model.OrderDetail;
import com.getir.readingisgood.service.OrderService;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private OrderService orderService;

  @Autowired
  private SecurityProperties securityProperties;

  @Test
  public void testGetById() throws Exception {
    OrderDetail orderDetail = new OrderDetail();
    Order order = new Order(1L,new Date(),1L, 1L,1,true);
    Customer customer = new Customer(1L,"test","test@gmail.com",new Date());
    Book book = new Book(2L,"test","test",1.0,1,new Date());
    orderDetail.setOrder(order);
    orderDetail.setCustomer(customer);
    orderDetail.setBook(book);

    given(orderService.getOrderDetailById(1L)).willReturn(orderDetail);

    mvc.perform(get("/api/order/{id}", 1).with(csrf()).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.order").exists());

  }

}
