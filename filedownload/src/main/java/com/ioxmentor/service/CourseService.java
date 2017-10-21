package com.ioxmentor.service;

import com.ioxmentor.entity.Course;
import com.ioxmentor.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by nabeelabdullah on 21/10/17.
 */
@Component
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    public Course getCourseById(Long id) {
        Course course = courseRepo.findOne(id);
        return course;
    }
}
