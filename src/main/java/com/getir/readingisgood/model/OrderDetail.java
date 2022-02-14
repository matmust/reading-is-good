package com.getir.readingisgood.model;

import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.entity.Order;
import lombok.Data;

@Data
public class OrderDetail {
  private com.getir.readingisgood.entity.Order Order;
  private Customer customer;
  private Book book;
}
