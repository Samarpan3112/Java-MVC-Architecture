package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;


public class CheckBalance extends HttpServlet 
{
	private HttpSession session;
	private String accno1=null;
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Model m = new Model();
		session=request.getSession();
		int accno=(int)session.getAttribute("accno");
		m.setAccno(accno);
		
		try 
		{
			boolean value=m.checkBalance();
			if(value==true)
			{
				session=request.getSession();
				session.setAttribute("balance", m.getBalance());
				response.sendRedirect("/PROJECT1/DisplayBalance.jsp");
			}
			else
			{
				response.sendRedirect("/PROJECT1/balancefail.html");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
