package gestion_dette.core.repository;

import java.util.List;


public interface Repository<T>{
    List<T> selectAll();
    int insert(T object);
}
