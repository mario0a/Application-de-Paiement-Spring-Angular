package net.mvp.back_spring.web;


import net.mvp.back_spring.entities.Payment;
import net.mvp.back_spring.entities.PaymentStatus;
import net.mvp.back_spring.entities.PaymentType;
import net.mvp.back_spring.entities.Student;
import net.mvp.back_spring.repository.PaymentRopository;
import net.mvp.back_spring.repository.StudentRepository;
import net.mvp.back_spring.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*") // pour autoriser l'acces a tous les domaines au front sans security
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRopository paymentRopository;

    private PaymentService paymentService;

    public PaymentRestController(StudentRepository studentRepository, PaymentRopository paymentRopository, PaymentService paymentService) {
        this.studentRepository = studentRepository;
        this.paymentRopository = paymentRopository;
        this.paymentService = paymentService;
    }

    // methode pour consulter la liste de tous les paiement

    @GetMapping(path = "/payments")
    public List<Payment> allPayments(){
        return paymentRopository.findAll();
    }

    // on cherche les paiements d'un etudiant
    @GetMapping(path = "/students/{code}/payments")
    public List<Payment> paymentsByStudent(@PathVariable String  code){
        return paymentRopository.findByStudentCode(code);
    }
//afiicher les paiements par status
    @GetMapping(path = "/payments/byStatus")
    public List<Payment> paymentsByStatus(@RequestParam PaymentStatus status){
        return paymentRopository.findByStatus(status);
    }

    //afiicher les paiements par type
    @GetMapping(path = "/payments/byType")
    public List<Payment> paymentsByType(@RequestParam PaymentType type){
        return paymentRopository.findByType(type);
    }

    // consultation d'un paiement sachant son id
    @GetMapping(path = "/payments/{id}")
    public Payment getPayementId(@PathVariable Long id){
        return paymentRopository.findById(id).get();
    }
    //consultation des paiements
    @GetMapping("/students")

    public  List<Student> allStudents(){
        return  studentRepository.findAll();
    }

    // afficher un etudiant sachant son code
    @GetMapping("/students/code")

    public Student getStudentByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }
    // consultation des etudiants d'une filiere

    @GetMapping(path = "/studentsByprogramId")
    public List<Student> gertStudentsByProgramId( @RequestParam String programId){
        return studentRepository.findByProgramId(programId);
    }

    // permet de mettre a jours le status des paiements
    @PutMapping("/payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id){
        // recuperation du service
       return paymentService.updatePaymentStatus(status, id);
    }

    // methode pour ajouter un paiement avec la gestion des fichiers updload
    //consumes = MediaType.MULTIPART_FORM_DATA_VALUE pour gerer les fichier
    // on recupere le service
    @PostMapping(value = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, LocalDate date, double montant, PaymentType type,
                               String studentCode) throws IOException {
        // recuperation du service

        return this.paymentService.savePayment(file,date,montant,type,studentCode);


    }

    // creation d'une methode qui permet de consulter un fiichier et retoune un tableau de byte
    @GetMapping(path = "/paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
        return paymentService.getPaymentFile(paymentId);

    }


}
