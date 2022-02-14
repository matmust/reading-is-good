package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.CustomerDto;
import com.getir.readingisgood.model.Response;
import com.getir.readingisgood.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping(value = "/api/customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @PostMapping
  public ResponseEntity<String> create(@RequestBody CustomerDto customerDto) {

    try {
     Response response = customerService.create(customerDto);
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
}
