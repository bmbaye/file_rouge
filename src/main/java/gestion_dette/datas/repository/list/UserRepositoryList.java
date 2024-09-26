package gestion_dette.datas.repository.list;

import java.util.List;

import gestion_dette.core.repository.impl.RepositoryListImpl;
import gestion_dette.datas.entities.User;
import gestion_dette.datas.repository.UserRepository;

public class UserRepositoryList extends RepositoryListImpl<User> implements UserRepository{

    @Override
    public User selectByLogin(String login) {
       return this.list.stream().filter(user ->user.getLogin().compareToIgnoreCase(login) ==0).findFirst().orElse(null);
    }

    @Override
    public User selectById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    @Override
    public List<User> selectAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    @Override
    public int insert(User object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }
}
