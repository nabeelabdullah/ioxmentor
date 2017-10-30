package com.codingsuperstar.codingsuperstar.repo;

import com.codingsuperstar.codingsuperstar.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nabeelabdullah on 30/10/17.
 */
public interface TemplateRepo extends JpaRepository<Template, Long> {
    Template findByName(String name);
}
