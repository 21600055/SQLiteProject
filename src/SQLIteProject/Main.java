package SQLIteProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String dbFile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
			find(con);
			insert(con);
			find(con);
			update(con);
			find(con);
			delete(con);
			find(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			// TODO Auto-generated catch block
			if(con!=null) {
				try {
					con.close();
				} catch(Exception e) {}
			}
		}
	}
	
	public static void find(Connection con) {
		
		System.out.println("\n---데이터 조회---");
		try {
			Statement stat = con.createStatement();
			String sql = "Select * From g_artist";
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				String year = rs.getString("year");
				String debut = rs.getString("debut");
				String regdate = rs.getString("regdate");
				System.out.println(id+" "+name+" "+type+" "+year+" "+debut+" "+regdate);
			}
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insert(Connection con) {
		System.out.println("\n---데이터 추가---");
		try {
			Statement stat = con.createStatement();
			String sql = "insert into g_artist (name,type,year,debut,regdate)"+
					"values ('test','남성','2012년대','2013년',datetime('now','localtime'));";
			int cnt = stat.executeUpdate(sql);
			if (cnt>0)
				System.out.println("세로운 데이터가 추가되었습니다!");
			else
				System.out.println("[Error] 데이터 추가 오류!");
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void update(Connection con) {
		System.out.println("\n---데이터 수정---");
		try {
			Statement stat = con.createStatement();
			String sql = "update g_artist set year = '2000년대,2010년대, 2020년대' "+
					"Where id =1 ;";
			int cnt = stat.executeUpdate(sql);
			if (cnt>0)
				System.out.println("데이터가 수정되었습니다!");
			else
				System.out.println("[Error] 데이터 수정 오류!");
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void delete(Connection con) {
		System.out.println("\n---데이터 삭제---");
		try {
			Statement stat = con.createStatement();
			String sql = "delete from g_artist where id = 8;";
			int cnt = stat.executeUpdate(sql);
			if (cnt>0)
				System.out.println("데이터가 삭제되었습니다!");
			else
				System.out.println("[Error] 데이터 삭제 오류!");
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
