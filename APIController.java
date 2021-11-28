package com.example.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = "/")
@RestController
public class APIController {

    @Autowired
    StudentData studentData;

    @GetMapping("/name/{name}")
    public ResponseEntity getStudentsData(@PathVariable String name) {
        try {
            return new ResponseEntity(studentData.getStudentByName(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/gpa")
    public ResponseEntity getAverageGPA() {
        try {
            return new ResponseEntity(studentData.averageGPA(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/students")
    public ResponseEntity getStudentsGrade(@RequestParam double gpa, @RequestParam String gender) {
        try {
            return new ResponseEntity(studentData.getAllStudentsByGpaAndGender(gpa, gender), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
