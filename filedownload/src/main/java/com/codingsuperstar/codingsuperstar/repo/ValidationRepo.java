package com.codingsuperstar.codingsuperstar.repo;

import com.codingsuperstar.codingsuperstar.entity.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nabeelabdullah on 01/11/17.
 */
public interface ValidationRepo extends JpaRepository<Validation, Long> {

    Validation findByCode(String code);
}
