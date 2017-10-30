package com.codingsuperstar.codingsuperstar.entity;

import com.codingsuperstar.codingsuperstar.enums.AmountType;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by nabeelabdullah on 22/10/17.
 */
@Entity
public class Coupon {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(unique = true)
    private String coupon;

    private String course;

    private Timestamp expAt;

    private Integer maxUsable;

    private Float amountOff;

    @Enumerated(EnumType.STRING)
    private AmountType amountType;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Timestamp getExpAt() {
        return expAt;
    }

    public void setExpAt(Timestamp expAt) {
        this.expAt = expAt;
    }

    public Integer getMaxUsable() {
        return maxUsable;
    }

    public void setMaxUsable(Integer maxUsable) {
        this.maxUsable = maxUsable;
    }

    public Float getAmountOff() {
        return amountOff;
    }

    public void setAmountOff(Float amountOff) {
        this.amountOff = amountOff;
    }

    public AmountType getAmountType() {
        return amountType;
    }

    public void setAmountType(AmountType amountType) {
        this.amountType = amountType;
    }
}
