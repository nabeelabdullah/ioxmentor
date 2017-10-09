package com.ioxmentor.repo;

import com.ioxmentor.entity.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nabeelabdullah on 09/10/17.
 */
public interface EnrollRepo extends JpaRepository<Enroll, Long> {

    Enroll findByCourseIdAndUserId(Long courseId, Long userId);

}
