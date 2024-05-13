package net.mvp.back_spring;

import net.mvp.back_spring.entities.Payment;
import net.mvp.back_spring.entities.PaymentStatus;
import net.mvp.back_spring.entities.PaymentType;
import net.mvp.back_spring.entities.Student;
import net.mvp.back_spring.repository.PaymentRopository;
import net.mvp.back_spring.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class BackSpringApplication {



	public static void main(String[] args) {
		SpringApplication.run(BackSpringApplication.class, args);
	}

	// creation d'un programme de test

	@Bean
		// permet d'executer un programme au demarrage
	CommandLineRunner commandLineRunner(PaymentRopository paymentRopository,
										StudentRepository studentRepository){
		return args -> {
			//.id(UUID.randomUUID().toString()). on specifie l'id parce quil n'est pas auto incrementer
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
							.firstName("mvp").code("1231").programId("Java21")
					.build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("mvp2").code("1200").programId("Java2")
					.build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("mvp3").code("12451").programId("Java30")
					.build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("mvp4").code("1781").programId("Python1")
					.build());

			//creation de piement pour chaque etudiant
			PaymentType[] paymentTypes = PaymentType.values();
			Random random =new Random();
			studentRepository.findAll().forEach(st->{
				for (int i = 0; i < 40; i++) {
					int index = random.nextInt(paymentTypes.length);
					Payment payment = Payment.builder()
							.montant(1000+(int)(Math.random()*20000))
							.type(paymentTypes[index])
							.status(PaymentStatus.CREER)
							.date(LocalDate.now())
							.student(st)
							.build();
					paymentRopository.save(payment);
				}
			});

		};
	}



}
