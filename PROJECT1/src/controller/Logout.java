package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Logout extends HttpServlet 
{

	private HttpSession session;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		session=request.getSession();
		session.invalidate();
		response.sendRedirect("http://localhost:9090/PROJECT1/");
	}

}
