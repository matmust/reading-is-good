package com.getir.readingisgood.converter;

import com.getir.readingisgood.dto.CustomerDto;
import com.getir.readingisgood.entity.Customer;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoToModelConverter implements Converter<CustomerDto, Customer> {

  @Override
  public Customer convert(CustomerDto customerDto) {
    Customer customer = new Customer();

    customer.setUserName(customerDto.getUsername());
    customer.setEmail(customerDto.getEmail());
    customer.setCreatedDate(new Date());

    return customer;
  }
}
