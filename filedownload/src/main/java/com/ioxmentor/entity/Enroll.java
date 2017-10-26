package com.ioxmentor.entity;

import com.ioxmentor.enums.CourseType;
import com.ioxmentor.enums.PaymentStatus;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by nabeelabdullah on 09/10/17.
 */
@Entity
public class Enroll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long courseId;

    @Column
    private Long userId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column
    private Float amountPaid;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp paidAt;

    @Column
    private Boolean couponApplied;

    @Column
    private String coupon;

    @Column
    private Float actualPrice;

    @Column
    private Float ammountToBePaid;

    @Column
    private Float discount;

    @Column
    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Timestamp paidAt) {
        this.paidAt = paidAt;
    }

    public Boolean getCouponApplied() {
        return couponApplied;
    }

    public void setCouponApplied(Boolean couponApplied) {
        this.couponApplied = couponApplied;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public Float getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Float actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Float getAmmountToBePaid() {
        return ammountToBePaid;
    }

    public void setAmmountToBePaid(Float ammountToBePaid) {
        this.ammountToBePaid = ammountToBePaid;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }
}
