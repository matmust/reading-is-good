package com.getir.readingisgood.converter;

import com.getir.readingisgood.dto.BookDto;
import com.getir.readingisgood.entity.Book;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookDtoToModelConverter  implements Converter<BookDto, Book> {

  @Override
  public Book convert(BookDto bookDto) {
    Book book = new Book();

    book.setAuthor(bookDto.getAuthor());
    book.setTitle(bookDto.getTitle());
    book.setPrice(bookDto.getPrice());
    book.setQuantity(bookDto.getQuantity());
    book.setCreatedDate(new Date());

    return book;
  }
}