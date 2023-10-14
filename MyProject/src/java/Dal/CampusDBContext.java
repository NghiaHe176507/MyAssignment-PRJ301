/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.Campus;
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
public class CampusDBContext extends DBContext<Campus>{

    @Override
    public ArrayList<Campus> list() {
        ArrayList<Campus> campuses = new ArrayList<>();
        try {
            String sql = "SELECT IdCampus, NameCampus FROM Campus";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Campus c = new Campus();
                c.setId(rs.getInt("IdCampus"));
                c.setName(rs.getString("NameCampus"));
                campuses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CampusDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return campuses;
    }

    @Override
    public void insert(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Campus get(Campus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
