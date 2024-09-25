package gestion_dette;

import java.util.Scanner;

import gestion_dette.datas.entities.Client;
import gestion_dette.datas.entities.Role;
import gestion_dette.datas.entities.User;
import gestion_dette.core.Factory.FactoryRepository;
import gestion_dette.services.ClientService;
import gestion_dette.services.UserService;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        FactoryRepository factory = new FactoryRepository();


        UserService userService = new UserService(factory.getUserRepositoryInstance());
        ClientService clientService = new ClientService(factory.getClientRepositoryInstance());
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
                    System.out.println("le surname:");
                    String name = scan.nextLine();
                    // do {
                    //     System.out.println("le surname:");
                    //     name = scan.nextLine();
                    // } while(clientService.getReposiClient().getClientBYSurname(name) !=null);
                    System.out.println("le telephone:");
                    String tel = scan.nextLine();
                    // do {
                    //     System.out.println("le telephone:");
                    //     tel = scan.nextLine();
                    // } while(clientService.getReposiClient().getClientByTel(tel) !=null);
                    System.out.println("Entrez l'adresse");
                    String ad = scan.nextLine();
                    // int id =clientService.getReposiClient().selectAll().size() +1;
                    int id =0;
                    Client cl = new Client(id, name, tel, ad, null);
                    clientService.create(cl);
                }
                case 3->{
                    scan.nextLine();
                    System.out.println("Entrez le telephone");
                    String tel = scan.nextLine();
                    Client cl = clientService.findClientByTelephone(tel);
                    cl.toString();
                    if(cl ==null){
                        System.out.println("Ce client n'existe pas !");
                    }else{
                        System.out.println("Surname: " + cl.getSurname() + "| Telephone: " + cl.getTelephone() + "| Adresse: " + cl.getAdresse());
                    }
                }
                case 4->{
                    scan.nextLine();
                    System.out.println("le nom:");
                    String nom = scan.nextLine();
                    System.out.println("le prenom:");
                    String prenom = scan.nextLine();
                    System.out.println("le login:");
                    String login = scan.nextLine();
                    System.out.println("le password");
                    String pass = scan.nextLine();
                    // do {
                    //     System.out.println("le login:");
                    //     login = scan.nextLine();
                    // } while(userService.findUserByLogin(login) !=null);
                    
                    Role role = new Role("Client");
                    // String tel;
                    // Client cl;
                    // do {
                    //     for (Client client : clientService.lister()) {
                    //         System.out.println("Surname: " + client.getSurname() + "| Telephone: " + client.getTelephone() + "| Adresse: " + client.getAdresse());
                    //     }
                    //     System.out.println("Telephone du client:");
                    //     tel = scan.nextLine();
                    //     cl = clientService.getReposiClient().getClientByTel(tel);
                    // } while (cl ==null);
                    int etat = 1;
                    // int id = userService.lister().size() + 10;
                    User user = new User(1, nom, prenom, login, pass, role, etat);
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
                    } while(userService.findUserByLogin(login) !=null);
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
                        role = new Role("Boutiquier");
                    }else{
                        role =new Role("Admin");
                    }
                    Client cl = null;
                    int etat = 1;
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
                        user =userService.findUserByLogin(login);
                    } while( user ==null);
                    int etat;
                    String mess;
                    if(user.getEtat() == 1){
                       etat =0;
                        mess = "Compte desactive !";
                    }else{
                        etat = 1;
                        mess = "Compte active !";
                    }
                    user.setEtat(etat);
                    System.out.println(mess);
                }
            }
        } while (choix <7);
        
    }
}