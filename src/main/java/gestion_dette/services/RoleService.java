package gestion_dette.services;

import gestion_dette.core.service.Service;
import gestion_dette.datas.entities.Role;

public interface RoleService extends Service<Role>{
    Role findByName(String name);
}
