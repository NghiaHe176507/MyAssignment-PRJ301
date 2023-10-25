/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.Assessment;
import Entity.Course;
import Entity.Grade;
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
public class AssessmentDBContext extends DBContext<Assessment> {

    @Override
    public ArrayList<Assessment> list() {
        ArrayList<Assessment> assessments = new ArrayList<>();
        try {
            String sql = "SELECT a.[AssessmentID]\n"
                    + "      ,a.[CourseId]\n"
                    + "      ,a.[GradeId]\n"
                    + "      ,a.[Weight]\n"
                    + "      ,c.[CourseName]\n"
                    + "      ,g.[GradeName]\n"
                    + "  FROM [Assessment] a\n"
                    + "  INNER JOIN [Course] c ON a.[CourseId] = c.[CourseId]\n"
                    + "  INNER JOIN [Grade] g ON a.[GradeId] = g.[GradeId]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Assessment ass = new Assessment();
                ass.setId(rs.getInt("AssessmentID"));
                Course c = new Course();
                c.setId(rs.getString("CourseId"));
                ass.setCourse(c);
                Grade g = new Grade();
                g.setId(rs.getString("GradeId"));
                ass.setGrade(g);
                ass.setWeight(rs.getFloat("Weight"));
                
                assessments.add(ass);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AssessmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assessments;
    }

    @Override
    public void insert(Assessment entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Assessment entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Assessment entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Assessment get(Assessment entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
