package com.getir.readingisgood.service;

import com.getir.readingisgood.converter.CustomerModelToDtoConverter;
import com.getir.readingisgood.entity.Customer;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
  @Mock
  private CustomerService customerService;

  @Mock
  private CustomerModelToDtoConverter customerModelToDtoConverter;

  @Test
  public void testCreate() {
    Customer customer = new Customer(1L, "test", "test@gmail.com", new Date());

    customerService.create(customerModelToDtoConverter.convert(customer));

    verify(customerService).create(customerModelToDtoConverter.convert(customer));
  }

}
