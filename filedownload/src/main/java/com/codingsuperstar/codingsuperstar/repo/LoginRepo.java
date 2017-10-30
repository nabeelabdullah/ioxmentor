package com.codingsuperstar.codingsuperstar.repo;


import com.codingsuperstar.codingsuperstar.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nabeelabdullah on 09/10/17.
 */
public interface LoginRepo extends JpaRepository<Login, Long> {

    Login findByToken(String token);
}
