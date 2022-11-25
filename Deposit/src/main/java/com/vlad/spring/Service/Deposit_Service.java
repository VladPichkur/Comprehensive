package com.vlad.spring.Service;
import com.vlad.spring.Dao.Bank_Repository;
import com.vlad.spring.Dao.Deposit_Repository;

import com.vlad.spring.Entity.Bank;
import com.vlad.spring.Entity.Deposit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Deposit_Service {
    private final Deposit_Repository deposit_repository;
    private static final Logger logger= LoggerFactory.getLogger(Deposit_Service.class);
    private final Bank_Repository bank_repository;

    @Autowired
    public Deposit_Service(Deposit_Repository deposit_repository, Bank_Repository bank_repository) {
        this.deposit_repository = deposit_repository;
        this.bank_repository = bank_repository;
    }
    @Transactional
    public List<Deposit> getDeposit(){
        logger.info("Взято список депозитів");
        return deposit_repository.findAll();
    }

    @Transactional
    public void addDeposit(String name, Long bank, Double sum_min, Double sum_max, Double nalog,Integer termin, Double interest_rate){
        logger.info("Спроба створення...");
        Bank bank1 = bank_repository.getReferenceById(bank);
        Deposit deposit = new Deposit(name,bank1,sum_min,sum_max,nalog,termin,interest_rate);
        logger.info("Успішно створено!!!");
        deposit_repository.save(deposit);
    }

    @Transactional
    public void removeDeposit(Long depositID){
        logger.info("Спроба видалення...");
        boolean exists= deposit_repository.existsById(depositID);
        if (!exists){
            logger.error("Помилка");
            throw new IllegalStateException("Депозит з ID "+depositID+" не існує");
        }

        if (deposit_repository.getReferenceById(depositID).getBank()!=null){
            logger.error("Помилка");
            throw new IllegalStateException("Депозит з ID "+depositID+" використується");
        }
        if (!deposit_repository.getReferenceById(depositID).getContractList().isEmpty()){
            logger.error("Помилка");
            throw new IllegalStateException("Депозит з ID "+depositID+" використується");
        }
        deposit_repository.deleteById(depositID);
        logger.info("Успішно видалено!!!");
    }
}
