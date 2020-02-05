package model;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import oracle.jdbc.driver.OracleDriver;

public class Model
{
	String url="jdbc:oracle:thin:@//localhost:1521/XE";
	String user="system";
	String pwd="samar";
	private Connection con;
	
	private String name;
	private int accno;
	private int balance;
	private String userid;
	private String password;
	private String email;
	PreparedStatement pstmt;
	ResultSet res;
	private HttpSession session=null;
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getAccno()
	{
		return accno;
	}

	public void setAccno(int accno)
	{
		this.accno = accno;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Model()
	{
		try 
		{
			DriverManager.registerDriver(new OracleDriver());
			con=DriverManager.getConnection(url, user, pwd);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public boolean login()throws Exception
	{
		String sql="SELECT ACCNO FROM BANK WHERE USERID=? AND PASSWORD=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1,userid);
		pstmt.setString(2,password);
		res=pstmt.executeQuery();
		
		if(res.next()==true)
		{
			accno=res.getInt("ACCNO");
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean checkBalance()throws Exception
	{
		String sql="SELECT BALANCE FROM BANK WHERE ACCNO=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,accno);
		res=pstmt.executeQuery();
		
		if(res.next()==true)
		{
			balance=res.getInt("BALANCE");
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean changePassword(String npwd)throws Exception
	{
		String sql="UPDATE BANK SET PASSWORD=? WHERE ACCNO=? AND PASSWORD=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, npwd);
		pstmt.setInt(2, accno);
		pstmt.setString(3, password);
		int row=pstmt.executeUpdate();
		if(row==0)
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}
	
	public boolean transfer1(int amount)throws Exception
	{
		String sql="UPDATE BANK SET BALANCE=BALANCE-? WHERE ACCNO=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, amount);
		pstmt.setInt(2, accno);
		int row=pstmt.executeUpdate();
		if(row==0)
		{
			return false;
		}
		else
		{
			statement1(amount);
			return true;
		}
	}
	
	public boolean transfer2(int amount,int rvcno)throws Exception
	{
		String sql="UPDATE BANK SET BALANCE=BALANCE+? WHERE ACCNO=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, amount);
		pstmt.setInt(2, rvcno);
		int row=pstmt.executeUpdate();
		if(row==0)
		{
			return false;
		}
		else
		{
			statement2(amount,rvcno);
			return true;
		}
		
	}
	
	public void statement1(int amount)throws Exception
	{
		String sql="INSERT INTO STATEMENT(ACCNO,DEBIT) VALUES(?,?)";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, accno);
		pstmt.setInt(2, amount);
		pstmt.executeUpdate();
		
	}
	public void statement2(int amount,int rvcno)throws Exception
	{
		String sql="INSERT INTO STATEMENT(ACCNO,CREDIT) VALUES(?,?)";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, rvcno);
		pstmt.setInt(2, amount);
		pstmt.executeUpdate();
		
	}
	public ArrayList getCredit()throws Exception
	{
		ArrayList al1 = new ArrayList();
		String sql="SELECT CREDIT FROM STATEMENT WHERE ACCNO=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, accno);
		res=pstmt.executeQuery();
		while(res.next()==true)
		{
			al1.add(res.getInt("CREDIT"));
		}
		return al1;
	}
	public ArrayList getDebit()throws Exception
	{
		ArrayList al2 = new ArrayList();
		String sql="SELECT DEBIT FROM STATEMENT WHERE ACCNO=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, accno);
		res=pstmt.executeQuery();
		while(res.next()==true)
		{
			al2.add(res.getInt("DEBIT"));
		}
		return al2;
	}
	public boolean loan(String is)throws Exception
	{
		String sql="INSERT INTO LOAN(IMAGE) VALUES(?)";
		pstmt=con.prepareStatement(sql);
		Blob b=con.createBlob();
		b.setBytes(1, is.getBytes());
		pstmt.setBlob(1, b);
		//int row=0;
		int row=pstmt.executeUpdate();
		if(row==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	
	}
	
	public boolean forgotPass(String np,String email)throws Exception
	{
		String sql="UPDATE BANK SET PASSWORD=? WHERE EMAIL=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, np);
		pstmt.setString(1, email);
		int row=pstmt.executeUpdate();
		if(row==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
