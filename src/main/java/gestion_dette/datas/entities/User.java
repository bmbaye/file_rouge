package gestion_dette.datas.entities;
import gestion_dette.datas.entities.enums.Etat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id_user;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private Role role;
    private Etat etat;
}
