package gestion_dette.datas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Paiement {
    private int idPaiement;
    private String datePaiement;
    private int montantPaiement;
    private int detteId;
}
