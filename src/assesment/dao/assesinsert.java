package assesment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import assesment.Validator.InsertQuestion;

public class assesinsert {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		int l;
		try
		{
		do
		{
		System.out.println("enter the assesment name");
		String i=s.next();
		InsertQuestion.validateAssname(i);
		insertop(i);
		int as=find_assid(i);
		if(as!=0)
	    {
		System.out.println("choose the set of questions");
	   ArrayList<ListQues> h=selection();
	   for(ListQues b:h)
	   {
		   System.out.println(b.q);
		   System.out.println("do you want to add this question give 1 or 0");
		 int v=s.nextInt();
		 InsertQuestion.validateInput(v);
		 if(v==1)
			 insertoption(as,b.i);
	   }
	    }
		else
			System.out.println("Assesment table is not updated");
		System.out.println("do you want to create another assesment give 1 or 0");
	    l=s.nextInt();
	    InsertQuestion.validateInput(l);
		}
	     while(l==1);
		}
		catch (ClassNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			s.close();
		}

	}
	public static void insertop(String d) throws Exception {
		Connection con = null;
		try {
			con = connection.myconnection();
		String sql = "insert into asses (name) values (?)";
		PreparedStatement p = con.prepareStatement(sql);
		p.setString(1, d);
		int rows = p.executeUpdate();
		System.out.println("no of rows inserted" + rows);
		} catch (ClassNotFoundException e) {
			throw new Exception("NOT Connected");
		} catch (SQLException e) {
			throw new Exception("Database server is not available");
		} finally {
			con.close();
		}
		}
	public static void insertoption(int a,int b) throws Exception {
		Connection con = null;
		try {
			con = connection.myconnection();
		
		String sql = "insert into asstest (assid,quid) values (?,?)";
		PreparedStatement p = con.prepareStatement(sql);
		p.setInt(1, a);
		p.setInt(2, b);
		int rows = p.executeUpdate();
		System.out.println("no of rows inserted" + rows);
	} catch (ClassNotFoundException e) {
		throw new Exception("NOT Connected");
	} catch (SQLException e) {
		throw new Exception("Database server is not available");
	} finally {
		con.close();
	}
	}
	public static int find_assid(String a) throws Exception{
		Connection con = null;
		try {
			con = connection.myconnection();
		
		String sql = "select id from asses where name=?";
		PreparedStatement p = con.prepareStatement(sql);
		p.setString(1, a);
		ResultSet rs = p.executeQuery();
		if (rs.next())
			return rs.getInt("id");
		return 0;
	} catch (ClassNotFoundException e) {
		throw new Exception("NOT Connected");
	} catch (SQLException e) {
		throw new Exception("Database server is not available");
	} finally {
		con.close();
	}
	}
	public static ArrayList<ListQues> selection() throws Exception {
		Connection con = null;
		try {
			con = connection.myconnection();
			String sql = "select id,qtext from ques";
		PreparedStatement p = con.prepareStatement(sql);
		ResultSet rs = p.executeQuery();
		ArrayList<ListQues> l=new ArrayList<ListQues>();
		while(rs.next())
		{
		ListQues m = new ListQues();
		 m.q=rs.getString("qtext");
		 m.i=rs.getInt("id");
		l.add(m);
		}
	  return l;
		}
   catch (ClassNotFoundException e) {
	  throw new Exception("NOT Connected");
    } 
		 catch (SQLException e) {
	throw new Exception("Database server is not available");
    } finally {
	con.close();
    }
 }
}