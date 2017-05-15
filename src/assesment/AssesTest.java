package assesment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AssesTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the assesment do you want to take");
	    String as=s.next();
	    System.out.println("The questions are");
	    int f=find_assid(as);
	    if(f!=0)
	    {
	    	listqs(f);
	    }
	    else
	    	System.out.println("No such a asseement");
	    
		}
	public static int find_assid(String a) throws ClassNotFoundException, SQLException {
		Connection con = connection.myconnection();
		String sql = "select id from asses where name=?";
		PreparedStatement p = con.prepareStatement(sql);
		p.setString(1, a);
		ResultSet rs = p.executeQuery();
		if (rs.next())
			return rs.getInt("id");
		return 0;
	}
	public static void selectqs(int a) throws ClassNotFoundException, SQLException {
		Connection con = connection.myconnection();
		String sql = "select qtext from ques where id=?";
		PreparedStatement p = con.prepareStatement(sql);
		p.setInt(1, a);
		ResultSet rs = p.executeQuery();
		if(rs.next())
		{	
		  System.out.println("The question is"+rs.getString("qtext"));
		}
	    sql = "select anstext from aoption  where qtext=?";
	    p = con.prepareStatement(sql);
		p.setInt(1, a);
		rs = p.executeQuery();
		System.out.println("The answer options are");
		while (rs.next())
		{
		 System.out.println(rs.getString("anstext"));
		}
	}  
	
	
	public static void listqs(int a) throws ClassNotFoundException, SQLException {
		Connection con = connection.myconnection();
		String sql = "select quid from asstest where assid=?" ;
		PreparedStatement p = con.prepareStatement(sql);
		p.setInt(1, a);
		ResultSet rs = p.executeQuery();
		while (rs.next())
		{
		  selectqs(rs.getInt("quid"));
		 }
	}

}
