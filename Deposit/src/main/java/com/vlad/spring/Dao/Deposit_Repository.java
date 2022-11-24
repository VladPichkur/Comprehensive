package com.vlad.spring.Dao;

import com.vlad.spring.Entity.Bank;
import com.vlad.spring.Entity.Contract;
import com.vlad.spring.Entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface Deposit_Repository
        extends JpaRepository<Deposit,Long> {
    @Query("SELECT obj FROM Deposit obj WHERE obj.ID=?1")
    Optional<Deposit> findById(Long id);

//    @Query("SELECT obj from Deposit obj where obj.sum_min>= (:sum_min1)")
//    List<Deposit> findAllBySum_min(@Param("sum_min1") Double sum_min1);
//
//    @Query("SELECT obj from Deposit obj where obj.sum_max>= (:sum_max1)")
//    List<Deposit> findAllBySum_max(@Param("sum_max1") Double sum_max1);
//
//    @Query("SELECT obj from Deposit obj where obj.nalog>= (:nalog1)")
//    List<Deposit> findAllByNalog(@Param("nalog1") Double nalog1);
//
//    @Query("SELECT obj from Deposit obj where obj.interest_rate>= (:interest_rate1)")
//    List<Deposit> findAllByInterest_rate(@Param("interest_rate1") Double interest_rate1);
//
//    @Query("SELECT obj from Deposit obj where obj.termin>= (:termin1)")
//    List<Deposit> findAllByTermin(@Param("termin1") Integer termin1);

//    @Query("SELECT obj from Deposit obj where obj.bank>= (:bank1)")
//    List<Deposit> findAllByBank(@Param("bank1") Bank bank1);
}