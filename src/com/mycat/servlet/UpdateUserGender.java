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
 * Servlet implementation class UpdateUserGender
 */
@WebServlet("/updateUserGender")
public class UpdateUserGender extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDao userDao=new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserGender() {
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
			int gender = Integer.parseInt(request.getParameter("gender").trim());
			user.setPhone(phone);
			user.setGender(gender);

			boolean reslut = this.userDao.updateGender(user);

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
			int gender = Integer.parseInt(request.getParameter("gender").trim());
			user.setPhone(phone);
			user.setGender(gender);

			boolean reslut = this.userDao.updateGender(user);

			out.println(reslut);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

}
