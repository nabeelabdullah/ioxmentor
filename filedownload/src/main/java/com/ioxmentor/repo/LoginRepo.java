package com.ioxmentor.repo;

import com.ioxmentor.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nabeelabdullah on 09/10/17.
 */
public interface LoginRepo extends JpaRepository<Login, Long> {

    Login findByToken(String token);
}
