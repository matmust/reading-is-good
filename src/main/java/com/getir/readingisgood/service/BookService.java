package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.BookDto;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.model.Response;
import java.util.List;

public interface BookService {

  List<Book> findAll();

  Response create(BookDto bookDto);

  Response update(BookDto bookDto);
}
