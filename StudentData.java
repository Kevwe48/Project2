package com.example.student;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository
public class StudentData {

    private List<Student> students;
    private static StudentData instance;

    public StudentData() throws IOException {
        students = readData();
    }

    public static StudentData getInstance() throws IOException {
        if (instance == null) {
            instance = new StudentData();
        }
        return instance;
    }



    /** http://localhost:8080/name/Dawit
     *
     * @param name Name of the student
     * @return student with name
     * @throws IOException
     */
    public Student getStudentByName(String name) throws IOException {
        if (this.students != null) {
            for (Student student:
                 students) {
              if (student.getFirst_name().equalsIgnoreCase(name)) {
                  return student;
              }
            }
        }
        return null;
    }

    /**
     * http://localhost:8080/student?gpa=3.4 - Part 3&gender=male
     *
     * @param gpa GPA of student
     * @param gender gender of the student
     * @return the student that has gpa and has the gender
     * @throws IOException
     */
    public Student getAllStudentsByGpaAndGender(double gpa, String gender) throws IOException {
        if (this.students != null) {
            for (Student student:
                    students) {
                if (student.getGpa() == gpa && (student.getGender().toLowerCase()).equals(gender.toLowerCase())) {
                    return student;
                }
            }
        }
        return null;
    }

    /** http://localhost:8080/gpa
     *
     * @return average gpa
     * @throws IOException
     */
    public double averageGPA() throws IOException {
        if (this.students != null) {
            double totalGpa = 0.0;
            for (Student student:
                    students) {
                totalGpa += student.getGpa();
            }

            return totalGpa / students.size();
        }
        return Double.NaN;
    }
    /***
     * Read the student.txt file
     * @return all the students
     * @throws IOException exception while reading  file
     */
    private List<Student> readData() throws IOException {
        Scanner scanner = new Scanner(new File("./src/main/resources/student.txt"));
        List<Student> students = new ArrayList<>();

        scanner.nextLine();

        while ( scanner.hasNextLine()){
            String line = scanner.nextLine();

            String[] studentData = line.split(",");
            Student student = new Student();
            student.setId(Integer.parseInt(studentData[0]));
            student.setFirst_name(studentData[1]);
            student.setGpa(Double.parseDouble(studentData[2]));
            student.setEmail(studentData[3]);
            student.setGender(studentData[4]);

            students.add(student);
        }
        return students;
    }
}
