package com.getir.readingisgood.converter;

import com.getir.readingisgood.dto.CustomerDto;
import com.getir.readingisgood.entity.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelToDtoConverter implements Converter<Customer,CustomerDto> {

  @Override
  public CustomerDto convert(Customer customer) {
    CustomerDto customerDto = new CustomerDto();

    customerDto.setUsername(customer.getUserName());
    customerDto.setEmail(customer.getEmail());

    return customerDto;
  }
}
