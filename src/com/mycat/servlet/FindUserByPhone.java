package com.mycat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mycat.dao.UserDao;
import com.mycat.entity.User;

/**
 * Servlet implementation class FindUserByPhone
 */
@WebServlet("/findUser")
public class FindUserByPhone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDao userDao = new UserDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindUserByPhone() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			User user = null;
			String phone = request.getParameter("phone");
			user = this.userDao.findUserByPhone(phone);
			if (user != null) {
				Gson gson = new Gson();
				String param = gson.toJson(user);
				out.println(param);
			} else {
				out.println("no find");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			User user = null;
			String phone = request.getParameter("phone");
			user = this.userDao.findUserByPhone(phone);
			if (user != null) {
				Gson gson = new Gson();
				String param = gson.toJson(user);
				out.println(param);
			} else {
				out.println("no find");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

}
