package com.jwtdemo.authjwt.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwtdemo.authjwt.entity.User;

@Repository
public interface UserReponsitory extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findById(int id);

    Boolean existsByUsername(String username);
}
