package com.chainhaus.mtg.repository;

import com.chainhaus.mtg.entity.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Asad Sarwar on 20/06/2020.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByTitle(String title);
}
