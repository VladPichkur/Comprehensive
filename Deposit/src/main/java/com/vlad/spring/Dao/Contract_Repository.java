package com.vlad.spring.Dao;

import com.vlad.spring.Entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Contract_Repository
        extends JpaRepository<Contract,Long> {
    @Query("SELECT obj FROM Contract obj WHERE obj.contractID=?1")
    Optional<Contract> findById(Long id);

    @Query("SELECT obj from Contract obj where obj.sum_contract>= (:sum1)")
    List<Contract> findAllBySum_contract_more_than(@Param("sum1") Double sum1);

    @Query("SELECT obj from Contract obj where obj.sum_contract<= (:sum1)")
    List<Contract> findAllBySum_contract_less_than(@Param("sum1") Double sum1);

    @Query("SELECT obj from Contract obj where obj.sum_contract= (:sum1)")
    List<Contract> findAllBySum_contract_poshyk(@Param("sum1") Double sum1);

    @Query("SELECT obj from Contract obj where obj.contractID>= (:ID1)")
    List<Contract> findAllByContractID_more_than(@Param("ID1") Long ID1);

    @Query("SELECT obj from Contract obj where obj.contractID<= (:ID1)")
    List<Contract> findAllByContractID_less_than(@Param("ID1") Long ID1);

    @Query("SELECT obj from Contract obj where obj.contractID= (:ID1)")
    List<Contract> findAllByContractID_poshyk(@Param("ID1") Long ID1);

    @Query("SELECT obj from Contract obj where obj.contractID= (:ID1) and obj.sum_contract=(:sum1)")
    List<Contract> findAllByContractID_poshyk_za_id_sum(@Param("ID1") Long ID1, @Param("sum1") Double sum1);

}