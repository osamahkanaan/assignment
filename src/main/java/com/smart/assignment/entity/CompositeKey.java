package com.smart.assignment.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author osamah kanaan <osamah.kan3an@gmail.com>
 * Created on Oct 1, 2021
 */
public class CompositeKey implements Serializable {
    
    private Book book;
    
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeKey compositeKey = (CompositeKey) o;
        return getBook().getId().equals(compositeKey.getBook().getId()) &&
                getPromotionCode().getId().equals(compositeKey.getPromotionCode().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBook().getId(), getPromotionCode().getId());
    }
}
