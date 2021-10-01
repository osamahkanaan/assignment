package com.smart.assignment.service;

import com.smart.assignment.entity.Book;
import com.smart.assignment.entity.BookPromotionCode;
import com.smart.assignment.repository.BookPromotionCodeRepository;
import com.smart.assignment.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author osamah kanaan <osamah.kan3an@gmail.com>
 * Created on Oct 1, 2021
 */
@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookPromotionCodeRepository bookPromotionCodeRepository;
    
    public Double checkOut(List<Long> booksIds, String promotionCode){
        
        Double result =0.0;
        if (booksIds != null && !booksIds.isEmpty()){
            List<Book> books = bookRepository.findAllById(booksIds);
            for(Book book : books){
                if (promotionCode != null && !promotionCode.isEmpty()){
                    List<BookPromotionCode> promotions = bookPromotionCodeRepository.findByBookAndPromotionCode_Code(book, promotionCode);
                    if (promotions != null && !promotions.isEmpty()){
                        
                        result += (book.getPrice() * (100 - book.getClassification().getDiscount())/100);
                        continue;
                    }
                }
                result += book.getPrice();
            }
        }
        return result;
    }
    
    public Book getMaxId(){
        return bookRepository.findFirstByOrderByIdDesc();
    }
}
