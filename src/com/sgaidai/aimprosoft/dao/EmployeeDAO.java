package com.sgaidai.aimprosoft.dao;

import com.sgaidai.aimprosoft.models.Employee;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private Connection con;

    public EmployeeDAO(Connection con) {
        this.con = con;
    }
    public void create(Employee emp) throws ServletException {
         PreparedStatement ps = null;
         try {
             ps = con.prepareStatement("INSERT INTO EMPLOYEE (firstname,lastname, email, salary, birthdate, department ) VALUES (?,?,?,?,?,?)");
             ps.setString(1, emp.getFirstname());
             ps.setString(2, emp.getLastname());
             ps.setString(3, emp.getEmail());
             ps.setInt(4, emp.getSalary());
             ps.setDate(5, new java.sql.Date(emp.getBirthdate().getTime()));
             ps.setInt(6, emp.getDepartment());
             ps.execute();

         } catch (SQLException e) {
             e.printStackTrace();
             throw new ServletException("DB Connection problem.");
         }finally{
             try {
                 ps.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
    }
    public void delete(int id) throws ServletException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM EMPLOYEE WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
        }finally{
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Employee> findAllByDepatmentId(int id) throws ServletException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Employee> list = new ArrayList();
        try {
            ps = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE department = ? ORDER BY updated DESC");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee(
                    rs.getInt("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("email"),
                    rs.getInt("salary"),
                    rs.getDate("birthdate"),
                    rs.getInt("department"),
                    rs.getDate("created"),
                    rs.getDate("updated")
                );
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
        }finally{
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public void update(Employee emp) throws ServletException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE EMPLOYEE SET FIRSTNAME = ?,LASTNAME = ?, EMAIL = ?, SALARY = ?, BIRTHDATE = ?, DEPARTMENT = ?  WHERE ID = ?");

            ps.setString(1, emp.getFirstname());
            ps.setString(2, emp.getLastname());
            ps.setString(3, emp.getEmail());
            ps.setInt(4, emp.getSalary());
            ps.setDate(5, new java.sql.Date(emp.getBirthdate().getTime()));
            ps.setInt(6, emp.getDepartment());
            ps.setInt(7, emp.getId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
        }finally{
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public List<String> checkEmail(String name) throws ServletException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> list = new ArrayList();
        try {
            // Use the default insensetive case search
            ps = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE email = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("email"));
            };

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
        }finally{
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public Employee findById(int id) throws ServletException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee emp = new Employee();
        try {
            ps = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getInt("salary"),
                        rs.getDate("birthdate"),
                        rs.getInt("department"),
                        rs.getDate("created"),
                        rs.getDate("updated")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
        }finally{
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emp;
    }


}
