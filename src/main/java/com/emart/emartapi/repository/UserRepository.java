package com.emart.emartapi.repository;

import com.emart.emartapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {

}
