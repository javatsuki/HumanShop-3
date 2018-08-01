package com.example.Human.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Human.entity.Users;

@Repository
public interface HumanRepository extends JpaRepository<Users, Integer>{

}
