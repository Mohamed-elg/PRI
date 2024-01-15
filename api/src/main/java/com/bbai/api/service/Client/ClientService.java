package com.bbai.api.service.Client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbai.api.model.Account.ERole;
import com.bbai.api.model.Client.ClientModel;
import com.bbai.api.service.Account.accountService;
import com.bbai.api.repository.ClientRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private accountService accountService;

    public Optional<ClientModel> getClientbyId(String token, long ClientId) {
        return clientRepository.findById(ClientId);
    }

    public Optional<ClientModel> getClientbyRef(String token, String ClientRef) {
        return clientRepository.findByRef(ClientRef);
    }

    public Optional<ClientModel> getClientbyNom(String token, String ClientNom) {
        return clientRepository.findByNom(ClientNom);
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
