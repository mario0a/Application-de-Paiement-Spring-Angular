package net.mvp.back_spring.services;

import net.mvp.back_spring.entities.Payment;
import net.mvp.back_spring.entities.PaymentStatus;
import net.mvp.back_spring.entities.PaymentType;
import net.mvp.back_spring.entities.Student;
import net.mvp.back_spring.repository.PaymentRopository;
import net.mvp.back_spring.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional// dans un service, toutes les methodes doivent etre transactionnelles
public class PaymentService {
    //on injecte
    private StudentRepository studentRepository;
    private PaymentRopository paymentRopository;

    //injecter les constructeurs


    public PaymentService(StudentRepository studentRepository, PaymentRopository paymentRopository) {
        this.studentRepository = studentRepository;
        this.paymentRopository = paymentRopository;
    }


    // methode pour ajouter un paiement avec la gestion des fichiers updload
    //consumes = MediaType.MULTIPART_FORM_DATA_VALUE pour gerer les fichier
    public Payment savePayment(MultipartFile file, LocalDate date, double montant, PaymentType type,
                               String studentCode) throws IOException {
        // recuperation du chemin vers le fichier
        Path folderPath = Paths.get(System.getProperty("user.home"),"enset-data","payments");
        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        //on cree un nom de fichier unique
        String fileName = UUID.randomUUID().toString(); //on exige les pdf
        Path filePath = Paths.get(System.getProperty("user.home"),"enset-data","payments",fileName+".pdf");
        Files.copy(file.getInputStream(),filePath);
        Student student = studentRepository.findByCode(studentCode);
        Payment payment = Payment.builder()
                .date(date).type(type).student(student)
                .montant(montant)
                .file(filePath.toUri().toString())
                .status(PaymentStatus.CREER)
                .build();

        return paymentRopository.save(payment);

    }
    // permet de mettre a jours le status des paiements
    public Payment updatePaymentStatus( PaymentStatus status,Long id) {
        Payment payment = paymentRopository.findById(id).get();
        payment.setStatus(status);
        return paymentRopository.save(payment);
    }

    // creation d'une methode qui permet de consulter un fiichier et retoune un tableau de byte
    public byte[] getPaymentFile( Long paymentId) throws IOException {
        Payment payment = paymentRopository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }

}
