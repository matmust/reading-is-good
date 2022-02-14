package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

  Optional<Book> findById(Long id);

  Optional<Book> findByTitleAndAuthor(String title,String author);

}
