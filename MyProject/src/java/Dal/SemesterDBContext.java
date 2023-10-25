/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.Semester;
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
public class SemesterDBContext extends  DBContext<Semester>{

    @Override
    public ArrayList<Semester> list() {
        ArrayList<Semester> semesters =  new ArrayList<>();
        try {
            String sql = "SELECT SemesterId, SemesterName FROM [Semester]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Semester s = new Semester();
                s.setId(rs.getInt("SemesterId"));
                s.setName(rs.getString("SemesterName"));
                semesters.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SemesterDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return semesters;
    }

    @Override
    public void insert(Semester entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Semester entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Semester entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Semester get(Semester entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
