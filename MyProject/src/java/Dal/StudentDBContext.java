/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.Campus;
import Entity.Major;
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
public class StudentDBContext extends DBContext<Student> {

    @Override
    public ArrayList<Student> list() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT s.[StudentId]\n"
                    + "      ,s.[StudentName]\n"
                    + "      ,s.[Gender]\n"
                    + "      ,s.[Dob]\n"
                    + "      ,s.[MajorId]\n"
                    + "      ,s.[IdCampus]\n"
                    + "      ,c.[NameCampus]\n"
                    + "      ,m.[MajorName]\n"
                    + "  FROM [Student] s\n"
                    + "  INNER JOIN [Campus] c ON s.[IdCampus] = c.[IdCampus]\n"
                    + "  INNER JOIN [Major] m ON s.[MajorId] = m.[MajorId]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Student s = new Student();
                s.setId(rs.getString("StudentId"));
                s.setName(rs.getString("StudentName"));
                s.setGender(rs.getBoolean("Gender"));
                s.setDob(rs.getDate("Dob"));
                
                Major m = new Major();
                m.setId(rs.getString("MajorId"));
                s.setMajor(m);
                
                Campus cp = new Campus();
                cp.setId(rs.getInt("IdCampus"));
                s.setCampus(cp);
                
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    @Override
    public void insert(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
