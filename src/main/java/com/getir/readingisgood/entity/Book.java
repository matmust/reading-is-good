package com.getir.readingisgood.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String author;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private Double price;

  @Column(nullable = false)
  private int quantity;

  @Column(nullable = false)
  private Date createdDate;
}
