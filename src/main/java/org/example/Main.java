package org.example;
import com.training.entities.Student;
import com.training.repository.StudentDAO;
import com.training.util.DBCon;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc= new Scanner(System.in);
        System.out.println("Hello world!");
        DBCon.getConnection();
        StudentDAO dao=new StudentDAO();

        Student std =new Student(107,"asd","ME","hgsg@gmail","89646");
        dao.addStudent(std);
        dao.getAll();
        dao.getEmployeeById(101);
        dao.updateStud();
        System.out.println("Enter roll number of student to delete its entry:");
        int roll = sc.nextInt();
        dao.deleteStudent(roll);

}
}
