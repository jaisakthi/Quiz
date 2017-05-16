package assesment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import assesment.Validator.QuestionType;

public class InitianOperation {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		try
		{
		Scanner s = new Scanner(System.in);
		System.out.println("enter the question type");
		System.out.println("1.Choose the best answer 2.Fill in the blanks");
		int ch = s.nextInt();
		//s.nextLine();
		QuestionType.typeCheck(ch);
		if (ch==1)
           insertrow("choose");
        else
            insertrow("fill");	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public static void insertrow(String d) throws Exception {
		Connection con = null;
		try
		{
		 con= connection.myconnection();
		String sql = "insert into QType (Qtype) values (?)";
		PreparedStatement p = con.prepareStatement(sql);
		p.setString(1, d);
		int rows = p.executeUpdate();
		System.out.println("no of rows inserted" + rows);
	    }
         catch(ClassNotFoundException e){
	        throw new Exception ("Not connected");
         }
	        catch(SQLException e){	 
            throw new Exception ("Not connected");	
	     }
         finally
         {
        	 con.close();
         }
	}
	}