package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.CustomerDto;
import com.getir.readingisgood.model.Response;

public interface CustomerService {
  Response create(CustomerDto customerDto);
}
