package com.chainhaus.mtg.repository;

import com.chainhaus.mtg.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Asad Sarwar on 11/06/2020.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);
    User findByEmail(String email);
}
