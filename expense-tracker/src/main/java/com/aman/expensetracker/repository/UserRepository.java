package com.aman.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aman.expensetracker.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}