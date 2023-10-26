/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.Account;
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
public class AccountDBContext extends DBContext<Account> {

    @Override
    public ArrayList<Account> list() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            String sql = "SELECT [Username]\n"
                    + "      ,[Password]\n"
                    + "      ,[Displayname]\n"
                    + "      ,a.[IdCampus]\n"
                    + "      ,a.[IdPosition]\n"
                    + "      ,a.[StudentId]\n"
                    + "  FROM [Account] a \n"
                    + "INNER JOIN Position p ON p.IdPosition = a.IdPosition\n"
                    + "INNER JOIN Campus c ON c.IdCampus = a.IdCampus\n"
                    + "INNER JOIN Student s ON s.StudentId = a.StudentId";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUsername(rs.getString("Username"));
                a.setDisplayname(rs.getString("displayname"));
                a.setPid(rs.getInt("IdPosition"));
                a.setCid(rs.getInt("IdCampus"));
                Student s = new Student();
                s.setId(rs.getString("StudentId"));
                a.setStudent(s);
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    @Override
    public void insert(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account get(Account entity) {
        try {
            String sql = "SELECT [Username]\n"
                    + "      ,[Password]\n"
                    + "      ,[Displayname]\n"
                    + "      ,a.[IdCampus]\n"
                    + "      ,a.[IdPosition]\n"
                    + "      ,a.[StudentId]\n"
                    + "  FROM [Account] a \n"
                    + "INNER JOIN Position p ON p.IdPosition = a.IdPosition\n"
                    + "INNER JOIN Campus c ON c.IdCampus = a.IdCampus\n"
                    + "INNER JOIN Student s ON s.StudentId = a.StudentId\n"
                    + "WHERE a.Username = ? AND a.Password = ? AND a.IdPosition = ? AND a.IdCampus = ?";
            //AND a.IdPosition = ? AND a.IdCampus = ?
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, entity.getUsername());
            stm.setString(2, entity.getPassword());
            stm.setInt(3, entity.getPid());
            stm.setInt(4, entity.getCid());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setDisplayname(rs.getString("displayname"));
                account.setPid(rs.getInt("IdPosition"));
                account.setCid(rs.getInt("IdCampus"));
                Student s = new Student();
                s.setId(rs.getString("StudentId"));
                account.setStudent(s);
                return account;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public String getStudentId(String username) {
        try {
            String sql = "SELECT StudentId\n"
                    + "FROM Account\n"
                    + "WHERE Username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("StudentId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
