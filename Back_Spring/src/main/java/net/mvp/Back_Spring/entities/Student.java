package net.mvp.back_spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

// pour que ce soit une entité jpa on met les annotations
@Entity
// les annotation lombock
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder

public class Student {
    @Id   // pour que ce soit une entité jpa on met les annotations
    private String id;
    private  String firstName; //nom de l'etudiant'
    private String lastName; // prenom de l'etudiant
    @Column(unique = true)  // pour ne pas avoir les meme etudiant avec le meme code
    private String code; // code de l'etudiant
    private String programId; // filiere de l'etudiant
    private String photo; // ici on va stoquer le chemin de la photo de l'etudiant
}
