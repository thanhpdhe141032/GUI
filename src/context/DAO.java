/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.employees;

/**
 *
 * @author Thanh Dang
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<employees> getAllEmp() {
        try {
            String sql = "SELECT * FROM tblEmployees";
            ArrayList<employees> arr = new ArrayList<>();
            conn = new Context().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                arr.add(new employees(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4)));
            }
            return arr;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return null;
        }
    }

    public ArrayList<employees> getAllOrderEmp(String order) {
        try {
            String sql = "SELECT * FROM tblEmployees ORDER BY code " + order;
            ArrayList<employees> arr = new ArrayList<>();
            conn = new Context().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                arr.add(new employees(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4)));
            }
            return arr;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return null;
        }
    }

    public ArrayList<employees> searchByCode(String search) {
        try {
            String sql = "SELECT * FROM tblEmployees WHERE code like '%" + search + "%'";
            ArrayList<employees> arr = new ArrayList<>();
            conn = new Context().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                arr.add(new employees(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4)));
            }
            return arr;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return null;
        }
    }

    public boolean addEmployees(String id, String name, double salary, double bonus) {
        try {
            String sql = "INSERT INTO [dbo].[tblEmployees]\n"
                    + "           ([code]\n"
                    + "           ,[name]\n"
                    + "           ,[salary]\n"
                    + "           ,[bonus])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            conn = new Context().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setDouble(3, salary);
            ps.setDouble(4, bonus);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return false;
        }
    }

}
