package controller;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Model;
public class Login extends HttpServlet {
	private HttpSession session;
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Model m  = new Model();
		m.setUserid(request.getParameter("userid"));
		m.setPassword(request.getParameter("password"));
		try
		{
			boolean value=m.login();
			if (value==true)
			{
				session=request.getSession(true);
				session.setAttribute("accno", m.getAccno());
				session.setAttribute("balance", m.getBalance());
				response.sendRedirect("/PROJECT1/home.html");
			}
			else
			{
				response.sendRedirect("/PROJECT1/loginfail.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
