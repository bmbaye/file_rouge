package gestion_dette.core.repository.impl;


import java.sql.ResultSet;
import java.sql.SQLException;

import gestion_dette.core.datasBase.impl.DatasBaseImpl;
import gestion_dette.core.repository.Repository;

public abstract class RepositoryBdImpl<T> extends DatasBaseImpl implements Repository<T>{
    protected String tableName;
    public abstract T convertToObject(ResultSet rs) throws SQLException;
}
