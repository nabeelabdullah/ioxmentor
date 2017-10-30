package com.codingsuperstar.codingsuperstar.repo;

import com.codingsuperstar.codingsuperstar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * created By @Nabeel 08-Oct-2017
 **/

public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
