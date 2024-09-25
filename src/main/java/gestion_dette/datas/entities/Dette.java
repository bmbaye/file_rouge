package gestion_dette.datas.entities;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dette {
    private int idDette;
    private String dateDette;
    private int montantDette;
    private int montantVerse;
    private int montantRestant;
    private int compteId;
}
