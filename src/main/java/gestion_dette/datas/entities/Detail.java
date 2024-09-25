package gestion_dette.datas.entities;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor 
public class Detail {
    private int idDetail;
    private int qte;
    private int prixTotal;
    private int articleId;
    private int detteId;
}
