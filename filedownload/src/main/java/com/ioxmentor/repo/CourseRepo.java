package com.ioxmentor.repo;

import com.ioxmentor.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nabeelabdullah on 21/10/17.
 */
public interface CourseRepo extends JpaRepository<Course, Long> {
}
