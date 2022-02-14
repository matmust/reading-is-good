package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.converter.CustomerDtoToModelConverter;
import com.getir.readingisgood.dto.CustomerDto;
import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.model.Response;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.service.CustomerService;
import java.util.Objects;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  private final CustomerDtoToModelConverter customerDtoToModelConverter;

  @Override
  public Response create(CustomerDto customerDto) {
    Response response = new Response();

    String email = customerDto.getEmail();
    Customer customer = customerRepository.findByEmail(email).orElse(null);
    if (customer != null) {
      log.info("Customer is exists with this email: {}", email);
      response.setMessage("Customer is exists with this email!");
      response.setSuccess(false);
    } else {
      response.setMessage("Customer has been successfully saved...");
      response.setSuccess(true);
      customerRepository.saveAndFlush(Objects.requireNonNull(customerDtoToModelConverter.convert(customerDto)));
      log.info("Customer has been successfully saved...");
    }
    return response;
  }
}
