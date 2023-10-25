/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.Course;
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
public class CourseDBContext extends DBContext<Course>{

    @Override
    public ArrayList<Course> list() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT CourseId, CourseName FROM [Course]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Course c =  new Course();
                c.setId(rs.getString("CourseId"));
                c.setName(rs.getString("CourseName"));
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }

    @Override
    public void insert(Course entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Course entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Course entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Course get(Course entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
