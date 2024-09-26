package gestion_dette.datas.repository;

import gestion_dette.core.repository.Repository;
import gestion_dette.datas.entities.Role;

public interface RoleRepository extends Repository<Role>{
    Role selectByName(String name);
    Role selectById(int id);
}
