package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;


public class GetStatement extends HttpServlet 
{

	private HttpSession session;
	private boolean val1;
	private boolean val2;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		session=request.getSession();
		int accno=(int)session.getAttribute("accno");
		Model m = new Model();
		m.setAccno(accno);
		try
		{
		ArrayList al1=m.getCredit();
		ArrayList al2=m.getDebit();
		val1=al1.isEmpty();
		val2=al2.isEmpty();
		if(val1 && val2==true)
		{
			response.sendRedirect("/PROJECT1/StatementFail.html");
		}
		else
		{
			session.setAttribute("Credit", al1);
			session.setAttribute("Debit", al2);
			response.sendRedirect("/PROJECT1/StatementSuccess.jsp");
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
