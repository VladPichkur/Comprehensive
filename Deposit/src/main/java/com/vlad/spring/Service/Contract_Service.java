package com.vlad.spring.Service;

import com.vlad.spring.Dao.Client_Repository;
import com.vlad.spring.Dao.Contract_Repository;

import com.vlad.spring.Dao.Deposit_Repository;
import com.vlad.spring.Entity.Client;
import com.vlad.spring.Entity.Contract;
import com.vlad.spring.Entity.Deposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class Contract_Service {
    private final Contract_Repository contract_repository;
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
        return contract_repository.findAllBySum_contract_poshyk(sum);
    }

//    @Transactional
//    public List<Contract> getContractByContractID_more_than(Long ID1){
//        return contract_repository.findAllByContractID_more_than(ID1);
//    }
//    @Transactional
//    public List<Contract> getContractByContractID_less_than(Long ID1){
//        return contract_repository.findAllByContractID_less_than(ID1);
//    }
//    @Transactional
//    public List<Contract> getContractByContractID_poshyk(Long ID1){
//        return contract_repository.findAllByContractID_poshyk(ID1);
//    }

    @Transactional
    public List<Contract> getContractByContractID_poshyk_all(Long ID1, Double sum1){
        return contract_repository.findAllByContractID_poshyk_za_id_sum(ID1,sum1);
    }
    @Transactional
    public void addContract(Long deposit,Double sum_contarct, Long client){
        Deposit deposit1 = deposit_repository.getReferenceById(deposit);
        Client client1 = client_repository.getReferenceById(client);
        Contract contract = new Contract(deposit1,sum_contarct,client1, LocalDate.now());
       contract_repository.save(contract);
    }
    @Transactional
    public void addContract1(Long deposit,Double sum_contarct, Long client,LocalDate date){
        Deposit deposit1 = deposit_repository.getReferenceById(deposit);
        Client client1 = client_repository.getReferenceById(client);
        Contract contract = new Contract(deposit1,sum_contarct,client1, date);
        contract_repository.save(contract);
    }
    @Transactional
    public void removeContract(Long contractID){
        boolean exists= contract_repository.existsById(contractID);
        if (!exists){
            throw new IllegalStateException("Депозит з ID "+contractID+" не існує");
        }

        if (contract_repository.getReferenceById(contractID).getSum_contract_dostrokove()>0){
            throw new IllegalStateException("Депозит з ID "+contractID+" використується");
        }

        contract_repository.deleteById(contractID);
    }
    @Transactional
    public void Znyatya_Dostrokove(Long contractID){

        boolean exists= contract_repository.existsById(contractID);
        if (!exists){
            throw new IllegalStateException("Депозит з ID "+contractID+" не існує");
        }

        if (!contract_repository.getReferenceById(contractID).getDeposit_condition().getDostrokove()){
            throw new IllegalStateException("Депозит з ID "+contractID+" використується");
        }
        contract_repository.getReferenceById(contractID).getClient().setBalance(
                (1- contract_repository.getReferenceById(contractID).getDeposit_condition().getNalog())*
                        contract_repository.getReferenceById(contractID).getClient().getBalance()+
                        contract_repository.getReferenceById(contractID).getSum_contract_dostrokove());
        contract_repository.getReferenceById(contractID).setSum_contract(0.0);
        contract_repository.deleteById(contractID);



    }

    @Transactional
    public void Znatya(Long contractID){
        boolean exists= contract_repository.existsById(contractID);
        if (!exists){
            throw new IllegalStateException("Депозит з ID "+contractID+" не існує");
        }

        if (Period.between(contract_repository.getReferenceById(contractID).getTermin_pochatok(),LocalDate.now()).getMonths()>=contract_repository.getReferenceById(contractID).getDeposit_condition().getTermin()){
            throw new IllegalStateException("Депозит з ID "+contractID+" використується");
        }
        contract_repository.getReferenceById(contractID).getClient().setBalance(
                (1- contract_repository.getReferenceById(contractID).getDeposit_condition().getNalog())*
                        contract_repository.getReferenceById(contractID).getClient().getBalance()+
                        contract_repository.getReferenceById(contractID).getSum_contract_povne());
        contract_repository.getReferenceById(contractID).setSum_contract(0.0);
        contract_repository.deleteById(contractID);
    }
    @Transactional
    public void Popovnutu(Long contractID, Double sum){
        boolean exists= contract_repository.existsById(contractID);
        if (!exists){
            throw new IllegalStateException("Депозит з ID "+contractID+" не існує");
        }

        if (contract_repository.getReferenceById(contractID).getClient().getBalance()<sum){
            throw new IllegalStateException("Не достатньо коштів на рахунку");
        }
        contract_repository.getReferenceById(contractID).setSum_contract(contract_repository.getReferenceById(contractID).getSum_contract_dostrokove()+sum);
        contract_repository.getReferenceById(contractID).getClient().setBalance(contract_repository.getReferenceById(contractID).getClient().getBalance()-sum);

    }

}
