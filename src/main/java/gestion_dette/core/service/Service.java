package gestion_dette.core.service;

import java.util.List;

public interface Service<T>{
    List<T> lister();
    int create(T object);
}
