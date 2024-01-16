package com.bbai.api.service.client;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbai.api.model.client.ClientModel;
import com.bbai.api.repository.ClientRepository;
import com.bbai.api.service.account.AccountService;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountService accountService;

    public Optional<ClientModel> getClientbyId(String token, long ClientId) {
        return clientRepository.findById(ClientId);
    }

    public void deleteClientbyId(String token, long ClientId) {
        ClientModel client = clientRepository.findById(ClientId).get();
        clientRepository.delete(client);
    }

    public Optional<ClientModel> getClientbyRef(String token, String ClientRef) {
        return clientRepository.findByRef(ClientRef);
    }

    public Optional<ClientModel> getClientbyNom(String token, String ClientNom) {
        return clientRepository.findByNom(ClientNom);
    }

    public ClientModel updateClientbyId(String token, long ClientId, ClientModel client) throws AccessDeniedException {
        if (accountService.getAccountByToken(token).isPresent()) {
            Optional<ClientModel> old_client = clientRepository.findById(ClientId);
            if (old_client.isPresent()) {
                client.setId(old_client.get().getId());
                clientRepository.save(client);
                return client;
            }
            return old_client.get();
        } else {
            throw new AccessDeniedException("Access is forbidden: invalid token");
        }
    }

    public ClientModel createClient(String token, ClientModel client) throws AccessDeniedException {
        if (accountService.getAccountByToken(token).isPresent()) {
            return clientRepository.save(client);
        } else {
            throw new AccessDeniedException("Access is forbidden: invalid token");
        }
    }

}
