package gestion_dette.core.datasBase;

import java.sql.ResultSet;
import java.lang.reflect.Field;
import java.sql.*;

public interface DatasBase {
    void getConnexion() throws SQLException;
    void closeConenxion() throws SQLException;
    ResultSet executeQuery() throws SQLException;
    int executeUpdate() throws SQLException;
    String generateSql();
    void setFields(Object entity);
    void initPs(String sql) throws SQLException;
    Field[] getClassFileds(Object entity);

}
