package gestion_dette.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import gestion_dette.core.service.impl.ServiceImpl;
import gestion_dette.datas.entities.User;
import gestion_dette.datas.repository.UserRepository;
import gestion_dette.services.UserService;
import lombok.Getter;

@Getter
public class UserServiceImpl extends ServiceImpl<User> implements UserService{
    protected UserRepository reposiUser;

     public UserServiceImpl(UserRepository userRepository) {
        this.reposiUser = userRepository;
    }

    @Override
    public List<User> lister() {
        return this.reposiUser.selectAll();
    }

    @Override
    public int create(User user) {
        return this.reposiUser.insert(user);
    }

    @Override
    public User findByLogin(String login){
        return this.reposiUser.selectByLogin(login);
    }


    @Override
    public User findById(int id) {
        return this.reposiUser.selectById(id);
    }


}
