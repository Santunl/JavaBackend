package net.code.java;
import java.sql.*;

//import com.mysql.cj.protocol.Resultset;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MySQLTest {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/namedb";
		String username="root";
		String password="tiger";
		Scanner sc=new Scanner(System.in);
		System.out.println("enter first name");
		String s1=sc.next();
		System.out.println("enter last name");
		String s2=sc.next();
		
		try {
		Connection connection=DriverManager.getConnection(url,username,password);
		//Steps to insert data into database
		
		String sql1 ="INSERT INTO customer(firstname,lastname) VALUES(?,?)";
		PreparedStatement statement=connection.prepareStatement(sql1);
		statement.setString(1,s1);
		statement.setString(2,s2);
		
		int rows=statement.executeUpdate();
		if(rows>0) {
			System.out.println("rows has been inserted");
		}
		//statement.close();
		//Steps retrieve data from database
		String sq ="SELECT * FROM customer";
		Statement s =connection.createStatement();
		ResultSet result =s.executeQuery(sq);
		
		int count=0;
		while(result.next()) {
			String firstname=result.getString(1);
			String lastname=result.getString("lastname");
			count++;
			System.out.println("Customer "+count+": "+firstname+" "+lastname);			
		}
		connection.close();
		} catch(SQLException e) {
			System.out.println("oops,Error");
			e.printStackTrace();
		}

	}

}
