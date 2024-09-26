package gestion_dette.datas.repository.list;

import java.util.List;

import gestion_dette.core.repository.impl.RepositoryListImpl;
import gestion_dette.datas.entities.Client;
import gestion_dette.datas.repository.ClientRepository;
import lombok.Getter;

@Getter
public class ClientRepositoryList extends RepositoryListImpl<Client> implements ClientRepository {

    @Override
    public Client getClientByTel(String tel) {
        return this.list.stream().filter(cl ->cl.getTelephone().compareToIgnoreCase(tel) ==0).findFirst().orElse(null);
    }

    @Override
    public Client getClientBYSurname(String surname) {
        return this.list.stream().filter(cl ->cl.getSurname().compareToIgnoreCase(surname) ==0).findFirst().orElse(null);
    }

    @Override
    public List<Client> selectAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    @Override
    public int insert(Client object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

}
