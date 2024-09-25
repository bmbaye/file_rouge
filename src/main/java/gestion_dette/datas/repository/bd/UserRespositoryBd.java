package gestion_dette.datas.repository.bd;

import gestion_dette.core.repository.impl.RepositoryBdImpl;
import gestion_dette.datas.entities.Client;
import gestion_dette.datas.entities.User;
import gestion_dette.datas.repository.UserRepository;
import java.util.*;
import java.sql.*;;

public class UserRespositoryBd extends RepositoryBdImpl<User> implements UserRepository{

    public UserRespositoryBd(){
        this.tableName = "user";
        //charger le driver
        try {
            this.getConnexion();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public User selectByLogin(String login) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserByLogin'");
    }
    
    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        Connection conn = null;
       try {

            String sql =String.format("SELECT * FORM %s", this.tableName);
            Statement stmt = null;
            ResultSet rs =null;

            stmt =conn.createStatement();
            rs =stmt.executeQuery(sql);

            while (rs.next()) {
                int id_user =rs.getInt("id_user");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String login = rs.getString("login");
                String pass = rs.getString("password");
                // int role_id =rs.getInt("role_id");
                int etat =rs.getInt("etat");

                User user = new User(id_user, nom, prenom, login, pass, null, etat);
                users.add(user);
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
    public boolean insert(User user) {
        Connection conn = null;
        int last_id =0;
        try {
         //charger le driver
         Class.forName("org.postgresql.Driver");
 
         //etablir la connexion
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Bmbaye-2400");
             
             String sql =String.format("INSERT INTO %s (id_user, nom, prenom, login, password, role_id, etat) VALUES (?, ?, ?, ?, ?, ?, ?)", this.tableName) ;
             PreparedStatement preStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             preStmt.setInt(1, 0);
             preStmt.setString(2, user.getNom());
             preStmt.setString(3, user.getPrenom());
             preStmt.setString(4, user.getLogin());
             preStmt.setString(5, user.getPassword());
             preStmt.setInt(6, 1);
             preStmt.setInt(7, 1);
            
             preStmt.executeUpdate();

             ResultSet rs = preStmt.getGeneratedKeys();
             while (rs.next()) {
                last_id = rs.getInt(1);
             }
             System.out.println("Compte ajoute avec succes !");

             return last_id >0;
         } catch (ClassNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             System.out.println("impossible de charger le driver");
         }
         catch (SQLException e) {
             System.out.println("Echecccccccccccccccccccccc");
             e.printStackTrace();
         }
         return false;
    }

    @Override
    public User convertToObject(ResultSet rs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToObject'");
    }

    @Override
    public User selectById(int id) {
        User user =null;
        try {
                this.getConnexion();
                String sql =String.format("SELECT * FORM %s where id_user =  ?", this.tableName);
                
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
