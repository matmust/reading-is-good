package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.converter.BookDtoToModelConverter;
import com.getir.readingisgood.dto.BookDto;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.model.Response;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.service.BookService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  private final BookDtoToModelConverter bookDtoToModelConverter;

  @Override
  public List<Book> findAll(){
    return bookRepository.findAll();
  }

  @Override
  public Response create(BookDto bookDto) {
    Response response = new Response();

    String title = bookDto.getTitle();
    String author = bookDto.getAuthor();
    Book book = bookRepository.findByTitleAndAuthor(title,author).orElse(null);
    if (book != null) {
      log.info("Book is exists with this title: {} and author: {}", title,author);
      response.setMessage("Book is exists with this title and author!");
      response.setSuccess(false);
    } else {
      response.setMessage("Book has been successfully saved...");
      response.setSuccess(true);
      bookRepository.saveAndFlush(Objects.requireNonNull(bookDtoToModelConverter.convert(bookDto)));
      log.info("Book has been successfully saved...");
    }
    return response;
  }

  @Override
  public Response update(BookDto bookDto) {
    Response response = new Response();

    String title = bookDto.getTitle();
    String author = bookDto.getAuthor();
    Book book = bookRepository.findByTitleAndAuthor(title,author).orElse(null);
    if (book != null) {
      book.setPrice(bookDto.getPrice());
      book.setQuantity(bookDto.getQuantity());
      bookRepository.saveAndFlush(Objects.requireNonNull(book));
      response.setMessage("Book has been successfully updated...");
      response.setSuccess(true);
      log.info("Book has been successfully updated...");

    } else {
      log.info("Book does not exists with this title: {} and author: {}", title,author);
      response.setMessage("Book does not exists with this title and author!");
      response.setSuccess(false);
    }
    return response;
  }
}
