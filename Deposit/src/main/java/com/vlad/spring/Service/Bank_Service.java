package com.vlad.spring.Service;

import com.vlad.spring.Dao.Bank_Repository;
import com.vlad.spring.Entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class Bank_Service {
    private final Bank_Repository bank_repository;

    @Autowired
    public Bank_Service(Bank_Repository bank_repository) {
        this.bank_repository = bank_repository;
    }

    @Transactional
    public List<Bank> getBank(){
        return bank_repository.findAll();
    }

    @Transactional
    public void addBank(Bank bank){
        Optional<Bank> optionalPlayer= bank_repository.findById(bank.getID());
        if (optionalPlayer.isPresent()){
            throw new IllegalStateException("ID взято");
        }

        bank_repository.save(bank);
    }

    @Transactional
    public void removeBank(Long bankID){
        boolean exists= bank_repository.existsById(bankID);
        if (!exists){
            throw new IllegalStateException("Банку з ID "+bankID+" не існує");
        }

       bank_repository.deleteById(bankID);
    }
}
