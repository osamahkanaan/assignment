package com.smart.assignment.controller;

import com.smart.assignment.entity.Book;
import com.smart.assignment.repository.BookRepository;
import com.smart.assignment.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author osamah kanaan <osamah.kan3an@gmail.com>
 * Created on Sep 30, 2021
 */
@RequestMapping("/books")
@RestController
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookService bookService;
    
    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<?> list(
            @RequestParam(name = "search", required = false) String searchQuery) {
        
        if (searchQuery == null || searchQuery.isEmpty())
            return new ResponseEntity(
                    bookRepository.findAll(), HttpStatus.OK);
        else
            return new ResponseEntity(
                    bookRepository.findByNameIsContaining(searchQuery), HttpStatus.OK);
    }
    
    @GetMapping(value = "/page")
    @ResponseBody
    public ResponseEntity<?> page(
            @RequestParam(name = "search", required = false) String searchQuery,
            Pageable pageable) {
        
        if (searchQuery == null || searchQuery.isEmpty())
            return new ResponseEntity(
                    bookRepository.findAll(pageable), HttpStatus.OK);
        else
            return new ResponseEntity(
                    bookRepository.findByNameIsContaining(searchQuery, pageable), HttpStatus.OK);
    }
    
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> get(
            @PathVariable("id") Book book) {
        
        return new ResponseEntity(book, HttpStatus.OK);
    }
    
    @PostMapping(value = "/create")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> create(
            @RequestBody Book entity) {
        if (entity.getId() != null) {
            return new ResponseEntity<>(
                    "Id must be null", HttpStatus.BAD_REQUEST);
        }
        else{
            entity = bookRepository.save(entity);
            return new ResponseEntity<>(entity, HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> delete(
            @PathVariable("id") Long entityId) {
        
        bookRepository.deleteById(entityId);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }
    
    
    @PutMapping(value = "/update/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> update(
            @RequestBody Book entity,
            @PathVariable("id") Long entityId){
        
        Book existingBook = bookRepository.findById(entityId).get();
        bookRepository.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/checkout",
            method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseEntity<?> checkOut(
            @RequestParam(name = "promotionCode", required = false) String promotionCode,
            @RequestBody List<Long> bookIds) {
        
        return new ResponseEntity<>(bookService.checkOut(bookIds, promotionCode), HttpStatus.OK);
    }
}
