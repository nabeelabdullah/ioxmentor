package com.ioxmentor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ioxmentor.entity.User;

/**
 * created By @Nabeel 08-Oct-2017
 **/

public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
