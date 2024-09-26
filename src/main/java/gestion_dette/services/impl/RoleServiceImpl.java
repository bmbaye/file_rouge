package gestion_dette.services.impl;

import java.util.List;

import gestion_dette.core.service.impl.ServiceImpl;
import gestion_dette.datas.entities.Role;
import gestion_dette.datas.repository.RoleRepository;
import gestion_dette.services.RoleService;

public class RoleServiceImpl extends ServiceImpl<Role> implements RoleService{
    protected RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> lister() {
        return this.roleRepository.selectAll();
    }

    @Override
    public int create(Role object) {
       return this.roleRepository.insert(object);
    }

    @Override
    public Role findByName(String name) {
        return this.roleRepository.selectByName(name);
    }
    
}
