package gestion_dette.core.repository.impl;

import java.util.List;

import gestion_dette.core.repository.Repository;


public abstract class RepositoryListImpl<T> implements Repository<T>{
    protected List<T> list;

}
