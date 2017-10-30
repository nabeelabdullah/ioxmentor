package com.codingsuperstar.codingsuperstar.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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

    private Float basePriceOnlie;

    private Float basePriceOffline;

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

    public Float getBasePriceOnlie() {
        return basePriceOnlie;
    }

    public void setBasePriceOnlie(Float basePriceOnlie) {
        this.basePriceOnlie = basePriceOnlie;
    }

    public Float getBasePriceOffline() {
        return basePriceOffline;
    }

    public void setBasePriceOffline(Float basePriceOffline) {
        this.basePriceOffline = basePriceOffline;
    }
}
