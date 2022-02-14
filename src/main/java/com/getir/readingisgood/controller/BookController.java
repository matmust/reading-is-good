package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.BookDto;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.model.Response;
import com.getir.readingisgood.service.BookService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping(value = "/api/book")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping
  public ResponseEntity<List<Book>> findAll(){
    try {
      List<Book> bookList = bookService.findAll();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping
  public ResponseEntity<String> create(@RequestBody BookDto bookDto ) {

    try {
      Response response = bookService.create(bookDto);
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

  @PutMapping
  public ResponseEntity<String> update(@RequestBody BookDto bookDto ) {

    try {
      Response response = bookService.update(bookDto);
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
