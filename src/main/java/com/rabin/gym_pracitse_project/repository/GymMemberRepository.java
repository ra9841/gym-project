package com.rabin.gym_pracitse_project.repository;

import com.rabin.gym_pracitse_project.entity.GymMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GymMemberRepository extends JpaRepository<GymMemberEntity, Long> {
    Optional<GymMemberEntity> findByEmail(String email);
}
