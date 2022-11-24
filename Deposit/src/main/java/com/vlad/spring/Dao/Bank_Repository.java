package com.vlad.spring.Dao;

import com.vlad.spring.Entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface Bank_Repository

        extends JpaRepository<Bank,Long> {
        @Query("SELECT obj FROM Bank obj WHERE obj.ID=?1")
        Optional<Bank> findById(Long id);
        }

