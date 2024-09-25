package gestion_dette.core.repository.impl;

import java.util.ArrayList;
import java.util.List;

import gestion_dette.core.repository.Repository;


public class RepositoryListImpl<T> implements Repository<T>{

    public List<T> list =new ArrayList<>();

    @Override
    public List<T> selectAll() {
        return this.list;
    }

    @Override
    public boolean insert(T object) {
        return this.list.add(object);
    }

}
