/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.classroster.dao;

import com.raviudit.classroster.dto.Student;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author raviu
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao{
    
    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";

    @Override
    //public Student addStudent(String studentId, Student student) {
    //   Student prevStudent = students.put(studentId, student);
    //   return prevStudent;
    //}
    public Student addStudent(String studentId, Student student) throws ClassRosterDaoException {
        loadRoster();
        Student newStudent = students.put(studentId, student);
        writeRoster();
        return newStudent;
    }

    @Override
    // public List<Student> getAllStudents() {
    //   return new ArrayList<Student>(students.values());
    //}
    public List<Student> getAllStudents() throws ClassRosterDaoException {
        loadRoster();
        return new ArrayList(students.values());
    }

    @Override
    //public Student getStudent(String studentId) {
    //    return students.get(studentId);
    //}
    public Student getStudent(String studentId) throws ClassRosterDaoException {
        loadRoster();
        return students.get(studentId);
    }

    @Override
    //public Student removeStudent(String studentId) {
    //   Student removedStudent = students.remove(studentId);
    //   return removedStudent;
    //}
    public Student removeStudent(String studentId) throws ClassRosterDaoException {
        loadRoster();
        Student removedStudent = students.remove(studentId);
        writeRoster();
        return removedStudent;
    }
    
    
    private Student unmarshallStudent(String studentAsText){
        
        //students are saved in file as [studentID]::[First Name]::[Last Name]::[cohort]
        //This sepreates the input from the file ionto seperate strings using DELIMITER to specify where each string ends.
        //The array store the values as studentTokens[0] = studentID
        //                              studentTokens[1] = First Name
        //                              studentTokens[2] = Last Name
        //                              studentTokens[3] = Cohort
        String[] studentTokens = studentAsText.split(DELIMITER);
        
        String studentId = studentTokens[0];
        
        //Creating a new Student Object and passing in the StudentID. 
        Student studentFromFile = new Student(studentId);
        
        studentFromFile.setFirstName(studentTokens[1]);
        studentFromFile.setLastName(studentTokens[2]);
        studentFromFile.setCohort(studentTokens[3]);
        
        return studentFromFile;        
    }
    
    private void loadRoster() throws ClassRosterDaoException{
        Scanner scanner;
        
        try{
            //Create scanner for  reading file
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        }catch (FileNotFoundException e){
            throw new ClassRosterDaoException("Coule Not Load File.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        Student currentStudent;
      
        // Go through ROSTER_FILE line by line, decoding each line into a 
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentStudent = unmarshallStudent(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            students.put(currentStudent.getStudentId(), currentStudent);
        }
        // close scanner
        scanner.close();
        
    }
    
    // Turns a students into a line to write to file
    private String marshallStudent(Student aStudent){
        
        //set studentID as first property
        String studentAsText = aStudent.getStudentId() + DELIMITER;
        
        //set remaining items in tehir respective properties
        studentAsText += aStudent.getFirstName() + DELIMITER;
        studentAsText += aStudent.getLastName() + DELIMITER;
        studentAsText += aStudent.getCohort();
        
        return studentAsText;
    }
    
    private void writeRoster() throws ClassRosterDaoException{
        
        PrintWriter out; 
        
        try{
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e){
            throw new ClassRosterDaoException("Could not save data", e);
        }
        
        String studentAsText;
        //Modification of writeRoster section of step 11. 
        //Original Line was: List studentList = this.getAllStudents();
        List<Student> studentList = this.getAllStudents();
        for (Student currentStudent : studentList){
            // turn a Student into a String
            studentAsText = marshallStudent(currentStudent);
            // write the Student object to the file
            out.println(studentAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
    
    private Map < String, Student> students = new HashMap<>();
    
}
