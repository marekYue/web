package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.book;
import service.bookDao;
@WebServlet("/servlet")
public class serverletTest extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String str1=req.getParameter("username");
		String str2=req.getParameter("password");
		Connection con=bookDao.getConnection();
		List<book> book=new ArrayList();
		Statement stm=null;
		try {
			stm=con.createStatement();
			ResultSet rs=stm.executeQuery("select id,name,quantity,enable from book");
			while(rs.next())
			{
				book b=new book();
				b.setId(rs.getString("id"));
				b.setName(rs.getString("name"));
				b.setQuantity(rs.getInt("quantity"));
				b.setEnable(rs.getInt("enable"));
				book.add(b);
			}
			stm.close();
		}catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stm!=null) stm.close();
            }catch(SQLException se2){
            }
            try{
                if(con!=null) con.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
		System.out.println(str1);
		resp.setContentType("text/json;charset=utf-8");
		Gson gson=new Gson();
		String json=gson.toJson(book);
		resp.getWriter().write(json);
		
		//resp.sendRedirect("./demo.html");
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
		
}
