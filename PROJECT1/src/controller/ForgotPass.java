package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;


public class ForgotPass extends HttpServlet 
{
	
	private HttpSession session;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Model m = new Model();
		session=request.getSession();
		m.setPassword(request.getParameter("np"));
		String np=request.getParameter("np");
		String email=(String)session.getAttribute("email");
		try 
		{
			boolean value=m.forgotPass(np,email);
			if(value==true)
			{
				
			}
			else
			{
				
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		
		}
		
	}

}
