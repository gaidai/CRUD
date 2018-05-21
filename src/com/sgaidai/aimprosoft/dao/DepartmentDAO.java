package com.sgaidai.aimprosoft.dao;


import com.sgaidai.aimprosoft.models.Department;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    Connection con;

    public DepartmentDAO(Connection con) {
        this.con = con;
    }
    public int create(String name) throws ServletException {
        PreparedStatement ps = null;
        int id = 0 ;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO DEPARTMENT ( name ) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.execute();
            rs = ps.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
                System.out.println("Generated id: "+id);
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
        return id;
    }
    public void delete(int id) throws ServletException {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("DELETE FROM DEPARTMENT WHERE id = ?");
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
    public void update(int id,String name) throws ServletException {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("UPDATE DEPARTMENT SET NAME = ? WHERE ID = ?");

            ps.setString(1, name);
            ps.setInt(2, id);
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

    public List<Department> findAll() throws ServletException {
        Statement statement = null;
        ResultSet rs = null;
        List<Department> list = new ArrayList();
        try {
            statement = con.createStatement();

            String selectTableSQL = "SELECT * FROM DEPARTMENT ORDER BY updated DESC";


            rs = statement.executeQuery(selectTableSQL);

            while (rs.next()) {

                Department dep = new Department(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("created"),
                        rs.getDate("updated")
                );
                list.add(dep);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
        }finally{
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public List<String> checkName(String name) throws ServletException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> list = new ArrayList();
        try {
            // Use the default insensetive case search
            ps = con.prepareStatement("SELECT * FROM DEPARTMENT  WHERE name = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("name"));
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
    public String findById(int id) throws ServletException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String name = null;
        try {
            ps = con.prepareStatement("SELECT name FROM DEPARTMENT  WHERE id = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                name = rs.getString("name");
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
        return name;
    }

}
