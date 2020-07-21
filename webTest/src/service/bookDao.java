package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class bookDao {
	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static String DB_URL = "jdbc:mysql://localhost:3306/booksystem?useUnicode=true&characterEncoding=UTF-8";
    private static String USER = "root";
    private static String PASS = "py116114";
    public static Connection getConnection()
    {
    	Connection con=null;
    	try {
    		Class.forName(JDBC_DRIVER);
        	con = DriverManager.getConnection(DB_URL,USER,PASS);

    	}catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    	return con;
    }
}
