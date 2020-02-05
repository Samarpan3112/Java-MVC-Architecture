package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FilterTransfer
 */
public class FilterTransfer implements Filter {

    private HttpSession session;
	/**
     * Default constructor. 
     */
    public FilterTransfer() {
        // TODO Auto-generated constructor stub
    }
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		String amt=request.getParameter("amount");
		int amount=Integer.parseInt(amt);
		session=((HttpServletRequest)(request)).getSession();
		int balance=(int)session.getAttribute("balance");
		System.out.print(balance);
		System.out.print(amount);
		
		//int balance=Integer.parseInt(bal);
		if(amount>balance)
		{
			((HttpServletResponse) (response)).sendRedirect("/PROJECT1/Insufficeint.html");
		}
		else
		{
			System.out.println("Transfer");
			chain.doFilter(request, response);
			
		}
	}


}
