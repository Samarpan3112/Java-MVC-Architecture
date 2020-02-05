package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;


public class ChangePassword extends HttpServlet 
{

	private HttpSession session;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Model m = new Model();
		session=request.getSession();
		int accno=(int)session.getAttribute("accno");
		m.setAccno(accno);
		m.setPassword(request.getParameter("opwd"));
		String npwd=request.getParameter("npwd");
		//request.getParameter("cpwd");
		
		try
		{
			boolean value=m.changePassword(npwd);
			if(value==true)
			{
				session.invalidate();
				response.sendRedirect("/PROJECT1/successChange.html");
				
			}
			else
			{
				response.sendRedirect("/PROJECT1/changeFail.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
