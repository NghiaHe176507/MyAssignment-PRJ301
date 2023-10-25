/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.Major;
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
public class MajorDBContext extends DBContext<Major>{

    @Override
    public ArrayList<Major> list() {
        ArrayList<Major> majors = new ArrayList<>();
        try {
            String sql = "SELECT MajorId, MajorName FROM [Major]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Major m = new Major();
                m.setId(rs.getString("MajorId"));
                m.setName(rs.getString("MajorName"));
                majors.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MajorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return majors;
    }

    @Override
    public void insert(Major entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Major entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Major entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Major get(Major entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
