package com.rishabh.Librarymanagementsystem.Services;

import com.rishabh.Librarymanagementsystem.Entities.Student;
import com.rishabh.Librarymanagementsystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(Student student) {
        studentRepository.save(student);
        return "Student data has been added to the database";
    }

    public Optional<Student> getStudent(Integer studentId) {
        return studentRepository.findById(studentId);
    }

    public Iterable<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public String updateStudent(int studentId,Student student) {
        studentRepository.deleteById(studentId);
        studentRepository.save(student);
        return "Student data has been updated successfully";
    }

    public String deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
        return "student data deleted successfully";
    }


//    public String updatePartialData(Integer studentId, String name, String branch, Double cgpa, String emailId) {
//        studentRepository.;
//    }
}
