package com.monohero.StudentMgtApp.Service;


import com.monohero.StudentMgtApp.Entity.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);

    List<Student> getStudents();

    Student updateStudent(Student student, long id);

    Student getStudentById(long id);

    void deleteStudent(long id);

}
