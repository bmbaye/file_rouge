package gestion_dette.core.Factory;

import gestion_dette.datas.repository.ClientRepository;
import gestion_dette.datas.repository.RoleRepository;
import gestion_dette.datas.repository.UserRepository;
import gestion_dette.datas.repository.bd.ClientRepositoryBd;
import gestion_dette.datas.repository.bd.RoleRepositoryBd;
import gestion_dette.datas.repository.bd.UserRespositoryBd;


public class FactoryRepository {
    private ClientRepository clientRepository = null;
    private UserRepository userRepository = null;
    private RoleRepository roleRepository =null;

    public ClientRepository getClientRepositoryInstance(){
        if(this.clientRepository == null){
            this.clientRepository = new ClientRepositoryBd(getUserRepositoryInstance());
        }
        return this.clientRepository;
    }

    public UserRepository getUserRepositoryInstance(){
        if(this.userRepository == null){
            this.userRepository = new UserRespositoryBd(getRoleRepositoryInstance());
        }
        return this.userRepository;
    }

    public RoleRepository getRoleRepositoryInstance(){
        if(this.roleRepository ==null){
            this.roleRepository = new RoleRepositoryBd();
        }
        return this.roleRepository;
    }

}
