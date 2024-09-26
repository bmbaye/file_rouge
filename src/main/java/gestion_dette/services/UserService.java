package gestion_dette.services;


import gestion_dette.core.service.Service;
import gestion_dette.datas.entities.User;

public interface UserService extends Service<User>{
    User findByLogin(String login);
    User findById(int id);
}
