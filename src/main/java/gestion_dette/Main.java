package gestion_dette;

import java.util.Scanner;

import gestion_dette.datas.entities.Client;
import gestion_dette.datas.entities.Role;
import gestion_dette.datas.entities.User;
import gestion_dette.datas.entities.enums.Etat;
import gestion_dette.core.Factory.FactoryRepository;
import gestion_dette.services.ClientService;
import gestion_dette.services.RoleService;
import gestion_dette.services.UserService;
import gestion_dette.services.impl.ClientServiceImpl;
import gestion_dette.services.impl.RoleServiceImpl;
import gestion_dette.services.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        FactoryRepository factory = new FactoryRepository();


        UserService userService = new UserServiceImpl(factory.getUserRepositoryInstance());
        ClientService clientService = new ClientServiceImpl(factory.getClientRepositoryInstance());
        RoleService roleService = new RoleServiceImpl(factory.getRoleRepositoryInstance());
        int choix;
        do {
            System.out.println("1.Lister les clients");
            System.out.println("2.Creer client");
            System.out.println("3.Rechercher client par telephone");
            System.out.println("4. Creer un compte client");
            System.out.println("5.Ajouter un compte boutiquier ou admin");
            System.out.println("6.Desactiver ou Activer un compte");
            System.out.println("7.Quitter");
            choix =scan.nextInt();
            switch (choix) {
                case 1->{
                    System.out.println("===========La liste des clients===========");
                    for (Client client : clientService.lister()) {
                        System.out.println("Surname: " + client.getSurname() + "| Telephone: " + client.getTelephone() + "| Adresse: " + client.getAdresse());
                    }
                }
                case 2->{
                    scan.nextLine();
                    String surname ;
                    do {
                        System.out.println("le surname:");
                        surname = scan.nextLine();
                    } while (clientService.findClientBySurname(surname) !=null);
                    String tel;
                    do {
                        System.out.println("Le telephone:");
                        tel = scan.nextLine();
                    } while (clientService.findClientByTelephone(tel) !=null);
                    System.out.println("l'adresse:");
                    String add = scan.nextLine();
                    int c;
                    do {
                        System.out.println("=====Lui creer un compte ? O/N=====");
                        System.out.println("1.OUI");
                        System.out.println( "2.Non");
                        c =scan.nextInt();
                    } while (c !=1 && c!=2);
                    User user = null;
                    if(c ==1){
                        scan.nextLine();
                        System.out.println("le nom:");
                        String nom= scan.nextLine();
                        System.out.println("le prenom:");
                        String prenom = scan.nextLine();
                        System.out.println("Le login:");
                        String login = scan.nextLine();
                        System.out.println("password:");
                        String pass = scan.nextLine();
                        Role role = roleService.findByName("Client");
                        Etat etat = Etat.ACTIVE;
                        int user_id = userService.create(new User(0, nom, prenom, login, pass, role, etat));
                        user =userService.findById(user_id);
                    }
                    Client cl = new Client(0, surname, tel, add, user);
                    clientService.create(cl);

                }
                case 3->{
                    scan.nextLine();
                    System.out.println("Entrez le telephone");
                    String tel = scan.nextLine();
                    Client client = clientService.findClientByTelephone(tel);
                    if(client ==null){
                        System.out.println("Ce client n'existe pas !");
                    }else{
                        System.out.println("Surname: " + client.getSurname() + "| Telephone: " + client.getTelephone() + "| Adresse: " + client.getAdresse());
                    }
                }
                case 4->{
                    scan.nextLine();
                    System.out.println("le nom:");
                    String nom = scan.nextLine();
                    System.out.println("le prenom:");
                    String prenom = scan.nextLine();
                    String login;
                    do {
                        System.out.println("le login:");
                        login = scan.nextLine();
                    } while(userService.findByLogin(login) !=null);
                    System.out.println("le password");
                    String pass = scan.nextLine();
                    Role role = roleService.findByName("Client");
                    String tel;
                    Client cl;
                    do {
                        for (Client client : clientService.lister()) {
                            System.out.println("Surname: " + client.getSurname() + "| Telephone: " + client.getTelephone() + "| Adresse: " + client.getAdresse());
                        }
                        System.out.println("Telephone du client:");
                        tel = scan.nextLine();
                        cl = clientService.findClientByTelephone(tel);
                    } while (cl ==null || cl.getUser() !=null);
                    Etat etat = Etat.ACTIVE;
                    User user = new User(0, nom, prenom, login, pass, role, etat);
                    userService.create(user);
                }
                case 5->{
                    scan.nextLine();
                    String login;
                    System.out.println("le nom:");
                    String nom = scan.nextLine();
                    System.out.println("le prenom:");
                    String prenom = scan.nextLine();
                    do {
                        System.out.println("le login:");
                        login = scan.nextLine();
                    } while(userService.findByLogin(login) !=null);
                    System.out.println("le password");
                    String pass = scan.nextLine();
                    int c;
                    do {
                        System.out.println("1.Boutiquier");
                        System.out.println("2.Admin");
                        System.out.println("Le role ?:");
                        c = scan.nextInt();
                    } while (c <1 && c>2);
                    Role role;
                    if(c ==1){
                        role = new Role(2,"Boutiquier");
                    }else{
                        role =new Role(1,"Admin");
                    }
                    Client cl = null;
                    Etat etat = Etat.ACTIVE;
                    int id = userService.lister().size();
                    User user = new User(id, nom, prenom, login, pass, role, etat);
                    userService.create(user);
                    System.out.println("Compte ajoute avec succes !");
                }
                case 6->{
                    scan.nextLine();
                    String login;
                    User user;
                    do {
                        System.out.println("le login:");
                        login = scan.nextLine();
                        user =userService.findByLogin(login);
                    } while( user ==null);
                    Etat etat;
                    String mess;
                    if(user.getEtat() == Etat.ACTIVE){
                       etat =Etat.DESACTIVE;
                        mess = "Compte desactive !";
                    }else{
                        etat = Etat.ACTIVE;
                        mess = "Compte active !";
                    }
                    user.setEtat(etat);
                    System.out.println(mess);
                }
            }
        } while (choix <7);
        
    }
}