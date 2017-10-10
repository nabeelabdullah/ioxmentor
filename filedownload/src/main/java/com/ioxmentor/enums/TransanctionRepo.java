package com.ioxmentor.enums;

import com.ioxmentor.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nabeelabdullah on 10/10/17.
 */
public interface TransanctionRepo extends JpaRepository<Transaction, Long> {
}
