package gestion_dette.core.datasBase.impl;

import java.lang.reflect.Field;
import java.sql.*;

import gestion_dette.core.datasBase.DatasBase;
import gestion_dette.datas.entities.Role;
import gestion_dette.datas.entities.User;
import gestion_dette.datas.entities.enums.Etat;

public class DatasBaseImpl implements DatasBase {
    protected Connection conn = null;
    protected PreparedStatement ps = null;
    private String url = "jdbc:mysql://localhost/fil_rouge";
    private String user = "root";
    private String password = "";

    @Override
    public void getConnexion() throws SQLException {
        if (this.conn ==null || this.conn.isClosed()) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(this.url, this.user, this.password);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void closeConenxion() throws SQLException{
        if(this.conn !=null && !this.conn.isClosed()){
                conn.close();
        }
    }

    @Override
    public ResultSet executeQuery() throws SQLException{
        //Generer les ps.set de maniere reflexive
        return this.ps.executeQuery();
    }

    @Override
    public int executeUpdate() throws SQLException{
        //Generer les ps.set de maniere reflexive
        return this.ps.executeUpdate();
    }

    @Override
    public String generateSql() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateSql'");
    }

    @Override
    public void initPs(String sql) throws SQLException {
        if(sql.toUpperCase().trim().startsWith("INSERT")){
            this.ps = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }else{
            this.ps = this.conn.prepareStatement(sql);
        }
        
    }

    @Override
    public Field[] getClassFields(Object entity) throws IllegalAccessError {
        Class<?> clas = entity.getClass();
        return  clas.getDeclaredFields();
    }

    @Override
    public void setFields(Object entity) throws IllegalArgumentException{
        Field[] fields = this.getClassFields(entity);

        for(int i =0; i<fields.length; i++){
            Field field = fields[i];
            field.setAccessible(true);
            try {
                Object value = field.get(entity);
                if(value instanceof Integer){
                    this.ps.setInt(i+1, (Integer) value);
                }else if(value instanceof String){
                    this.ps.setString(i +1, (String) value);
                }else if(value instanceof User){
                    int user_id = ((User)value).getId_user();
                    this.ps.setInt(i+1, user_id);
                }else if(value instanceof Role){
                    int role_id = ((Role) value).getId();
                    this.ps.setInt(i +1, role_id);
                }else if(value instanceof Etat){
                    this.ps.setString(i+1, ((Etat) value).name());
                }else{
                    this.ps.setNull(i+1, 0);;
                }
            }  catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}
