package gestion_dette.datas.repository.bd;

import java.util.List;
import java.util.ArrayList;

import gestion_dette.core.repository.impl.RepositoryBdImpl;
import gestion_dette.datas.entities.Client;
import gestion_dette.datas.repository.ClientRepository;
import gestion_dette.datas.repository.UserRepository;
import gestion_dette.datas.entities.User;

import java.sql.*;

public class ClientRepositoryBd extends RepositoryBdImpl<Client> implements ClientRepository{
    private UserRepository userRepository;
    public ClientRepositoryBd(UserRepository userRepository){
        this.tableName ="client";
        this.userRepository = userRepository;
        try {
            this.getConnexion();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Client getClientByTel(String tel) {
        Client cl =null;
        try {
                String sql =String.format("SELECT * FORM %s where telephone like ?", this.tableName);
                this.initPs(sql);
                this.ps.setString(1, tel);;
                ResultSet rs =this.executeQuery();
    
                if (rs.next()) {
                    cl = this.convertToObject(rs);
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
        return cl;
    }

    @Override
    public Client getClientBYSurname(String surname) {
        Client cl =null;
        try {    
                String sql =String.format("SELECT * FORM %s where telephone like ?", this.tableName) ;
                this.initPs(sql);
                this.ps.setString(1, surname);
                ResultSet rs = this.executeQuery();
    
                if (rs.next()) {
                    cl = this.convertToObject(rs);
                }
        }
        catch (SQLException e) {
            System.out.println("Echec de la connection a la base de donnees");
            e.printStackTrace();
        }finally{
            try {
                this.closeConenxion();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return cl;
    }

     @Override
    public List<Client> selectAll() {
        List<Client> clients = new ArrayList<>();
       try {
        //charger le driver
            String sql =String.format("SELECT * FORM %s", this.tableName) ;
            this.initPs(sql);
            ResultSet rs =this.executeQuery();

            while (rs.next()) {
                clients.add(this.convertToObject(rs));
            }
            rs.close();

            return clients;
            
        }
        catch (SQLException e) {
            System.out.println("Echec de la connection a la base de donnees");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(Client client) {
        int last_id =0;
        try {
             String sql =String.format("INSERT INTO %s (id_client, surname, telephone, adresse, user_id) VALUES (?, ?, ?, ?, ?)", this.tableName) ;
             this.initPs(sql);
             this.setFields(client);
             this.executeUpdate();

             ResultSet rs = this.ps.getGeneratedKeys();
             while (rs.next()) {
                last_id = rs.getInt(1);
             }
             
             return last_id >0;
         }
         catch (SQLException e) {
             e.printStackTrace();
         }
         return false;
    }

    @Override
    public Client convertToObject(ResultSet rs) throws SQLException{
        Client cl = new Client();

        cl.setIdClient(rs.getInt("id_client"));
        cl.setSurname(rs.getString("surname"));
        cl.setTelephone(rs.getString("telephone"));
        cl.setAdresse(rs.getString("adresse"));
        int user_id = rs.getInt("user_id");
        User user = this.userRepository.selectById(user_id);
        cl.setUser(user);
        return cl;
    }
    
}
