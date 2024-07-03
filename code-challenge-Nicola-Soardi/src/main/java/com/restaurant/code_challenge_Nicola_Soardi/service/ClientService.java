package com.restaurant.code_challenge_Nicola_Soardi.service;


import com.restaurant.code_challenge_Nicola_Soardi.entity.Client;

public interface ClientService {
    public Client getClient(Long id);
    public Client saveClient(Client client);
    public void deleteClient(Long id);
}
