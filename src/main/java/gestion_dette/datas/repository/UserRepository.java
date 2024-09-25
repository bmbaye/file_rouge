package gestion_dette.datas.repository;

import gestion_dette.core.repository.Repository;
import gestion_dette.datas.entities.User;

public interface UserRepository extends Repository<User>{
    User selectByLogin(String login);
    User selectById(int id);
}
