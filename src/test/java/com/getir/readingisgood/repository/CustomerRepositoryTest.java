package com.getir.readingisgood.repository;


import com.getir.readingisgood.entity.Customer;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  public void should_create_customer(){
    Customer customer = new Customer(1L,"test","test@gmail.com",new Date());
    customerRepository.save(customer);
    Customer createdCustomer = customerRepository.findById(1L).orElse(null);

    assertThat(createdCustomer).isNotNull();

  }
}
