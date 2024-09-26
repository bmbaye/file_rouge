package gestion_dette.datas.repository.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gestion_dette.core.repository.impl.RepositoryBdImpl;
import gestion_dette.datas.entities.Role;
import gestion_dette.datas.repository.RoleRepository;

public class RoleRepositoryBd extends RepositoryBdImpl<Role> implements RoleRepository{

    public RoleRepositoryBd(){
        this.tableName ="role";
    }

    @Override
    public List<Role> selectAll() {
        List<Role> roles = new ArrayList<>();
       try {
        this.getConnexion();
            String sql =String.format("SELECT * FROM %s", this.tableName) ;
            this.initPs(sql);
            ResultSet rs =this.executeQuery();
            while (rs.next()) {
                roles.add(this.convertToObject(rs));
            }
            rs.close();

            return roles;
        }
        catch (SQLException e) {
            System.out.println("Echec de la connection a la base de donnees");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(Role user) {
        int last_id =0;
        try {
            this.getConnexion();
             String sql =String.format("INSERT INTO %s (id_role, nomRole) VALUES (?, ?)", this.tableName) ;
             this.initPs(sql);
             this.setFields(user);
             this.executeUpdate();

             ResultSet rs = this.ps.getGeneratedKeys();
             while (rs.next()) {
                last_id = rs.getInt(1);
             }
             
         }
         catch (SQLException e) {
             e.printStackTrace();
         }
         return last_id;
    }

    @Override
    public Role selectByName(String name) {
       Role role = new Role();
       try {
        this.getConnexion();
            String sql = String.format("SELECT * FROM %s WHERE nomRole like ?", this.tableName);
            this.initPs(sql);
            this.ps.setString(1, name);
            ResultSet rs = this.ps.executeQuery();

            while (rs.next()) {
                role = this.convertToObject(rs);
            }
        }catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return role;
}

    @Override
    public Role convertToObject(ResultSet rs) throws SQLException {
        Role role = new Role();

        role.setId(rs.getInt("id_role"));;
        role.setNomRole(rs.getString("nomROle"));

        return role;
    }

    @Override
    public Role selectById(int id) {
        Role role =null;
        try {
                this.getConnexion();
                String sql =String.format("SELECT * FROM %s where id_role =  ?", this.tableName);
                
                this.initPs(sql);
                this.ps.setInt(1, id);
                ResultSet rs =this.executeQuery();
                if (rs.next()) {
                    role = this.convertToObject(rs);
                }
        }
        catch (SQLException e) {
            System.out.println("Echec de la connection a la base de donnees");
            e.printStackTrace();
        }
        finally{
            try {
                this.closeConenxion();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return role;
    }
    
}
