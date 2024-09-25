package gestion_dette.core.Factory;

import gestion_dette.datas.repository.ClientRepository;
import gestion_dette.datas.repository.UserRepository;
import gestion_dette.datas.repository.bd.ClientRepositoryBd;
import gestion_dette.datas.repository.bd.UserRespositoryBd;


public class FactoryRepository {
    private ClientRepository clientRepository = null;
    private UserRepository userRepository = null;

    public ClientRepository getClientRepositoryInstance(){
        if(this.clientRepository == null){
            this.clientRepository = new ClientRepositoryBd(getUserRepositoryInstance());
        }
        return this.clientRepository;
    }

    public UserRepository getUserRepositoryInstance(){
        if(this.userRepository == null){
            this.userRepository = new UserRespositoryBd();
        }
        return this.userRepository;
    }

}
