package gestion_dette.services;


import java.util.List;
import java.util.stream.Collectors;

import gestion_dette.datas.entities.User;
import gestion_dette.datas.repository.UserRepository;
import lombok.Getter;

@Getter
public class UserService extends ServiceImpl<User> implements Service<User>{
    protected UserRepository reposiUser;

     public UserService(UserRepository userRepository) {
        this.reposiUser = userRepository;
    }

    @Override
    public List<User> lister() {
        return this.reposiUser.selectAll();
    }

    @Override
    public boolean create(User user) {
        return this.reposiUser.insert(user);
    }

    public User findUserByLogin(String login){
        return this.reposiUser.getUserByLogin(login);
    }

    public List<User> findUsersByRole(String role){
        List<User> list = this.lister();
        return list.stream().filter(user -> user.getRole().getNomRole().compareToIgnoreCase(role) ==0).collect(Collectors.toList());
    }

    public List<User> findUserByEtat(int etat){
        List<User> list = this.lister();
        return list.stream().filter(user ->user.getEtat() == 0).collect(Collectors.toList());
    }


}
