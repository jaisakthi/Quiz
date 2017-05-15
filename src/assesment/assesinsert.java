package assesment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class assesinsert {
	static Scanner s=new Scanner(System.in);
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int k=0,l;
		
		do
		{
		k++;
		System.out.println("enter the assesment name");
		String i=s.next();
		insertop(i);
		System.out.println("choose the set of questions");
		selection(k);
		System.out.println("do you want to create another assesment give 1");
	    l=s.nextInt();
		}
	     while(l==1);
	
	}
	public static void insertop(String d) throws ClassNotFoundException, SQLException {
		Connection con = connection.myconnection();
		String sql = "insert into asses (name) values (?)";
		PreparedStatement p = con.prepareStatement(sql);
		p.setString(1, d);
		int rows = p.executeUpdate();
		System.out.println("no of rows inserted" + rows);
	}
	public static void insertoption(int a,int b) throws ClassNotFoundException, SQLException {
		Connection con = connection.myconnection();
		String sql = "insert into asstest (assid,quid) values (?,?)";
		PreparedStatement p = con.prepareStatement(sql);
		p.setInt(1, a);
		p.setInt(2, b);
		int rows = p.executeUpdate();
		System.out.println("no of rows inserted" + rows);
	}
	public static int find_qid(String a) throws ClassNotFoundException, SQLException {
		Connection con = connection.myconnection();
		String sql = "select id from ques where qtext=?";
		PreparedStatement p = con.prepareStatement(sql);
		p.setString(1, a);
		ResultSet rs = p.executeQuery();
		if (rs.next())
			return rs.getInt("id");
		return 0;
	}
	public static void selection(int a) throws ClassNotFoundException, SQLException {
		
		Connection con = connection.myconnection();
		String sql = "select id,qtext from ques";
		PreparedStatement p = con.prepareStatement(sql);
		ResultSet rs = p.executeQuery();
		while (rs.next())
		{
		 System.out.println(rs.getString("qtext"));
		 System.out.println("do you want to add this question give 1");
		 int v=s.nextInt();
		 if(v==1)
			 insertoption(a,rs.getInt("id"));
		}
	}
}
