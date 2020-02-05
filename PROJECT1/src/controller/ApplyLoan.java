package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import model.Model;

public class ApplyLoan extends HttpServlet 
{

	private HttpSession session;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// filepart=request.getParameter("aadhar");
		//InputStream is=filepart.getInputStream();
		Model m = new Model();
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		try
		{
			Part p=request.getPart("aadhar");
			String s=p.getContentType();
			boolean value=m.loan(s);
			if(value==true)
			{
				response.sendRedirect("/PROJECT1/aadharSuccess.html");
				
			}
			else
			{
				response.sendRedirect("/PROJECT1/aadharFailure.html");
			}
			//InputStream i=m.login(is);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//Part filePart = request.getPart("file");
	     //InputStream fileContent = filePart.getInputStream();
	     //InputStream in = new ByteArrayInputStream(string.getBytes("UTF-8"));
	     //ServletInputStream in1=request.getInputStream();
		//session=request.getSession();
		//session.setAttribute("aadhar",is);
		//String os=filepart.toString();
		//String os=request.getParameter("aadhar");
		//byte b[]=os.getBytes();
		//String imageDataString = Base64.encode(b);		
	}

}
