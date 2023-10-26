/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.Course;
import Entity.Enrollment;
import Entity.Group;
import Entity.Semester;
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
public class EnrollMentDBContext extends DBContext<Enrollment> {

    @Override
    public ArrayList<Enrollment> list() {
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        try {
            String sql = "SELECT e.[EnrollmentId]\n"
                    + "      ,e.[StudentId]\n"
                    + "      ,e.[GroupId]\n"
                    + "      ,e.[SemesterId]\n"
                    + "      ,e.[CourseId]\n"
                    + "      ,e.[EnrollStartDate]\n"
                    + "      ,e.[EnrollEndDate]\n"
                    + "      ,g.[GroupName]\n"
                    + "      ,s.[SemesterName]\n"
                    + "      ,c.[CourseName]\n"
                    + "      ,stu.[StudentName]\n"
                    + "      ,stu.[Gender]\n"
                    + "      ,stu.[Dob]\n"
                    + "      ,stu.[MajorId]\n"
                    + "      ,stu.[IdCampus]\n"
                    + "  FROM [Enrollment] e\n"
                    + "  INNER JOIN [Group] g ON e.[GroupId] = g.[GroupId]\n"
                    + "  INNER JOIN [Semester] s ON e.[SemesterId] = s.[SemesterId]\n"
                    + "  INNER JOIN [Course] c ON e.[CourseId] = c.[CourseId]\n"
                    + "  INNER JOIN [Student] stu ON e.[StudentId] = stu.[StudentId]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Enrollment e = new Enrollment();
                e.setId(rs.getInt("EnrollmentId"));
                Student s = new Student();
                s.setId(rs.getString("StudentId"));
                e.setStudent(s);
                Group g = new Group();
                g.setId(rs.getString("GroupId"));
                e.setGroup(g);
                Semester se = new Semester();
                se.setId(rs.getInt("SemesterId"));
                e.setSemester(se);
                Course c = new Course();
                c.setId(rs.getString("CourseId"));
                e.setCourse(c);
                e.setStartdate(rs.getDate("EnrollStartDate"));
                e.setEnddate(rs.getDate("EnrollEndDate"));
                enrollments.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(EnrollMentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enrollments;
    }

    @Override
    public void insert(Enrollment entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Enrollment entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Enrollment entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Enrollment get(Enrollment e) {
        try {
            String sql = "SELECT e.[EnrollmentId]\n"
                    + "      ,e.[StudentId]\n"
                    + "      ,e.[GroupId]\n"
                    + "      ,e.[SemesterId]\n"
                    + "      ,e.[CourseId]\n"
                    + "      ,e.[EnrollStartDate]\n"
                    + "      ,e.[EnrollEndDate]\n"
                    + "      ,g.[GroupName]\n"
                    + "      ,s.[SemesterName]\n"
                    + "      ,c.[CourseName]\n"
                    + "      ,stu.[StudentName]\n"
                    + "      ,stu.[Gender]\n"
                    + "      ,stu.[Dob]\n"
                    + "      ,stu.[MajorId]\n"
                    + "      ,stu.[IdCampus]\n"
                    + "  FROM [dbo].[Enrollment] e\n"
                    + "  INNER JOIN [dbo].[Group] g ON e.[GroupId] = g.[GroupId]\n"
                    + "  INNER JOIN [dbo].[Semester] s ON e.[SemesterId] = s.[SemesterId]\n"
                    + "  INNER JOIN [dbo].[Course] c ON e.[CourseId] = c.[CourseId]\n"
                    + "  INNER JOIN [dbo].[Student] stu ON e.[StudentId] = stu.[StudentId]\n"
                    + "  WHERE stu.[StudentId] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, e.getStudent().getId());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                e.setId(rs.getInt("EnrollmentId"));
                Student s = new Student();
                s.setId(rs.getString("StudentId"));
                e.setStudent(s);
                Group g = new Group();
                g.setId(rs.getString("GroupId"));
                e.setGroup(g);
                Semester se = new Semester();
                se.setId(rs.getInt("SemesterId"));
                e.setSemester(se);
                Course c = new Course();
                c.setId(rs.getString("CourseId"));
                e.setCourse(c);
                e.setStartdate(rs.getDate("EnrollStartDate"));
                e.setEnddate(rs.getDate("EnrollEndDate"));
                return e;

            }
        } catch (SQLException ex) {
            Logger.getLogger(EnrollMentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Enrollment> listDistinctSemesters() {
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT s.[SemesterName], e.[StudentId], e.[SemesterId]\n"
                    + "  FROM [Enrollment] e\n"
                    + "  INNER JOIN [Group] g ON e.[GroupId] = g.[GroupId]\n"
                    + "  INNER JOIN [Semester] s ON e.[SemesterId] = s.[SemesterId]\n"
                    + "  INNER JOIN [Course] c ON e.[CourseId] = c.[CourseId]\n"
                    + "  INNER JOIN [Student] stu ON e.[StudentId] = stu.[StudentId]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Enrollment e = new Enrollment();
                Semester se = new Semester();
                se.setName(rs.getString("SemesterName"));
                e.setSemester(se);
                Student s = new Student();
                s.setId(rs.getString("StudentId"));
                e.setStudent(s);
                Semester semester = new Semester();
                semester.setId(rs.getInt("SemesterId"));
                e.setSemester(semester);
                enrollments.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnrollMentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enrollments;
    }

    public ArrayList<Enrollment> listDistinctCourses() {
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT c.[CourseName], e.[StudentId], e.[CourseId]\n"
                    + "  FROM [Enrollment] e\n"
                    + "  INNER JOIN [Group] g ON e.[GroupId] = g.[GroupId]\n"
                    + "  INNER JOIN [Semester] s ON e.[SemesterId] = s.[SemesterId]\n"
                    + "  INNER JOIN [Course] c ON e.[CourseId] = c.[CourseId]\n"
                    + "  INNER JOIN [Student] stu ON e.[StudentId] = stu.[StudentId]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Enrollment e = new Enrollment();
                Course course = new Course();
                course.setName(rs.getString("CourseName"));
                e.setCourse(course);
                Student s = new Student();
                s.setId(rs.getString("StudentId"));
                e.setStudent(s);
                Course courses = new Course();
                courses.setId(rs.getString("CourseId"));
                e.setCourse(course);
                enrollments.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnrollMentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enrollments;
    }

    
}
