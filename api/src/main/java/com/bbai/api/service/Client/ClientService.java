package com.bbai.api.service.Client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbai.api.model.Client.ClientModel;
import com.bbai.api.repository.ClientRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Optional<ClientModel> getClient(long ClientId) {
        return clientRepository.findById(ClientId);
    }

    public ClientModel addClient(ClientModel client) {
        return clientRepository.save(client);
    }

}
