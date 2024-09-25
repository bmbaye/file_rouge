package gestion_dette.datas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article {
    private int idArticle;
    private String libelle;
    private String reference;
    private int qteStock;
    private int prix;
}
