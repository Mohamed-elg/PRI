package com.bbai.api.service.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbai.api.repository.ContactRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

}
