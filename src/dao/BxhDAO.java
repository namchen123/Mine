package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import model.BangXepHang;
import database.JDBCUtil;

public class BxhDAO {
	public static BxhDAO getInstance() {
		return new BxhDAO();
	}
	public void them(BangXepHang t) {
		int ketqua = 0;
		try {
			Connection cn = JDBCUtil.getConnection();
			Statement st = cn.createStatement();
			String sql = "INSERT INTO bxh(name,time,lever) "+
						"Values('"+t.getName()+"','"+t.getTime()+"','"+t.getLevel()+"')";
			ketqua =st.executeUpdate(sql);
			JDBCUtil.closeConnection(cn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void lay() {
        Connection cn = JDBCUtil.getConnection();
        try {
            PreparedStatement pstmt = cn.prepareStatement("SELECT name, lever, time FROM bxh ORDER BY CASE lever WHEN 'Easy' THEN 1 WHEN 'Normal' THEN 2 WHEN 'Hard' THEN 3 ELSE 4 END DESC, time ASC LIMIT 0, 1000");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String level = rs.getString("lever");
                Time time = rs.getTime("time");
                String t= time.toString();
                BangXepHang bxh = new BangXepHang(name, t, level);
                System.out.println("Name: " + name + ", Level: " + level + ", Time: " + time);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	public ArrayList<BangXepHang> layds() {
	    ArrayList<BangXepHang> danhSach = new ArrayList<BangXepHang>();
	    try {
	        Connection cn = JDBCUtil.getConnection();
	        PreparedStatement pstmt = cn.prepareStatement("SELECT name, lever, time FROM bxh ORDER BY CASE lever WHEN 'Easy' THEN 1 WHEN 'Normal' THEN 2 WHEN 'Hard' THEN 3 ELSE 4 END DESC, time ASC LIMIT 0, 1000");
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            String name = rs.getString("name");
	            String level= rs.getString("lever");
	            Time time = rs.getTime("time");
	            String t = time.toString();
	            danhSach.add(new BangXepHang(name, t, level));
	        }
	        JDBCUtil.closeConnection(cn);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return danhSach;
	}
}