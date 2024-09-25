package gestion_dette.datas.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Client {   
    private int idClient;
    private String surname;
    private String telephone;
    private String adresse;
    private User user;
}
