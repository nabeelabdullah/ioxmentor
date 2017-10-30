package com.codingsuperstar.codingsuperstar.enums;

import com.codingsuperstar.codingsuperstar.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nabeelabdullah on 21/10/17.
 */
public interface AlertRepo extends JpaRepository<Alert, Long> {
}
