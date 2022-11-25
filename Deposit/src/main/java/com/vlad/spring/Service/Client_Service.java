package com.vlad.spring.Service;

import com.vlad.spring.Dao.Client_Repository;
import com.vlad.spring.Entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class Client_Service {
    private final Client_Repository client_repository;
    private static final Logger logger= LoggerFactory.getLogger(Client_Service.class);
    @Autowired
    public Client_Service(Client_Repository client_repository) {
        this.client_repository = client_repository;
    }
    @Transactional
    public List<Client> getClient(){
        logger.info("Взято список клієнтів");
        return client_repository.findAll();
    }

    @Transactional
    public void addClient(Client client){
        logger.info("Спроба створення...");
        Optional<Client> optionalPlayer= client_repository.findById(client.getID());
        if (optionalPlayer.isPresent()){
            logger.error("Помилка");
            throw new IllegalStateException("ID взято");
        }
        client_repository.save(client);
        logger.info("Успішно створено!!!");
    }

    @Transactional
    public void removeClient(Long clientID){
        logger.info("Спроба видалення...");
        boolean exists= client_repository.existsById(clientID);
        if (!exists){
            logger.error("Помилка");
            throw new IllegalStateException("Клієнта з ID "+clientID+" не існує");
        }
        client_repository.deleteById(clientID);
        logger.info("Успішно видалено!!!");
    }

}
