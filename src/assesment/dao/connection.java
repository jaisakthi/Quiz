package assesment.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {

	public static Connection myconnection()throws ClassNotFoundException,SQLException
{
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url="jdbc:mysql://localhost:3306/assesment?useSSL=false";
	String username="root";
	String password="true";
	Connection con=DriverManager.getConnection(url,username,password);
	return con;
}
}
