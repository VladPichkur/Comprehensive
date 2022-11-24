package com.vlad.spring.Demo;

import com.vlad.spring.Entity.Bank;
import com.vlad.spring.Entity.Client;
import com.vlad.spring.Entity.Contract;
import com.vlad.spring.Entity.Deposit;
import com.vlad.spring.Service.Bank_Service;
import com.vlad.spring.Service.Client_Service;
import com.vlad.spring.Service.Contract_Service;
import com.vlad.spring.Service.Deposit_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
@RestController
public class APP implements CommandLineRunner {
    private Scanner scanner;
    @Autowired
    private Bank_Service bank_service;
    @Autowired
    private Client_Service client_service;
    @Autowired
    private Contract_Service contract_service;
    @Autowired
    private Deposit_Service deposit_service;

    public APP(Scanner scanner, Bank_Service bank_service, Client_Service client_service, Contract_Service contract_service, Deposit_Service deposit_service) {
        this.scanner = scanner;
        this.bank_service = bank_service;
        this.client_service = client_service;
        this.contract_service = contract_service;
        this.deposit_service = deposit_service;
    }
    public APP() {
    }

    @PostConstruct
    public void start(){
        System.out.println(3);
        configBank();
        scanner=new Scanner(System.in);
        while (menu()){}
    }
    private boolean menu(){
        System.out.println("------------------------------------");
        System.out.println("1-Банк\n" +
                "2-Клієнт\n"+
                "3-Контракт\n" +
                "4-Депозит\n" +
                "0-Вихід");
        int choice= scanner.nextInt();
        switch (choice){
            case 1:
                while (Bank_inter()){}
                return true;
            case 2:
                while (Client_inter()){}
                return true;
            case 3:
                while (Contract_inter()){}
                return true;
            case 4:
                while (Deposit_inter()){}
                return true;
            case 0:
                return false;
        }
        return false;
    }
    private boolean Bank_inter(){
        System.out.println("------------------------------------");
        System.out.println("1-добавити банк\n" +
                "2-видалити банк\n"+
                "3-отримати список доступних банків\n"+
                "0-вийти");
        int choice= scanner.nextInt();
        switch (choice){
            case 1:
                addBank();
                return true;
            case 2:
                removeBank();
                return true;
            case 3:
                getListOfBanks();
                return true;

            case 0:
                return false;
        }
        return false;
    }
    private void addBank(){
        System.out.println("------------------------------------");
        System.out.println("Добавити банк:");
        System.out.println("Ім'я банку:");
        String name = scanner.next();

        try {
            Bank bank =new Bank(name);
            bank_service.addBank(bank);
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }
    private void removeBank(){
        System.out.println("------------------------------------");
        System.out.println("Оберіть ID банку, який хочете видалити:");
        long choice= scanner.nextInt();
        try {
            bank_service.removeBank(choice);
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }

    private void getListOfBanks(){
        System.out.println("------------------------------------");
        System.out.println("Список банків:");
        try {
            for (Bank bank :bank_service.getBank()) {
                System.out.println(bank.toString());
            }
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }

    private boolean Client_inter(){
        System.out.println("------------------------------------");
        System.out.println("Оберіть варіант");
        System.out.println("1-добавити клієнта\n" +
                "2-видалити клієнта\n"+
                "3-отримати список клієнтів\n"+
                "0-вихід");
        int choice= scanner.nextInt();
        switch (choice){
            case 1:
                addClient();
                return true;
            case 2:
                removeClient();
                return true;
            case 3:
                getListOfClients();
                return true;

            case 0:
                return false;
        }
        return false;
    }
    private void addClient(){
        System.out.println("------------------------------------");
        System.out.println("Введіть ім'я клієнта:");
        String choice= scanner.next();
        System.out.println("Введіть баланс клієнта:");
        Double choice2= scanner.nextDouble();
        try {
            client_service.addClient(new Client(choice, choice2));
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }
    private void removeClient(){
        System.out.println("------------------------------------");
        System.out.println("Оберіть ID клієнта, якого хочете видалити");
        long choice= scanner.nextInt();
        try {
            client_service.removeClient(choice);
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }

    private void getListOfClients(){
        System.out.println("------------------------------------");
        System.out.println("Список клієнтів:");
        try {
            for (Client client :client_service.getClient()) {
                System.out.println(client.toString());
            }
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }


    private boolean Contract_inter(){
        System.out.println("------------------------------------");
        System.out.println("1-добавити контракт\n" +
                "2-видалити контракт\n"+
                "3-зняття депозиту з контракту\n" +
                "4-дострокове зняття\n"+
                "5-поповнення депозиту контракта\n"+
                "6-отримати список контрактів\n"+
                "7-сортування за зростанням суми контратку\n"+
                "8-сортування за спаданням суми контракту\n"+
                "9-пошук контрактів за ID та сумою\n"+
                "10-пошук контрактів за сумою\n"+
                "0-вийти");
        int choice= scanner.nextInt();
        switch (choice){
            case 1:
                addContract();
                return true;
            case 2:
                removeContract();
                return true;
            case 3:
                Znatya();
                return true;
            case 4:
                Znatya_Chastkove();
                return true;
            case 5:
                Popovnutu();
                return true;
            case 6:
                getListOfContracts();
                return true;
            case 7:

                getListOfContractsBySum_more_than();
                return true;
            case 8:
                getListOfContractBySum_less_than();
                return true;
            case 9:
                poshyk();
                return true;
            case 10:
                getListOfContractBySum_poshyk();
                return true;
            case 0:

                return false;
        }
        return false;
    }
    private void addContract(){
        System.out.println("------------------------------------");
        System.out.println("Введіть суму контракту:");
        Double sum_contract= scanner.nextDouble();
        System.out.println("Введіть ID депозиту:");
        Long deposit = scanner.nextLong();
        System.out.println("Введіть ID клієнту:");
        Long client = scanner.nextLong();
        try {
            contract_service.addContract(deposit,sum_contract,client);
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }
    private void removeContract(){
        System.out.println("------------------------------------");
        System.out.println("Введіть ID контракту, який хочете видалити");
        long choice= scanner.nextInt();
        try {
            contract_service.removeContract(choice);
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }
    private void Znatya_Chastkove(){
        System.out.println("------------------------------------");
        System.out.println("Введіть ID контракту:");
        Long id = scanner.nextLong();
        try {
           contract_service.Znyatya_Dostrokove(id);
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }
    private void Znatya(){
        System.out.println("------------------------------------");
        System.out.println("Введіть ID контракту:");
        Long id = scanner.nextLong();
            contract_service.Znatya(id);

    }


    private void Popovnutu(){
        System.out.println("------------------------------------");
        System.out.println("Введіть ID контракту для поповнення:");
        Long id = scanner.nextLong();
        System.out.println("Введіть суму попповнення:");
        Double sum = scanner.nextDouble();
        try {
            contract_service.Popovnutu(id, sum);
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }
    private void getListOfContracts(){
        System.out.println("------------------------------------");
        System.out.println("Список контрактів:");
        try {
            for (Contract contract : contract_service.getContract()) {
                System.out.println(contract.toString());
            }
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }
    private void poshyk(){
        System.out.println("------------------------------------");
        System.out.println("Введіть ID:");
        Long ID =scanner.nextLong();
        System.out.println("Введіть суму контракту:");
        Double sum =scanner.nextDouble();
        System.out.println("Результат пошуку:");
        try {
            for (Contract contract : contract_service.getContractByContractID_poshyk_all(ID,sum)) {
                System.out.println(contract.toString());
            }
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }
    private void getListOfContractsBySum_more_than(){
        System.out.println("------------------------------------");
        System.out.println("Введіть суму контракту");
        Double sum =scanner.nextDouble();
        System.out.println("Результат пошуку:");
        try {
            for (Contract contract : contract_service.getContractBySum_more_than(sum)) {
                System.out.println(contract.toString());
            }
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }
    private void getListOfContractBySum_less_than(){
        System.out.println("------------------------------------");
        System.out.println("Введіть суму контракту");
        Double sum =scanner.nextDouble();
        System.out.println("Результат пошуку:");
        try {
            for (Contract contract : contract_service.getContractBySum_less_than(sum)) {
                System.out.println(contract.toString());
            }
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }
    private void getListOfContractBySum_poshyk(){
        System.out.println("------------------------------------");
        System.out.println("Введіть суму контракту");
        Double sum =scanner.nextDouble();
        System.out.println("Результат пошуку:");
        try {
            for (Contract contract : contract_service.getContractBySum_poshyk(sum)) {
                System.out.println(contract.toString());
            }
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }
    private boolean Deposit_inter(){
        System.out.println("------------------------------------");
        System.out.println("1-добавити депозит\n" +
                "2-видалити депозит\n"+
                "3-отримати список депозитів\n" +
                "0-вийти");
        int choice= scanner.nextInt();
        switch (choice){
            case 1:
                addDeposit();
                return true;
            case 2:
                removeDeposit();
                return true;
            case 3:
                getListOfDeposits();
                return true;

            case 0:
                return false;
        }
        return false;
    }

    private void addDeposit(){
        System.out.println("------------------------------------");
        System.out.println("Введіть данні:");
        System.out.println("Введіть назву банку:");
        String name = scanner.next();
        System.out.println("Введіть ID:");
        Long bank = scanner.nextLong();
        System.out.println("Введіть мінімальну суму вкладу:");
        Double sum_min = scanner.nextDouble();
        System.out.println("Введіть максимальну суму вкладу:");
        Double sum_max = scanner.nextDouble();
        System.out.println("Введіть налог на прибуток:");
        Double nalog = scanner.nextDouble();
        System.out.println("Введіть к-ть місяців депозиту:");
        Integer termin = scanner.nextInt();
        System.out.println("Введіть процентну ставку:");
        Double interest_rate = scanner.nextDouble();
        try {
            deposit_service.addDeposit(name,bank,sum_min,sum_max,nalog,termin,interest_rate);
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }

    private void removeDeposit(){
        System.out.println("------------------------------------");
        System.out.println("Оберіть депозит, який хочете видалити");
        System.out.println("Введіть ID:");
        long choice= scanner.nextInt();
        try {
            deposit_service.removeDeposit(choice);
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }
    private void getListOfDeposits(){
        System.out.println("------------------------------------");
        try {
            for (Deposit deposit : deposit_service.getDeposit()) {
                System.out.println(deposit.toString());
            }
        } catch (Exception e) {
            System.out.println("Помилка");
        }
    }



    @Override
    public void run(String... args) throws Exception {

    }
    private void configBank(){

        Bank bank1 =new Bank(1L,"Los Pollos Hermanos");
        Bank bank2 =new Bank(2L,"Приват банк");
        Bank bank3=new Bank(3L,"Banco Amigo");
        Bank bank4=new Bank(4L,"Accendo Banco");
        Bank bank5=new Bank(5L,"Albuquerque Bank");

        for (Bank bank :
                List.of(bank1,bank2,bank3,bank4,bank5)) {
            bank_service.addBank(bank);
        }

        deposit_service.addDeposit("Депозит №1",1L,10000.0,50000.500,0.01,12,0.1);
        deposit_service.addDeposit("Депозит №2",4L,20000.0,60000.500,0.01,12,0.15);
        deposit_service.addDeposit("Депозит №3",1L,21000.0,51000.500,0.01,12,0.05);
        deposit_service.addDeposit("Депозит №4",2L,9000.0,75000.500,0.01,12,0.07);
        deposit_service.addDeposit("Депозит №5",3L,1000.0,96000.500,0.01,12,0.05);
        deposit_service.addDeposit("Депозит №6",2L,100000.0,570000.500,0.01,12,0.25);
        deposit_service.addDeposit("Депозит №7",1L,50000.0,1000000.500,0.01,12,0.2);
        deposit_service.addDeposit("Депозит №8",1L,35000.0,100000.500,0.01,12,0.15);
        deposit_service.addDeposit("Депозит №9",5L,21000.0,51000.500,0.01,12,0.1);
        deposit_service.addDeposit("Депозит №10",5L,10000.0,52000.500,0.01,12,0.09);
        deposit_service.addDeposit("Депозит №11",1L,10000.0,50000.500,0.01,12,0.01);
        deposit_service.addDeposit("Депозит №12",3L,1000.0,25000.500,0.01,12,0.08);
        deposit_service.addDeposit("Депозит №13",2L,9000.0,30000.500,0.01,12,0.07);
        deposit_service.addDeposit("Депозит №14",4L,10000.0,150000.500,0.01,12,0.09);

        Client client1 =new Client("Jessy Pinkman",100000.0);
        Client client2 =new Client("Walter White",200000.0);
        Client client3 =new Client("Saul Goodman", 6120000.0);
        Client client4=new Client("Gustavo Fring", 1000000.0);
        for (Client client :
                List.of(client1,client2,client3,client4)) {
            client_service.addClient(client);
        }

        contract_service.addContract(1l,60000.0,2L);
        contract_service.addContract1(2l,30000.0,1L, LocalDate.of(2021,03,15));
        contract_service.addContract(7L,120000.0,3L);
        contract_service.addContract1(7L,300000.0,4L,LocalDate.of(2021,02,16));
        contract_service.addContract(6L,250000.0,3L);
    }

}
