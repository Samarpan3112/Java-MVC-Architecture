package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;


public class TransferAmount extends HttpServlet 
{

	private HttpSession session;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String amt=request.getParameter("amount");
		int amount=Integer.parseInt(amt);
		String rvc=request.getParameter("rvcno");
		int rvcno=Integer.parseInt(rvc);
		session=request.getSession();
		int accno=(int)session.getAttribute("accno");
		//int balance=(int)session.getAttribute("balance");
		Model m = new Model();
		m.setAccno(accno);
		try
		{
		boolean value=m.transfer1(amount);
		if(value==true)
		{
			boolean value1=m.transfer2(amount, rvcno);
			if(value1==true)
			{
				response.sendRedirect("/PROJECT1/transferSuccess.html");
			}
		}
		else
		{
			response.sendRedirect("/PROJECT1/failureSuccess.html");
		}
		
		
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		

	}

}
