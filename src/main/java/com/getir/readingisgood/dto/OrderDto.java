package com.getir.readingisgood.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderDto {

  @NotNull
  @NotBlank(message = "Customer Id may not be blank")
  private Long customerId;

  @NotNull
  @NotBlank(message = "Book Id may not be blank")
  private Long bookId;

  @NotNull
  @NotBlank(message = "Book count may not be blank")
  private int count;
}