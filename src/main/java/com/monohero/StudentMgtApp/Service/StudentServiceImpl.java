package com.monohero.StudentMgtApp.Service;

import com.monohero.StudentMgtApp.Entity.Student;
import com.monohero.StudentMgtApp.Exceptions.StudentAlreadyExistException;
import com.monohero.StudentMgtApp.Exceptions.StudentNotFoundException;
import com.monohero.StudentMgtApp.Repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
    private final StudentRepo studentRepo;

    @Override
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        if (studentAlreadyExists(student.getEmail())){
            throw new StudentAlreadyExistException(student.getEmail()+" Already Exist!");
        }
        return studentRepo.save(student);
    }


    @Override
    public Student updateStudent(Student student, long id) {
        return studentRepo.findById(id).map(student1 -> {
            student1.setFirstName(student.getFirstName());
            student1.setLastName(student.getLastName());
            student1.setEmail(student.getEmail());
            student1.setDepartment(student.getDepartment());
            return studentRepo.save(student1);
        }).orElseThrow(() -> new StudentNotFoundException("Sorry, the student is not found!!!"));
    }


    @Override
    public Student getStudentById(long id) {
        return studentRepo.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Sorry, student ID" +id+" not found"));
    }

    @Override
    public void deleteStudent(long id) {
        if(!studentRepo.existsById(id)){
            throw new StudentNotFoundException("Sorry, student ID" +id+" not found");
        }
        studentRepo.deleteById(id);

    }



    private boolean studentAlreadyExists(String email) {
        return  studentRepo.findByEmail(email).isPresent();
    }
}
