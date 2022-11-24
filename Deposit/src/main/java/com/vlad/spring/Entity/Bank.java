package com.vlad.spring.Entity;
import javax.persistence.*;
import java.util.List;
@Entity
@Table
public class Bank {
    @Id
    @SequenceGenerator(
            name = "bank_sequence",
            sequenceName = "bank_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bank_sequence"
    )
    private Long ID;

    private String Name;
    @OneToMany
    private List<Deposit> Depositymova;

    public Bank(Long ID, String name, List<Deposit> depositymova) {
        this.ID = ID;
        Name = name;
        Depositymova = depositymova;
    }

    public Bank(String name, List<Deposit> depositymova) {
        Name = name;
        Depositymova = depositymova;
    }
    public Bank() {

    }

    public Bank(Long ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public Bank(String name) {
        Name = name;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Deposit> getDepositymova() {
        return Depositymova;
    }

    public void setDepositymova(List<Deposit> depositymova) {
        Depositymova = depositymova;
    }

    @Override
    public String toString() {
        return "Банк (" +
                "ID: " + ID +
                ", Назва: '" + Name + '\'' +
                ')';
    }

}
