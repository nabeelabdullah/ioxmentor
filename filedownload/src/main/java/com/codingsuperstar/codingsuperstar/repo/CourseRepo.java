package com.codingsuperstar.codingsuperstar.repo;


import com.codingsuperstar.codingsuperstar.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nabeelabdullah on 21/10/17.
 */
public interface CourseRepo extends JpaRepository<Course, Long> {
}
