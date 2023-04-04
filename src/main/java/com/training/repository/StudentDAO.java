package com.training.repository;

import com.training.entities.Student;
import com.training.util.DBCon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import static com.training.util.DBCon.con;

//data access object
public class StudentDAO {

     public ArrayList<Student> getAll() throws SQLException {
         ArrayList<Student> slist= new ArrayList<Student>();
         ResultSet r;
         try {
             Statement st = con.createStatement();
             r = st.executeQuery("select * from Student");
             while (r.next()) {
                 int rno = r.getInt(1);
                 String sname = r.getString(2);
                 String branch = r.getString(3);
                 String email = r.getString(4);
                 String phone = r.getString(5);

                 Student s1 = new Student();
                 s1.setRno(rno);
                 s1.setSname(sname);
                 s1.setBranch(branch);
                 s1.setEmail(email);
                 s1.setPhone(phone);
                 slist.add(s1);
                 System.out.println(s1);
             }
         }catch(SQLException e) {
             throw new RuntimeException(e);
         }
         return slist;
    }
    public int addStudent(Student std) {

        String insertQuery = "insert into Student(rno,sname,branch,email,phone) values(?,?,?,?,?)";

        int count = 0;

        try {
            PreparedStatement pstmt = con.prepareStatement(insertQuery);

            pstmt.setInt(1, std.getRno());
            pstmt.setString(2, std.getSname());
            pstmt.setString(3, std.getBranch());
            pstmt.setString(4,std.getEmail());
            pstmt.setString(5,std.getPhone());
            count = pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return count;

    }

    public int getEmployeeById(int rno) {
        Student st = new Student();
        String selectOne = "select rno,sname, branch, email,phone from Student where rno =?";
        try {
            PreparedStatement pstmt = con.prepareStatement(selectOne);
            pstmt.setInt(1, rno);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {

                st.setRno(rs.getInt("rno"));
                st.setSname(rs.getString("sname"));
                st.setBranch(rs.getString("branch"));
                st.setEmail(rs.getString("email"));
                st.setPhone(rs.getString("phone"));
                System.out.println(st);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rno;

    }

    public void updateStud()
    {
        Scanner sc = new Scanner(System.in);
        ResultSet rst;
        try{
            Statement st = con.createStatement();
            System.out.println("Provide student roll no. to update its details:");
            int roll = sc.nextInt();
            rst = st.executeQuery("SELECT * FROM Student WHERE rno="+roll);
            if(!rst.next())
            {
                System.out.println("This roll no.doesn't exist!!");
            }
            else
            {
                sc.nextLine();
                System.out.println("Enter student name: ");
                String sname = sc.next();
                System.out.println("Enter student email: ");
                String email = sc.next();
                String que = "UPDATE student set sname = '" + sname + "', email = '" + email + "'" +
                        " WHERE rno= '" + roll + "' ";
                int ret = st.executeUpdate(que);
                if(ret > 0)
                {
                    System.out.println("Successfully updated!!");
                }
                else {
                    System.out.println("Something went wrong!!");
                }

            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void deleteStudent(int rollno)
    {
        Scanner sc = new Scanner(System.in);
        ResultSet rst;
        try
        {
            Statement stmt = con.createStatement();
            rst = stmt.executeQuery( "SELECT * FROM Student WHERE rno="+rollno);
            if(!rst.next())
            {
                System.out.println("This roll no.doesn't exist!!");
            }
            else
            {
                String que = "delete from Student where rno = '" +rollno+ "' ";
                int ret = stmt.executeUpdate(que);
                if(ret > 0)
                {
                    System.out.println("Successfully deleted!!");
                }
                else {
                    System.out.println("Something went wrong!!");
                }
            }

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }



    }


