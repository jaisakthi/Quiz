package assesment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InitianOperation {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println("enter the question type");
		System.out.println("1.Choose the best answer 2.Fill in the blanks");
		int ch = s.nextInt();
		//s.nextLine();
		if (ch==1)
           insertrow("choose");
        else
            insertrow("fill");	
	
	}
	public static void insertrow(String d) throws ClassNotFoundException, SQLException {
		Connection con = connection.myconnection();
		String sql = "insert into QType (Qtype) values (?)";
		PreparedStatement p = con.prepareStatement(sql);
		p.setString(1, d);
		int rows = p.executeUpdate();
		System.out.println("no of rows inserted" + rows);
	}
}
