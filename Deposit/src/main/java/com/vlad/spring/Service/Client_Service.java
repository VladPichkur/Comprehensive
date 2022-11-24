package com.vlad.spring.Service;

import com.vlad.spring.Dao.Client_Repository;
import com.vlad.spring.Entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class Client_Service {
    private final Client_Repository client_repository;

    @Autowired
    public Client_Service(Client_Repository client_repository) {
        this.client_repository = client_repository;
    }
    @Transactional
    public List<Client> getClient(){
        return client_repository.findAll();
    }

    @Transactional
    public void addClient(Client client){
        Optional<Client> optionalPlayer= client_repository.findById(client.getID());
        if (optionalPlayer.isPresent()){
            throw new IllegalStateException("ID взято");
        }
        client_repository.save(client);
    }

    @Transactional
    public void removeClient(Long clientID){
        boolean exists= client_repository.existsById(clientID);
        if (!exists){
            throw new IllegalStateException("Клієнта з ID "+clientID+" не існує");
        }

        client_repository.deleteById(clientID);
    }

}
