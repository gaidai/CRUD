package com.sgaidai.aimprosoft.servlets;

import com.sgaidai.aimprosoft.dao.EmployeeDAO;
import com.sgaidai.aimprosoft.models.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/updateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("UTF-8");
        String errorMsg = null;
        int id = Integer.parseInt( request.getParameter("id"));
        if(id <= 0 ){
            errorMsg = "ID can't be null or empty.";
        }
        Employee emp = null ;

        if (errorMsg != null) {
            response.sendError(response.SC_BAD_REQUEST, errorMsg);
        }else {
            try {
                // get jsp page with updating form
                Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                EmployeeDAO dao = new EmployeeDAO(con);
                //Get current fields of Employee
                emp = dao.findById(id);
                request.setAttribute("departmentId", emp.getDepartment());
                request.setAttribute("id", id);
                request.setAttribute("firstname", emp.getFirstname());
                request.setAttribute("lastname", emp.getLastname());
                request.setAttribute("email", emp.getEmail());
                String birthdate = new SimpleDateFormat("dd-MM-yyyy").format( emp.getBirthdate());
                request.setAttribute("birthdate", birthdate);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateEmployee.jsp");
                rd.forward(request, response);
            }catch (ServletException e){
                response.sendError(response.SC_BAD_REQUEST, "DB exception");
            };
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String errorMsg = null;
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        int department = Integer.parseInt(request.getParameter("department"));
        int id = Integer.parseInt( request.getParameter("id"));

        SimpleDateFormat formatter = new SimpleDateFormat ("dd-MM-yyyy");
        Date birthday = null;
        try {
            birthday = formatter.parse(request.getParameter("birthdate"));
        } catch (ParseException e) {
            errorMsg = " Parcing exception of birthdate";
        }
        if(id <= 0 ){
            errorMsg = "ID can't be null or empty.";
        }
        if(email == null || email.equals("")){
            errorMsg = "Email ID can't be null or empty.";
        }
        if(firstname == null || firstname.equals("")){
            errorMsg = "Fistname can't be null or empty.";
        }
        if(lastname == null || lastname.equals("")){
            errorMsg = "Lastname can't be null or empty.";
        }
        if(department <= 0){
            errorMsg = "Department can't be null or empty.";
        }
        // compare birthday with current date
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        int yearBirthday = Integer.parseInt(df.format(birthday));
        int yearNow = Integer.parseInt(df.format(today));
        if (yearBirthday < yearNow - 100 || yearNow < yearBirthday + 15) {
            errorMsg = "Employee can't be younger then 15 or older then 100.";
        }
        if (errorMsg != null) {
            response.sendError(response.SC_BAD_REQUEST, errorMsg);
        }else {
            try {
                Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                EmployeeDAO dao = new EmployeeDAO(con);
                Employee emp = new Employee(id, firstname, lastname, email, birthday, department);
                System.out.println(emp);
                dao.update(emp);
            } catch (ServletException e) {
                response.sendError(response.SC_BAD_REQUEST, "DB exception");
            }
        }

    }

}