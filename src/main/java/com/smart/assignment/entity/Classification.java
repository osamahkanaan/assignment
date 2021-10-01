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
@Table(name = "CLASSIFICATIONS")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "IdOrGenerated")
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "DISCOUNT")
    private Double discount;

    public Classification() {
    }
    
    public Classification(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
