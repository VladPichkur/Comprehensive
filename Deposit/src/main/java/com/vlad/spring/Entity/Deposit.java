package com.vlad.spring.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Deposit {
    @Id
    @SequenceGenerator(
            name = "deposit_sequence",
            sequenceName = "deposit_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "deposit_sequence"
    )
    private Long ID;
    private String name;
    @ManyToOne
    private Bank bank;
    private  Double sum_min;
    private Double sum_max;
    private Double nalog;
    private Integer termin;
    private Boolean dostrokove = true;
    private Double interest_rate; //процентна ставка
    @OneToMany
    private List<Contract> contractList;

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }



    public Deposit(Long ID,String name, Bank bank, Double sum_min, Double sum_max, Double nalog, Integer termin, Boolean dostrokove,  Double interest_rate, List<Contract> contractList) {
        this.ID = ID;
        this.name=name;
        this.bank = bank;
        this.sum_min = sum_min;
        this.sum_max = sum_max;
        this.nalog = nalog;
        this.termin = termin;
        this.dostrokove = dostrokove;
        this.interest_rate = interest_rate;
        this.contractList = contractList;
    }
    public Deposit(String name,Bank bank, Double sum_min, Double sum_max, Double nalog, Integer termin, Double interest_rate) {
        this.name=name;
        this.bank = bank;
        this.sum_min = sum_min;
        this.sum_max = sum_max;
        this.nalog = nalog;
        this.termin = termin;
        this.interest_rate = interest_rate;
    }
    public Deposit() {

    }

    public Deposit(Long ID, String name, Double sum_min, Double sum_max, Double nalog, Integer termin, Double interest_rate) {
        this.ID = ID;
        this.name = name;
        this.sum_min = sum_min;
        this.sum_max = sum_max;
        this.nalog = nalog;
        this.termin = termin;
        this.interest_rate = interest_rate;
    }


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank name) {
        this.bank = name;
    }

    public Double getSum_min() {
        return sum_min;
    }

    public void setSum_min(Double sum_min) {
        this.sum_min = sum_min;
    }

    public Double getSum_max() {
        return sum_max;
    }

    public void setSum_max(Double sum_max) {
        this.sum_max = sum_max;
    }

    public Double getNalog() {
        return nalog;
    }

    public void setNalog(Double nalog) {
        this.nalog = nalog;
    }

    public Integer getTermin() {
        return termin;
    }

    public void setTermin(Integer termin) {
        this.termin = termin;
    }

    public Boolean getDostrokove() {
        return dostrokove;
    }

    public void setDostrokove(Boolean dostrokove) {
        this.dostrokove = dostrokove;
    }



    public Double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(Double interest_rate) {
        this.interest_rate = interest_rate;
    }


    @Override
    public String toString() {
        return "Депозит (" +
                ", ID: " + ID +
                ", Ім'я банку: " + bank +
                ", Мінімальна сума вкладу: " + sum_min +
                ", Максимальна сума вкладу: " + sum_max +
                ", Державний налог: " + nalog +
                ", Термін: " + termin +
                ", Процентна ставка: " + interest_rate +
                ')';
    }
}
