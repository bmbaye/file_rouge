package gestion_dette.services;

import java.util.List;

public interface Service<T>{
    List<T> lister();
    boolean create(T object);
}
