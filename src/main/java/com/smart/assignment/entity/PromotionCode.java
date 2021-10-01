package com.smart.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author osamah kanaan <osamah.kan3an@gmail.com>
 * Created on Sep 30, 2021
 */
@Entity
@Table(name = "PROMOTION_CODES")
public class PromotionCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "IdOrGenerated")
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "CODE")
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
