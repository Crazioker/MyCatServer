package com.mycat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mycat.dao.CheckDao;
import com.mycat.entity.Check;

/**
 * Servlet implementation class GetChecksByPhone
 */
@WebServlet("/findCheck")
public class GetChecksByPhone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CheckDao checkDao = new CheckDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetChecksByPhone() {
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
			List<Check> checks;
			String phone = request.getParameter("phone");
			String state=request.getParameter("state");
			if(state==null||(state.trim().equals("0"))) {
				checks = this.checkDao.getChecksByPhone(phone);
			}else {
				checks = this.checkDao.getChecksByPhoneAndState(phone);
			}
	
			if (checks != null) {
				Gson gson = new Gson();
				String param = gson.toJson(checks);
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
			List<Check> checks;
			String phone = request.getParameter("phone");
			String state=request.getParameter("state");
			if(state==null||(state.trim().equals("0"))) {
				checks = this.checkDao.getChecksByPhone(phone);
			}else {
				checks = this.checkDao.getChecksByPhoneAndState(phone);
			}
			if (checks != null) {
				Gson gson = new Gson();
				String param = gson.toJson(checks);
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
