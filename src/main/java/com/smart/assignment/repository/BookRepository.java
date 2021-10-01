package com.smart.assignment.repository;

import com.smart.assignment.entity.Book;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author osamah kanaan <osamah.kan3an@gmail.com>
 * Created on Sep 30, 2021
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    
    List<Book> findByNameIsContaining(String name);
    Page<Book> findByNameIsContaining(String name, Pageable pageable);
    
    Book findFirstByOrderByIdDesc();
}
