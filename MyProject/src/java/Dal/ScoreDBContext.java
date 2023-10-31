/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.Assessment;
import Entity.Enrollment;
import Entity.Grade;
import Entity.Score;
import Entity.Student;
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

                scores.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scores;
    }

    public ArrayList<Score> listScores() {
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
                    + "INNER JOIN [Grade] G ON A.[GradeId] = G.[GradeId];";
            PreparedStatement stm = connection.prepareStatement(sql);
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

                scores.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scores;
    }

}

