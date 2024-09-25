package gestion_dette.services;

import java.util.List;

import gestion_dette.datas.entities.Client;
import gestion_dette.datas.repository.ClientRepository;
import lombok.Getter;

@Getter
public class ClientService extends ServiceImpl<Client> implements Service<Client>{
    protected ClientRepository reposiClient;


    public ClientService(ClientRepository reposiClient) {
        this.reposiClient = reposiClient;
    }

    @Override
    public List<Client> lister() {
        return this.reposiClient.selectAll();
    }

    @Override
    public boolean create(Client object) {
        return this.reposiClient.insert(object);
    }

    public Client findClientByTelephone(String telephone){
        return this.reposiClient.getClientByTel(telephone);
    }

    public Client findClientBySurname(String surname){
        return this.reposiClient.getClientBYSurname(surname);
    }
    
}
