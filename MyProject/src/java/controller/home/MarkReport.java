/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import Dal.AccountDBContext;
import Dal.AssessmentDBContext;
import Dal.CampusDBContext;
import Dal.CourseDBContext;
import Dal.EnrollMentDBContext;
import Dal.GradeDBContext;
import Dal.ScoreDBContext;
import Dal.SemesterDBContext;
import Dal.StudentDBContext;
import Entity.Account;
import Entity.Assessment;
import Entity.Campus;
import Entity.Course;
import Entity.Enrollment;
import Entity.Grade;
import Entity.Score;
import Entity.Semester;
import Entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
//import org.apache.jasper.compiler.Node;

/**
 *
 * @author Phạm Văn Nghĩa
 */
public class MarkReport extends BasedRequiredAuthenticationController {

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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account LoggedUser) throws ServletException, IOException {


        String termId = request.getParameter("termName");
        request.setAttribute("term", termId);

        CampusDBContext cpb = new CampusDBContext();
        ArrayList<Campus> campus = cpb.list();

        AccountDBContext db = new AccountDBContext();
        ArrayList<Account> acc = db.list();

        StudentDBContext sb = new StudentDBContext();
        ArrayList<Student> student = sb.list();

        //String studentId = request.getParameter("sid");
        EnrollMentDBContext eb = new EnrollMentDBContext();
        ArrayList<Enrollment> enroll = eb.listDistinctSemesters();

        String sid = request.getParameter("termName");
        request.setAttribute("id", sid);

        EnrollMentDBContext edb = new EnrollMentDBContext();
        ArrayList<Enrollment> enrolls = edb.listDistinctCoursesBySemester(sid);


        String cid = request.getParameter("courseId");
        request.setAttribute("cid", cid);

        SemesterDBContext seb = new SemesterDBContext();
        ArrayList<Semester> semester = seb.list();

        CourseDBContext cb = new CourseDBContext();
        ArrayList<Course> course = cb.list();

        ScoreDBContext scb = new ScoreDBContext();
        ArrayList<Score> scores = scb.listScore(cid);

        GradeDBContext gb = new GradeDBContext();
        ArrayList<Grade> grade = gb.list();

        AssessmentDBContext ab = new AssessmentDBContext();
        ArrayList<Assessment> assessment = ab.list();

        request.setAttribute("assessment", assessment);
        request.setAttribute("grade", grade);
        request.setAttribute("scores", scores);
        request.setAttribute("course", course);
        request.setAttribute("semester", semester);
        request.setAttribute("enroll", enroll);
        request.setAttribute("enrolls", enrolls);
        request.setAttribute("student", student);
        request.setAttribute("campus", campus);
        request.setAttribute("acc", acc);

        //System.out.println(id);
        request.getRequestDispatcher("view/mark.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account LoggedUser) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
