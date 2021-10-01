package com.smart.assignment.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author osamah kanaan <osamah.kan3an@gmail.com>
 * Created on Oct 1, 2021
 */
@Entity
@Table(name = "BOOKS_PROMOTION_CODES")
@IdClass(CompositeKey.class)
public class BookPromotionCode {
    
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Book book;
    
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private PromotionCode promotionCode;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public PromotionCode getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(PromotionCode promotionCode) {
        this.promotionCode = promotionCode;
    }
}
