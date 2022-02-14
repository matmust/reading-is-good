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
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date createdDate;

  @Column(nullable = false)
  private Long customerId;

  @Column(nullable = false)
  private Long bookId;

  @Column(nullable = false)
  private int count;

  private boolean active;

}
