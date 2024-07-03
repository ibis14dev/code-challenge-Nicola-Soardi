package com.restaurant.code_challenge_Nicola_Soardi.service;

import com.restaurant.code_challenge_Nicola_Soardi.entity.Client;
import com.restaurant.code_challenge_Nicola_Soardi.exception.ClientNotFoundException;
import com.restaurant.code_challenge_Nicola_Soardi.exception.EmailExceprion;
import com.restaurant.code_challenge_Nicola_Soardi.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;

    @Override
    public Client getClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Override
    public Client saveClient(Client client) {
        try {
            return clientRepository.save(client);
        } catch (DataIntegrityViolationException e) {
            throw new EmailExceprion(client.getEmail());
        }
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}


