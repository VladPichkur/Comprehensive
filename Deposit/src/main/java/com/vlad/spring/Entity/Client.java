package com.vlad.spring.Entity;

import javax.persistence.*;
import java.util.List;
@Entity
@Table
public class Client {
    @Id
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    private Long ID;

    private String name;
    @OneToMany
    private List<Contract> contractList;
    private Double Balance;

    public Client(Long ID, String name, List<Contract> contractList, Double balance) {
        this.ID = ID;
        this.name = name;
        this.contractList = contractList;
        Balance = balance;
    }
    public Client( String name, List<Contract> contractList, Double balance) {
        this.name = name;
        this.contractList = contractList;
        Balance = balance;
    }
    public Client() {

    }

    public Client(Long ID, String name, Double balance) {
        this.ID = ID;
        this.name = name;
        Balance = balance;
    }

    public Client(String name, Double balance) {
        this.name = name;
        Balance = balance;
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

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    @Override
    public String toString() {
        return "Клієнт (" +
                "ID: " + ID +
                ", Ім'я: '" + name + '\'' +
                //", contractList=" + contractList +
                ", Баланс: " + Balance +
                ')';
    }
}
