package com.vlad.spring.Service;
import com.vlad.spring.Dao.Bank_Repository;
import com.vlad.spring.Dao.Deposit_Repository;

import com.vlad.spring.Entity.Bank;
import com.vlad.spring.Entity.Deposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Deposit_Service {
    private final Deposit_Repository deposit_repository;
    private final Bank_Repository bank_repository;

    @Autowired
    public Deposit_Service(Deposit_Repository deposit_repository, Bank_Repository bank_repository) {
        this.deposit_repository = deposit_repository;
        this.bank_repository = bank_repository;
    }
    @Transactional
    public List<Deposit> getDeposit(){
        return deposit_repository.findAll();
    }
//    @Transactional
//    public List<Deposit> getDepositBySum_min(Double sum_min1){
//        return deposit_repository.findAllBySum_min(sum_min1);
//    }
//    @Transactional
//    public List<Deposit> getDepositBySum_max(Double sum_max1){
//        return deposit_repository.findAllBySum_max(sum_max1);
//    }
//
//    @Transactional
//    public List<Deposit> getDepositByInterest_rate(Double interest_rate1){
//        return deposit_repository.findAllByInterest_rate(interest_rate1);
//    }
//
//    @Transactional
//    public List<Deposit> getDepositByTermin(Integer termin1){return deposit_repository.findAllByTermin(termin1);}
    @Transactional
    public void addDeposit(String name, Long bank, Double sum_min, Double sum_max, Double nalog,Integer termin, Double interest_rate){
        Bank bank1 = bank_repository.getReferenceById(bank);
        Deposit deposit = new Deposit(name,bank1,sum_min,sum_max,nalog,termin,interest_rate);
        deposit_repository.save(deposit);
    }

    @Transactional
    public void removeDeposit(Long depositID){
        boolean exists= deposit_repository.existsById(depositID);
        if (!exists){
            throw new IllegalStateException("Депозит з ID "+depositID+" не існує");
        }

        if (deposit_repository.getReferenceById(depositID).getBank()!=null){
            throw new IllegalStateException("Депозит з ID "+depositID+" використується");
        }
        if (!deposit_repository.getReferenceById(depositID).getContractList().isEmpty()){
            throw new IllegalStateException("Депозит з ID "+depositID+" використується");
        }
        deposit_repository.deleteById(depositID);
    }
}
