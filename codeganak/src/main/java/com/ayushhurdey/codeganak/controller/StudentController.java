package com.ayushhurdey.codeganak.controller;

import com.ayushhurdey.codeganak.model.bo.Student;
import com.ayushhurdey.codeganak.model.repositories.StudentRepository;
import com.ayushhurdey.codeganak.model.request.StudentAddRequest;
import com.ayushhurdey.codeganak.model.request.StudentUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/student")
public class StudentController {

        @Autowired
        private StudentRepository studentRepository;

        @PostMapping(path="/")
        public ResponseEntity<String> addNewUser (@RequestBody StudentAddRequest studentAddRequest){
            Student student = new Student();
            student.setName(studentAddRequest.getName());
            studentRepository.save(student);
            return ResponseEntity.ok().body("Student added successfully.");
        }

        @GetMapping(path="/")
        public ResponseEntity<Iterable<Student>> getAllUsers() {
            return ResponseEntity.ok().body(studentRepository.findAll());
        }

        @GetMapping(path="/{rollNo}")
        public ResponseEntity<Optional<Student>> getStudent(@PathVariable("rollNo") int rollNo) {
            return ResponseEntity.ok().body(studentRepository.findById(rollNo));
        }

        @PutMapping(path="/")
        public ResponseEntity<String> updateStudent(@RequestBody StudentUpdateRequest studentUpdateRequest) {
            Student student = new Student();
            student.setRollNo(studentUpdateRequest.getRollNo());
            student.setName(studentUpdateRequest.getName());
            studentRepository.save(student);
            return ResponseEntity.ok().body("Student updated successfully.");
        }

        @DeleteMapping(path="/{rollNo}")
        public ResponseEntity<String> deleteStudent(@PathVariable("rollNo") int rollNo) {
            studentRepository.deleteById(rollNo);
            return ResponseEntity.ok().body("deleted successfully.");
        }
}
