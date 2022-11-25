package com.vlad.spring.Service;

import com.vlad.spring.Dao.Client_Repository;
import com.vlad.spring.Dao.Contract_Repository;

import com.vlad.spring.Dao.Deposit_Repository;
import com.vlad.spring.Entity.Client;
import com.vlad.spring.Entity.Contract;
import com.vlad.spring.Entity.Deposit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class Contract_Service {
    private final Contract_Repository contract_repository;
    private static final Logger logger= LoggerFactory.getLogger(Contract_Service.class);
    private final Deposit_Repository deposit_repository;
    private final Client_Repository client_repository;
    @Autowired
    public Contract_Service(Contract_Repository contract_repository,Deposit_Repository deposit_repository, Client_Repository client_repository) {
        this.contract_repository = contract_repository;
        this.deposit_repository = deposit_repository;
        this.client_repository=client_repository;
    }
    @Transactional
    public List<Contract> getContract(){
        logger.info("Взято список контрактів");
        return contract_repository.findAll();
    }

    @Transactional
    public List<Contract> getContractBySum_more_than(Double sum){
        return contract_repository.findAllBySum_contract_more_than(sum);
    }
    @Transactional
    public List<Contract> getContractBySum_less_than(Double sum){
        return contract_repository.findAllBySum_contract_less_than(sum);
    }
    @Transactional
    public List<Contract> getContractBySum_poshyk(Double sum){
        logger.info("Знайдено");
        return contract_repository.findAllBySum_contract_poshyk(sum);
    }


    @Transactional
    public List<Contract> getContractByContractID_poshyk_all(Long ID1, Double sum1){
        logger.info("Знайдено");
        return contract_repository.findAllByContractID_poshyk_za_id_sum(ID1,sum1);
    }
    @Transactional
    public void addContract(Long deposit,Double sum_contarct, Long client){
        logger.info("Спроба створення...");
        Deposit deposit1 = deposit_repository.getReferenceById(deposit);
        Client client1 = client_repository.getReferenceById(client);
        Contract contract = new Contract(deposit1,sum_contarct,client1, LocalDate.now());
       contract_repository.save(contract);
        logger.info("Успішно створено!!!");
    }
    @Transactional
    public void addContract1(Long deposit,Double sum_contarct, Long client,LocalDate date){
        logger.info("Спроба створення:");
        Deposit deposit1 = deposit_repository.getReferenceById(deposit);
        Client client1 = client_repository.getReferenceById(client);
        Contract contract = new Contract(deposit1,sum_contarct,client1, date);
        contract_repository.save(contract);
        logger.info("Успішно створено!!!");
    }
    @Transactional
    public void removeContract(Long contractID){
        logger.info("Спроба видалення...");
        boolean exists= contract_repository.existsById(contractID);
        if (!exists){
            logger.error("Помилка");
            throw new IllegalStateException("Депозит з ID "+contractID+" не існує");
        }

        if (contract_repository.getReferenceById(contractID).getSum_contract_dostrokove()>0){
            logger.error("Помилка");
            throw new IllegalStateException("Депозит з ID "+contractID+" використується");
        }

        contract_repository.deleteById(contractID);
        logger.info("Успішно видалено!!!");
    }
    @Transactional
    public void Znyatya_Dostrokove(Long contractID){
        logger.info("Спроба дострокового зняття...");
        boolean exists= contract_repository.existsById(contractID);
        if (!exists){
            logger.error("Помилка");
            throw new IllegalStateException("Депозит з ID "+contractID+" не існує");
        }

        if (!contract_repository.getReferenceById(contractID).getDeposit_condition().getDostrokove()){
            logger.error("Помилка");
            throw new IllegalStateException("Депозит з ID "+contractID+" використується");
        }
        contract_repository.getReferenceById(contractID).getClient().setBalance(
                (1- contract_repository.getReferenceById(contractID).getDeposit_condition().getNalog())*
                        contract_repository.getReferenceById(contractID).getClient().getBalance()+
                        contract_repository.getReferenceById(contractID).getSum_contract_dostrokove());
        contract_repository.getReferenceById(contractID).setSum_contract(0.0);
        contract_repository.deleteById(contractID);
        logger.info("Успішно знято!!!");


    }

    @Transactional
    public void Znatya(Long contractID){
        logger.info("Спроба зняття...");
        boolean exists= contract_repository.existsById(contractID);
        if (!exists){
            logger.error("Помилка");
            throw new IllegalStateException("Депозит з ID "+contractID+" не існує");
        }

        if (Period.between(contract_repository.getReferenceById(contractID).getTermin_pochatok(),LocalDate.now()).getMonths()>=contract_repository.getReferenceById(contractID).getDeposit_condition().getTermin()){
            logger.error("Помилка");
            throw new IllegalStateException("Депозит з ID "+contractID+" використується");
        }
        contract_repository.getReferenceById(contractID).getClient().setBalance(
                (1- contract_repository.getReferenceById(contractID).getDeposit_condition().getNalog())*
                        contract_repository.getReferenceById(contractID).getClient().getBalance()+
                        contract_repository.getReferenceById(contractID).getSum_contract_povne());
        contract_repository.getReferenceById(contractID).setSum_contract(0.0);
        contract_repository.deleteById(contractID);
        logger.info("Успішно знято!!!");
    }
    @Transactional
    public void Popovnutu(Long contractID, Double sum){
        logger.info("Спроба поповнення...");
        boolean exists= contract_repository.existsById(contractID);
        if (!exists){
            logger.error("Помилка");
            throw new IllegalStateException("Депозит з ID "+contractID+" не існує");
        }

        if (contract_repository.getReferenceById(contractID).getClient().getBalance()<sum){
            logger.error("Помилка");
            throw new IllegalStateException("Не достатньо коштів на рахунку");
        }
        contract_repository.getReferenceById(contractID).setSum_contract(contract_repository.getReferenceById(contractID).getSum_contract_dostrokove()+sum);
        contract_repository.getReferenceById(contractID).getClient().setBalance(contract_repository.getReferenceById(contractID).getClient().getBalance()-sum);
        logger.info("Успішно поповнено!!!");
    }

}
