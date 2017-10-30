package com.codingsuperstar.codingsuperstar.repo;

import com.codingsuperstar.codingsuperstar.entity.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nabeelabdullah on 09/10/17.
 */
public interface EnrollRepo extends JpaRepository<Enroll, Long> {

    Enroll findByCourseIdAndUserId(Long courseId, Long userId);

}
