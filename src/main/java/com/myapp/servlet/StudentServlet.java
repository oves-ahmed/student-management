package com.myapp.servlet;

import com.myapp.dao.StudentDAO;
import com.myapp.model.Student;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO = new StudentDAO();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        req.setAttribute("students", students);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if ("add".equals(action)) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String course = req.getParameter("course");
            
            Student student = new Student(0, name, email, course);
            studentDAO.addStudent(student);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            studentDAO.deleteStudent(id);
        }
        
        resp.sendRedirect("students");
    }
}