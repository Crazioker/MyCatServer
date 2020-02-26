package com.mycat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycat.dao.UserDao;
import com.mycat.entity.User;


/**
 * Servlet implementation class UpdateUsername
 */
@WebServlet("/updateUsername")
public class UpdateUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDao userDao=new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUsername() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		try {
			User user;
			user=new User();
			String phone=request.getParameter("phone");
			String username =request.getParameter("username");

			user.setPhone(phone);
			user.setUsername(username);

			boolean reslut = this.userDao.updateUsername(user);

			out.println(reslut);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		try {
			User user;
			user=new User();
			String phone=request.getParameter("phone");
			String username = new String(request.getParameter("username").getBytes("ISO-8859-1"),"utf-8");

			user.setPhone(phone);
			user.setUsername(username);

			boolean reslut = this.userDao.updateUsername(user);

			out.println(reslut);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

}
