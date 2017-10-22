package com.ioxmentor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by nabeelabdullah on 21/10/17.
 */
@Entity
public class Course {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String courseTitle;

    private Integer durationInMinuts;

    private Float basePrice;

    private Short isActive = 0;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getDurationInMinuts() {
        return durationInMinuts;
    }

    public void setDurationInMinuts(Integer durationInMinuts) {
        this.durationInMinuts = durationInMinuts;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }

    public Short getIsActive() {
        return isActive;
    }

    public void setIsActive(Short isActive) {
        this.isActive = isActive;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    @Override
    public String toString() {
        return "Course{" +
                "Id=" + Id +
                ", courseTitle='" + courseTitle + '\'' +
                ", durationInMinuts=" + durationInMinuts +
                ", basePrice=" + basePrice +
                ", isActive=" + isActive +
                '}';
    }
}
