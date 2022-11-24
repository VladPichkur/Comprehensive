package com.vlad.spring.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Contract {
    @Id
    @SequenceGenerator(
            name = "contract_sequence",
            sequenceName = "contract_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contract_sequence"
    )
    private Long contractID;
    @ManyToOne
    private Deposit deposit_condition;//умова депозиту
    private Double sum_contract;//сума контракту
    @ManyToOne
    private Client client;//клієнт
    private LocalDate termin_pochatok;

    public Contract(Long ID, Deposit deposit_condition, Double sum_contract, Client client, LocalDate termin_pochatok) {
        this.contractID = ID;
        this.deposit_condition = deposit_condition;
        this.sum_contract = sum_contract;
        this.client = client;
        this.termin_pochatok=termin_pochatok;
    }
    public Contract( Deposit deposit_condition, Double sum_contract, Client client,LocalDate termin_pochatok) {

        this.deposit_condition = deposit_condition;
        this.sum_contract = sum_contract;
        this.client = client;
        this.termin_pochatok=termin_pochatok;
    }
    public Contract() {

    }

    public Contract(Long contractID, Double sum_contract, LocalDate termin_pochatok) {
        this.contractID = contractID;
        this.sum_contract = sum_contract;
        this.termin_pochatok=termin_pochatok;
    }

    public Contract(LocalDate termin_pochatok) {
        this.termin_pochatok = termin_pochatok;
    }

    public Long getContractID() {
        return contractID;
    }

    public void setContractID(Long ID) {
        this.contractID = ID;
    }

    public Deposit getDeposit_condition() {
        return deposit_condition;
    }

    public void setDeposit_condition(Deposit deposit_condition) {
        this.deposit_condition = deposit_condition;
    }

    public Double getSum_contract_dostrokove() {
     return sum_contract+sum_contract*deposit_condition.getInterest_rate()*(Period.between(termin_pochatok,LocalDate.now()).getMonths()/deposit_condition.getTermin());

    }
    public Double getSum_contract_povne() {
        return sum_contract+sum_contract*deposit_condition.getInterest_rate();

    }

    public Double getSum_contract() {
        return sum_contract;
    }

    public void setSum_contract(Double sum_contract) {
        this.sum_contract = sum_contract;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getTermin_pochatok() {
        return termin_pochatok;
    }

    public void setTermin_pochatok(LocalDate termin_pochatok) {
        this.termin_pochatok = termin_pochatok;
    }



    @Override
    public String toString() {
        return "Контракт (" +
                "ID: " + contractID +
                ", Сума контракту: " + sum_contract +
                ", Депозит: " + deposit_condition +
                ", Клієнт: " + client +
                ", Дата підписання контракту: " + termin_pochatok +
                ')';
    }
}
