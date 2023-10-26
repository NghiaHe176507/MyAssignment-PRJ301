/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import Dal.AccountDBContext;
import Dal.CampusDBContext;
import Dal.CourseDBContext;
import Dal.EnrollMentDBContext;
import Dal.SemesterDBContext;
import Dal.StudentDBContext;
import Entity.Account;
import Entity.Campus;
import Entity.Course;
import Entity.Enrollment;
import Entity.Semester;
import Entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Phạm Văn Nghĩa
 */
public class MarkReport extends BasedRequiredAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MarkReport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MarkReport at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account LoggedUser) throws ServletException, IOException {
        CampusDBContext cpb = new CampusDBContext();
        ArrayList<Campus> campus = cpb.list();

        AccountDBContext db = new AccountDBContext();
        ArrayList<Account> acc = db.list();

        StudentDBContext sb = new StudentDBContext();
        ArrayList<Student> student = sb.list();

        //String studentId = request.getParameter("sid");
        EnrollMentDBContext eb = new EnrollMentDBContext();
        ArrayList<Enrollment> enroll = eb.listDistinctSemesters();
        
        EnrollMentDBContext edb = new EnrollMentDBContext();
        ArrayList<Enrollment> enrolls = edb.listDistinctCourses();

        SemesterDBContext seb = new SemesterDBContext();
        ArrayList<Semester> semester = seb.list();

        CourseDBContext cb = new CourseDBContext();
        ArrayList<Course> course = cb.list();

        request.setAttribute("course", course);
        request.setAttribute("semester", semester);
        request.setAttribute("enroll", enroll);
        request.setAttribute("enrolls", enrolls);
        request.setAttribute("student", student);
        request.setAttribute("campus", campus);
        request.setAttribute("acc", acc);

        request.getRequestDispatcher("view/mark.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account LoggedUser) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
