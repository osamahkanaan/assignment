package com.smart.assignment.repository;

import com.smart.assignment.entity.Book;
import com.smart.assignment.entity.BookPromotionCode;
import com.smart.assignment.entity.PromotionCode;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author osamah kanaan <osamah.kan3an@gmail.com>
 * Created on Sep 30, 2021
 */
@Repository
public interface BookPromotionCodeRepository extends JpaRepository<BookPromotionCode, Long>{
    
    List<BookPromotionCode> findByBookAndPromotionCode_Code(Book book, String promotionCode);
}
