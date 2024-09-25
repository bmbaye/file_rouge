package gestion_dette.datas.repository;

import gestion_dette.core.repository.Repository;
import gestion_dette.datas.entities.Client;

public interface ClientRepository extends Repository<Client>{
    Client getClientByTel(String tel);
    Client getClientBYSurname(String surname);
}
