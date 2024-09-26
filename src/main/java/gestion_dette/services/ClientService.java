package gestion_dette.services;

import gestion_dette.core.service.Service;
import gestion_dette.datas.entities.Client;

public interface ClientService extends Service<Client>{
    Client findClientByTelephone(String telephone);

    Client findClientBySurname(String surname);
}
