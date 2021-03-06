package com.ioxmentor.entity;

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
}
