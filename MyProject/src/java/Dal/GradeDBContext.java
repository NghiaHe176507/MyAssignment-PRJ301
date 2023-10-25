/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

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
public class GradeDBContext extends DBContext<Grade> {

    @Override
    public ArrayList<Grade> list() {
        ArrayList<Grade> grades = new ArrayList<>();
        try {
            String sql = "SELECT GradeId, GradeName FROM [Grade]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Grade g = new Grade();
                g.setId(rs.getString("GradeId"));
                g.setName(rs.getString("GradeName"));
                grades.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grades;
    }

    @Override
    public void insert(Grade entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Grade entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Grade entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Grade get(Grade entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
