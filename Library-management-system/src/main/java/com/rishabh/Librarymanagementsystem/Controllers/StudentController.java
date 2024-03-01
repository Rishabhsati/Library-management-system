package com.rishabh.Librarymanagementsystem.Controllers;

import com.rishabh.Librarymanagementsystem.Entities.Student;
import com.rishabh.Librarymanagementsystem.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity addStudent(@RequestBody Student student){
        String result = studentService.addStudent(student);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/getStudent")
    public Optional<Student> getStudent(@RequestParam("studentId")Integer studentId){
        return studentService.getStudent(studentId);
    }

    @GetMapping("/getAllStudent")
    public @ResponseBody Iterable<Student> getAllUsers() {
        // This returns a JSON or XML with the users
        return studentService.getAllStudent();
    }

    @PutMapping("/updateStudent")
    public ResponseEntity updateStudent(@RequestParam("studentId")Integer studentId,@RequestBody Student student){
        String result = studentService.updateStudent(studentId,student);
        return new ResponseEntity(result,HttpStatus.OK);
    }

//    @PatchMapping("/updatePartialData")
//    public ResponseEntity updatePartialData(@RequestParam("studentId")Integer studentId,
//                                            @RequestParam(value = "name",required = false)String name,
//                                            @RequestParam(value = "branch",required = false)String branch,
//                                            @RequestParam(value = "cgpa",required = false)Double cgpa,
//                                            @RequestParam(value = "emailId",required = false)String emailId){
//        String result = studentService.updatePartialData(studentId,name,branch,cgpa,emailId);
//        return new ResponseEntity(result,HttpStatus.OK);
//    }

    @DeleteMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId")Integer studentId){
        String result = studentService.deleteStudent(studentId);
        return result;
    }
}
