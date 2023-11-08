/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.Assessment;
import Entity.Course;
import Entity.Enrollment;
import Entity.Grade;
import Entity.Score;
import Entity.Semester;
import Entity.Student;
import Entity.TotalEachScore;
import Entity.TotalScore;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phạm Văn Nghĩa
 */
public class ScoreDBContext extends DBContext<Score> {

    @Override
    public ArrayList<Score> list() {
        ArrayList<Score> scores = new ArrayList<>();
        try {
            String sql = "SELECT S.[ScoreId]\n"
                    + "      ,S.[AssessmentId]\n"
                    + "      ,S.[Score]\n"
                    + "      ,S.[EnrollmentId]\n"
                    + "      ,A.[CourseId]\n"
                    + "      ,A.[GradeId]\n"
                    + "      ,A.[Weight]\n"
                    + "      ,E.[StudentId]\n"
                    + "      ,E.[GroupId]\n"
                    + "      ,E.[SemesterId]\n"
                    + "      ,E.[CourseId]\n"
                    + "      ,E.[EnrollStartDate]\n"
                    + "      ,E.[EnrollEndDate]\n"
                    + "FROM [Score] S\n"
                    + "INNER JOIN [Assessment] A ON S.[AssessmentId] = A.[AssessmentID]\n"
                    + "INNER JOIN [Enrollment] E ON S.[EnrollmentId] = E.[EnrollmentId];";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Score s = new Score();
                s.setId(rs.getInt("ScoreId"));
                Assessment a = new Assessment();
                a.setId(rs.getInt("AssessmentId"));
                s.setAssessment(a);
                Enrollment e = new Enrollment();
                e.setId(rs.getInt("EnrollmentId"));
                s.setEnrollment(e);
                s.setScore(rs.getFloat("Score"));

                scores.add(s);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scores;
    }

    @Override
    public void insert(Score entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Score entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Score entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Score get(Score entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Score> listScore(String courseID) {
        ArrayList<Score> scores = new ArrayList<>();
        try {
            String sql = "SELECT S.[ScoreId], "
                    + "A.[GradeId], "
                    + "G.[GradeName], "
                    + "E.[StudentId], "
                    + "S.[AssessmentId], "
                    + "S.[EnrollmentId], "
                    + "A.[Weight], "
                    + "S.[Score] "
                    + "FROM [Score] S "
                    + "INNER JOIN [Assessment] A ON S.[AssessmentId] = A.[AssessmentID] "
                    + "INNER JOIN [Enrollment] E ON S.[EnrollmentId] = E.[EnrollmentId] "
                    + "INNER JOIN [Grade] G ON A.[GradeId] = G.[GradeId] "
                    + "WHERE E.[CourseId] = ?;";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, courseID);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Score s = new Score();
                s.setId(rs.getInt("ScoreId"));
                Assessment a = new Assessment();
                Grade g = new Grade();
                g.setId(rs.getString("GradeId"));
                g.setName(rs.getString("GradeName"));
                a.setGrade(g);
                a.setWeight(rs.getFloat("Weight"));
                s.setAssessment(a);
                Enrollment e = new Enrollment();
                Student stu = new Student();
                stu.setId(rs.getString("StudentId"));
                e.setStudent(stu);
                e.setId(rs.getInt("EnrollmentId"));
                s.setEnrollment(e);
                s.setScore(rs.getFloat("Score"));
                float scoreValue = rs.getFloat("Score");
                if (rs.wasNull()) {
                    s.setScore(null);
                } else {
                    s.setScore(scoreValue);
                }
                scores.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scores;
    }

    public ArrayList<TotalScore> listgpa() {
        ArrayList<TotalScore> totalscores = new ArrayList<>();
        try {
            String sql = "SELECT e.[EnrollmentId], se.SemesterId, e.[CourseId], "
                    + "SUM(s.[Score] * a.[Weight] / 100) AS [Total] "
                    + "FROM [Score] s "
                    + "INNER JOIN [Enrollment] e ON s.[EnrollmentId] = e.[EnrollmentId] "
                    + "INNER JOIN [Semester] se ON e.SemesterId = se.SemesterId "
                    + "INNER JOIN [Assessment] a ON s.[AssessmentId] = a.[AssessmentId] "
                    + "INNER JOIN [Grade] g ON a.[GradeId] = g.[GradeId] "
                    + "GROUP BY e.[CourseId], e.[EnrollmentId], se.SemesterId "
                    + "ORDER BY e.[EnrollmentId];";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TotalScore t = new TotalScore();
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getInt("EnrollmentId"));
                t.setEnrollment(enrollment);

                Semester semester = new Semester();
                semester.setId(rs.getInt("SemesterId"));
                t.setSemester(semester);

                Course course = new Course();
                course.setId(rs.getString("CourseId"));
                t.setCourse(course);

                t.setTotal(rs.getFloat("Total"));
                

                totalscores.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalscores;
    }

    public ArrayList<TotalEachScore> listtotal() {
        ArrayList<TotalEachScore> totaleachscores = new ArrayList<>();
        try {
            String sql = "SELECT e.[EnrollmentId], a.[AssessmentID] ,s.[ScoreId],s.Score, a.[Weight], g.GradeName ,e.[CourseId],\n"
                    + "       SUM(s.[Score] * a.[Weight] / 100) AS [Total]\n"
                    + "  FROM [Score] s\n"
                    + "       INNER JOIN [Enrollment] e ON s.[EnrollmentId] = e.[EnrollmentId]\n"
                    + "       INNER JOIN [Assessment] a ON s.[AssessmentId] = a.[AssessmentId]\n"
                    + "       INNER JOIN [Grade] g ON a.[GradeId] = g.[GradeId]\n"
                    + "GROUP BY e.[CourseId], e.[EnrollmentId],a.[AssessmentID],s.[ScoreId],g.GradeName,s.Score, a.[Weight]\n"
                    + "ORDER BY e.[EnrollmentId]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TotalEachScore t = new TotalEachScore();
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getInt("EnrollmentId"));
                t.setEnrollment(enrollment);
                
                Assessment a = new Assessment();
                a.setId(rs.getInt("AssessmentID"));
                a.setWeight(rs.getFloat("Weight"));
                t.setAssessment(a);
                
                Score s = new Score();
                s.setId(rs.getInt("ScoreId"));
                float scoreValue = rs.getFloat("Score");
                if (rs.wasNull()) {
                    s.setScore(null);
                } else {
                    s.setScore(scoreValue);
                }
                t.setScore(s);
                
                Grade g = new Grade();
                g.setName(rs.getString("GradeName"));
                t.setGrade(g);

                Course course = new Course();
                course.setId(rs.getString("CourseId"));
                t.setCourse(course);

                
                float totalValue = rs.getFloat("Total");
                if (rs.wasNull()) {
                    t.setTotal(null);
                } else {
                    t.setTotal(totalValue);
                }

                totaleachscores.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totaleachscores;
    }

}
