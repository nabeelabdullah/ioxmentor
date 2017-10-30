package com.codingsuperstar.codingsuperstar.enums;

import com.codingsuperstar.codingsuperstar.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nabeelabdullah on 10/10/17.
 */
public interface TransanctionRepo extends JpaRepository<Transaction, Long> {
}
