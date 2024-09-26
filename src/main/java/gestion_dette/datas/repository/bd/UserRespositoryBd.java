package gestion_dette.datas.repository.bd;

import gestion_dette.core.repository.impl.RepositoryBdImpl;
import gestion_dette.datas.entities.Role;
import gestion_dette.datas.entities.User;
import gestion_dette.datas.repository.RoleRepository;
import gestion_dette.datas.repository.UserRepository;
import java.util.*;
import java.sql.*;;


public class UserRespositoryBd extends RepositoryBdImpl<User> implements UserRepository{
    private RoleRepository roleRepository;
    public UserRespositoryBd(RoleRepository roleRepository){
        this.tableName ="user";
        this.roleRepository = roleRepository;
    }


    @Override
    public User selectByLogin(String login) {
        User user =null;
        try {
                this.getConnexion();
                String sql =String.format("SELECT * FROM %s where login =  ?", this.tableName);
                
                this.initPs(sql);
                this.ps.setString(1, login);
                ResultSet rs =this.executeQuery();
                if (rs.next()) {
                    user = this.convertToObject(rs);
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
        return user;
    }
    
    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
       try {
        this.getConnexion();
            String sql =String.format("SELECT * FROM %s", this.tableName);
            this.initPs(sql);
            ResultSet rs =this.ps.executeQuery();

            while (rs.next()) {
                users.add(this.convertToObject(rs));
            }

            return users;
            
        } 
        catch (SQLException e) {
            System.out.println("Echec de la connection a la base de donnees");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(User user) {
        int last_id =0;
        try {
            this.getConnexion();
             String sql =String.format("INSERT INTO %s (id_user, nom, prenom, login, password, role_id, etat) VALUES (?, ?, ?, ?, ?, ?, ?)", this.tableName) ;
             this.initPs(sql);
             this.setFields(user);
            
             this.ps.executeUpdate();

             ResultSet rs = this.ps.getGeneratedKeys();
             while (rs.next()) {
                last_id = rs.getInt(1);
             }
             System.out.println("Compte ajoute avec succes !");

         }
         catch (SQLException e) {
             System.out.println("Echecccccccccccccccccccccc");
             e.printStackTrace();
         }
         return last_id;
    }

    @Override
    public User convertToObject(ResultSet rs) throws SQLException{
       User user = new User();

        user.setId_user(rs.getInt("id_user"));
        user.setNom(rs.getString("nom"));
        user.setPrenom(rs.getString("prenom"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        int role_id = rs.getInt("role_id");
        Role role = this.roleRepository.selectById(role_id);
        user.setRole(role);
        return user;
    }

    @Override
    public User selectById(int id) {
        User user =null;
        try {
                this.getConnexion();
                String sql =String.format("SELECT * FROM %s where id_user =  ?", this.tableName);
                
                this.initPs(sql);
                this.ps.setInt(1, id);
                ResultSet rs =this.executeQuery();
                if (rs.next()) {
                    user = this.convertToObject(rs);
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
        return user;
    }
}
