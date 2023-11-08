/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import Dal.AccountDBContext;
import Dal.CampusDBContext;
import Dal.CourseDBContext;
import Dal.EnrollMentDBContext;
import Dal.ScoreDBContext;
import Dal.SemesterDBContext;
import Entity.Account;
import Entity.Campus;
import Entity.Course;
import Entity.Enrollment;
import Entity.Semester;
import Entity.TotalEachScore;
import Entity.TotalScore;
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
public class GradeTranscript extends BasedRequiredAuthenticationController {

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
            out.println("<title>Servlet GradeTranscript</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GradeTranscript at " + request.getContextPath() + "</h1>");
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
        CampusDBContext cb = new CampusDBContext();
        ArrayList<Campus> campus = cb.list();

        AccountDBContext db = new AccountDBContext();
        ArrayList<Account> acc = db.list();

        SemesterDBContext seb = new SemesterDBContext();
        ArrayList<Semester> semester = seb.list();

        CourseDBContext c = new CourseDBContext();
        ArrayList<Course> course = c.list();

        EnrollMentDBContext eb = new EnrollMentDBContext();
        ArrayList<Enrollment> enroll = eb.list();

        ScoreDBContext s = new ScoreDBContext();
        ArrayList<TotalEachScore> totalscore = s.listtotal();

        ArrayList<TotalEachScore> filteredScores = new ArrayList<>();

        for (TotalEachScore point : totalscore) {
            if (!"Final exam Resit".equals(point.getGrade().getName()) && !"Final exam".equals(point.getGrade().getName())
                    && !"Theory exam resit".equals(point.getGrade().getName()) && !"Theory exam".equals(point.getGrade().getName())
                    && !"Practical exam Resit".equals(point.getGrade().getName()) && !"Practical exam".equals(point.getGrade().getName())
                    && !"FE: Listening Resit".equals(point.getGrade().getName()) && !"FE: Listening".equals(point.getGrade().getName())
                    && !"FE: GVR Resit".equals(point.getGrade().getName()) && !"FE: GVR exam".equals(point.getGrade().getName())) {
                filteredScores.add(point);
            } else if ("Final exam".equals(point.getGrade().getName())) {
                filteredScores.add(point);
                for (TotalEachScore finalExam : totalscore) {
                    if ("Final exam Resit".equals(finalExam.getGrade().getName()) && finalExam.getScore().getScore() != null
                            && finalExam.getEnrollment().getId() == point.getEnrollment().getId()
                            && finalExam.getCourse().getId().equals(point.getCourse().getId())) {
                        filteredScores.remove(point);
                        filteredScores.add(finalExam);
                    } else if ("Final exam Resit".equals(finalExam.getGrade().getName())
                            && finalExam.getScore().getScore() == null
                            && finalExam.getEnrollment().getId() == point.getEnrollment().getId()
                            && finalExam.getCourse().getId().equals(point.getCourse().getId())) {
                    }
                }
            } else if ("Theory exam".equals(point.getGrade().getName())) {
                filteredScores.add(point);
                for (TotalEachScore theoryExam : totalscore) {
                    if ("Theory exam resit".equals(theoryExam.getGrade().getName()) && theoryExam.getScore().getScore() != null
                            && theoryExam.getEnrollment().getId() == point.getEnrollment().getId()
                            && theoryExam.getCourse().getId().equals(point.getCourse().getId())) {
                        filteredScores.remove(point);
                        filteredScores.add(theoryExam);
                    } else if ("Theory exam resit".equals(theoryExam.getGrade().getName())
                            && theoryExam.getScore().getScore() == null
                            && theoryExam.getEnrollment().getId() == point.getEnrollment().getId()
                            && theoryExam.getCourse().getId().equals(point.getCourse().getId())) {
                    }
                }
            } else if ("Practical exam".equals(point.getGrade().getName())) {
                filteredScores.add(point);
                for (TotalEachScore theoryExam : totalscore) {
                    if ("Practical exam Resit".equals(theoryExam.getGrade().getName()) && theoryExam.getScore().getScore() != null
                            && theoryExam.getEnrollment().getId() == point.getEnrollment().getId()
                            && theoryExam.getCourse().getId().equals(point.getCourse().getId())) {
                        filteredScores.remove(point);
                        filteredScores.add(theoryExam);
                    } else if ("Practical exam Resit".equals(theoryExam.getGrade().getName())
                            && theoryExam.getScore().getScore() == null
                            && theoryExam.getEnrollment().getId() == point.getEnrollment().getId()
                            && theoryExam.getCourse().getId().equals(point.getCourse().getId())) {
                    }
                }
            } else if ("FE: Listening".equals(point.getGrade().getName())) {
                filteredScores.add(point);
                for (TotalEachScore theoryExam : totalscore) {
                    if ("FE: Listening Resit".equals(theoryExam.getGrade().getName()) && theoryExam.getScore().getScore() != null
                            && theoryExam.getEnrollment().getId() == point.getEnrollment().getId()
                            && theoryExam.getCourse().getId().equals(point.getCourse().getId())) {
                        filteredScores.remove(point);
                        filteredScores.add(theoryExam);
                    } else if ("FE: Listening Resit".equals(theoryExam.getGrade().getName())
                            && theoryExam.getScore().getScore() == null
                            && theoryExam.getEnrollment().getId() == point.getEnrollment().getId()
                            && theoryExam.getCourse().getId().equals(point.getCourse().getId())) {
                    }
                }
            } else if ("FE: GVR".equals(point.getGrade().getName())) {
                filteredScores.add(point);
                for (TotalEachScore theoryExam : totalscore) {
                    if ("FE: GVR Resit".equals(theoryExam.getGrade().getName()) && theoryExam.getScore().getScore() != null
                            && theoryExam.getEnrollment().getId() == point.getEnrollment().getId()
                            && theoryExam.getCourse().getId().equals(point.getCourse().getId())) {
                        filteredScores.remove(point);
                        filteredScores.add(theoryExam);
                    } else if ("FE: GVR Resit".equals(theoryExam.getGrade().getName())
                            && theoryExam.getScore().getScore() == null
                            && theoryExam.getEnrollment().getId() == point.getEnrollment().getId()
                            && theoryExam.getCourse().getId().equals(point.getCourse().getId())) {
                    }
                }
            } else if ("Final exam Resit".equals(point.getGrade().getName()) && point.getScore().getScore() == null) {
                // Handle other conditions if needed
            }
        }

       


        request.setAttribute(
                "filteredScores", filteredScores);
        request.setAttribute(
                "course", course);
        request.setAttribute(
                "enroll", enroll);
        request.setAttribute(
                "campus", campus);
        request.setAttribute(
                "semester", semester);
        request.setAttribute(
                "acc", acc);
        request.getRequestDispatcher(
                "view/transcript.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account LoggedUser) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
