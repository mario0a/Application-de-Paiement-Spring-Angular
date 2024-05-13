package net.mvp.back_spring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
// les annotation lombock
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // pour les long on utilise ca
    private  Long id;
    private LocalDate date; // date de poaiement
    private  double montant;
    private PaymentType type; // type de paiement
    private PaymentStatus status; // status du paiement
    private  String file; // recu du paiement
    @ManyToOne // plusieurs paiement pour un etudiant
    private  Student student; // un paiement appartient a un etudiant




}
