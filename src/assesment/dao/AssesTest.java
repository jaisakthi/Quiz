package assesment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AssesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=null;
		try
		{
		s=new Scanner(System.in);
		System.out.println("Enter the assesment do you want to take");
	    String as=s.next();
	    System.out.println("The questions are:\n");
	    int f=find_assid(as);
	    if(f!=0)
	    {
	    	listqs(f);
	    }
	    else
	    	System.out.println("No such a assignment");
		}
		catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			s.close();
		}
		
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
	public static int selectqs(int a,int j) throws ClassNotFoundException, SQLException {
	    Scanner s =new Scanner(System.in);
		int m=0,sc = 0;
		Connection con = connection.myconnection();
		String sql = "select qty,qtext,qscore from ques where id=?";
		PreparedStatement p = con.prepareStatement(sql);
		p.setInt(1, a);
		ResultSet rs = p.executeQuery();
		if(rs.next())
		{	
		  System.out.println("The question" +j+"is\n"+rs.getString("qtext"));
	      sc=rs.getInt("qscore");
		  if(rs.getInt("qty")==1)
	      {
	    	  m=1;
	      }
		}
	    if(m==1)
	    {
		sql = "select anstext from aoption  where qtext=?";
	    p = con.prepareStatement(sql);
		p.setInt(1, a);
		rs = p.executeQuery();
		System.out.println("\nThe answer options are");
		while (rs.next())
		{
		 System.out.println("\n"+rs.getString("anstext"));
		}
   	}
    System.out.println("Enter the answer");
    String resp=s.next();
	sql = "select anstext from aoption where id in (select ansid from canswer  where qid=?)";
    p = con.prepareStatement(sql);
	p.setInt(1, a);
	rs = p.executeQuery();
	//System.out.println("\nThe answer options are");
	if (rs.next())
	{
	 if(resp.equals(rs.getString("anstext")))
		 return sc;
		 //System.out.println("\n"+rs.getString("anstext"));
	}
	
   return sc;
	
	}  
		
	public static void listqs(int a) throws ClassNotFoundException, SQLException {
		int scs=0,b=0;
		Connection con = connection.myconnection();
		String sql = "select quid from asstest where assid=?" ;
		PreparedStatement p = con.prepareStatement(sql);
		p.setInt(1, a);
		ResultSet rs = p.executeQuery();
		while (rs.next())
		{
		  int sc=selectqs(rs.getInt("quid"),++b);
		  scs=scs+sc;
		}
	System.out.println("Your score is:"+scs);
	}

}
