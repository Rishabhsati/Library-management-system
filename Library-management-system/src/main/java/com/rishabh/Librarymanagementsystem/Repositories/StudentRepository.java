package com.rishabh.Librarymanagementsystem.Repositories;

import com.rishabh.Librarymanagementsystem.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
