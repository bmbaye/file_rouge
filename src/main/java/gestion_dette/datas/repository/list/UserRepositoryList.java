package gestion_dette.datas.repository.list;

import gestion_dette.core.repository.impl.RepositoryListImpl;
import gestion_dette.datas.entities.User;
import gestion_dette.datas.repository.UserRepository;

public class UserRepositoryList extends RepositoryListImpl<User> implements UserRepository{

    @Override
    public User getUserByLogin(String login) {
       return this.list.stream().filter(user ->user.getLogin().compareToIgnoreCase(login) ==0).findFirst().orElse(null);
    }
}
