package gestion_dette.services.impl;

import java.util.List;

import gestion_dette.core.service.impl.ServiceImpl;
import gestion_dette.datas.entities.Client;
import gestion_dette.datas.repository.ClientRepository;
import gestion_dette.services.ClientService;
import lombok.Getter;

@Getter
public class ClientServiceImpl extends ServiceImpl<Client> implements ClientService{
    protected ClientRepository reposiClient;


    public ClientServiceImpl(ClientRepository reposiClient) {
        this.reposiClient = reposiClient;
    }

    @Override
    public List<Client> lister() {
        return this.reposiClient.selectAll();
    }

    @Override
    public int create(Client object) {
        return this.reposiClient.insert(object);
    }

    @Override
    public Client findClientByTelephone(String telephone){
        return this.reposiClient.getClientByTel(telephone);
    }
    @Override
    public Client findClientBySurname(String surname){
        return this.reposiClient.getClientBYSurname(surname);
    }
    
}
