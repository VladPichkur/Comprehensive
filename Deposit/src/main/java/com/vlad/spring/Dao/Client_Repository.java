package com.vlad.spring.Dao;

import com.vlad.spring.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface Client_Repository
        extends JpaRepository<Client,Long> {
    @Query("SELECT obj FROM Client obj WHERE obj.ID=?1")
    Optional<Client> findById(Long id);
}
