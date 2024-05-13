package net.mvp.back_spring.dtos;

import lombok.*;
import net.mvp.back_spring.entities.PaymentStatus;
import net.mvp.back_spring.entities.PaymentType;

import java.time.LocalDate;

// les annotation lombock
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PaymentDTO {
   // @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // pour les long on utilise ca
    //car c'ets pas une entite jpa
    private  Long id;
    private LocalDate date; // date de poaiement
    private  double montant;
    private PaymentType type; // type de paiement
    private PaymentStatus status; // status du paiement
   // private  String file; // recu du paiement
   // opn supprime @ManyToOne // plusieurs paiement pour un etudiant
   // private Student student; // un paiement appartient a un etudiant

// on creer un mapper : methode qui va nous permettre de transferzer les donnee d'un objet etudiant vers un objet etudiant.dto


}
