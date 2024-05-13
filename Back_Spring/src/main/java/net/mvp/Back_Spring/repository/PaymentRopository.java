package net.mvp.back_spring.repository;

import net.mvp.back_spring.entities.Payment;
import net.mvp.back_spring.entities.PaymentStatus;
import net.mvp.back_spring.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRopository extends JpaRepository<Payment,Long> {
    // permet de rechercher la liste des paiements par etudiant
    List<Payment> findByStudentCode(String code);
    // recherche les paiement par status
    List<Payment> findByStatus(PaymentStatus status);

    // liste des paiement par type
    List<Payment> findByType(PaymentType type);

}
