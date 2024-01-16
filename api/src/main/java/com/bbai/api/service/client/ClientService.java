package com.bbai.api.service.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbai.api.model.account.ERole;
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

    public Optional<ClientModel> getClientbyRef(String token, String ClientRef) {
        return clientRepository.findByRef(ClientRef);
    }

    public Optional<ClientModel> getClientbyNom(String token, String ClientNom) {
        return clientRepository.findByNom(ClientNom);
    }

    public ClientModel updateClientbyId(String token, long ClientId, ClientModel client) {
        Optional<ClientModel> old_client = clientRepository.findById(ClientId);
        if (old_client.isPresent()) {
            client.setId(old_client.get().getId());
            clientRepository.save(client);
            return client;
        }
        return old_client.get();
    }

    public ClientModel createClient(String token, ClientModel client) {
        ERole temp = accountService.getAccountByToken(token).get().getRole();
        if (temp == ERole.USER || temp == ERole.MANAGER || temp == ERole.ADMIN) {
            return clientRepository.save(client);
        } else {
            return null;
        }
    }

}
