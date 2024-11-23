package com.example.recipe_app.repository;

import com.example.recipe_app.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    // Additional query methods can be defined here
} 