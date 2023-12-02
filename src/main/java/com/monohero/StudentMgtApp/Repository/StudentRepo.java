package com.monohero.StudentMgtApp.Repository;

import com.monohero.StudentMgtApp.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);
}
