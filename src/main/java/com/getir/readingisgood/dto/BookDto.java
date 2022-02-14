package com.getir.readingisgood.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookDto {
  @NotNull
  private String author;
  @NotNull
  private String title;
  @NotNull
  private Double price;
  @NotNull
  private int quantity;
}