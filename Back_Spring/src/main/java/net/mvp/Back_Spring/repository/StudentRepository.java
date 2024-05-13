package net.mvp.back_spring.repository;

import net.mvp.back_spring.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,String>{
    // cette methode permet de rechecher un etudiant par son code
    Student findByCode(String code);
    //rechercher uj etudiant par sa filiere
    List<Student> findByProgramId(String programId);

}
