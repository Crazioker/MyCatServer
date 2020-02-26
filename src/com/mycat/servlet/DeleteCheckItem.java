package com.mycat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mycat.dao.CheckDao;
import com.mycat.entity.Check;

/**
 * Servlet implementation class DeleteCheckItem
 */
@WebServlet("/deleteCheckItem")
public class DeleteCheckItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CheckDao checkDao = new CheckDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCheckItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		try {
			Check check = null;
			check=new Check();
			String phone = request.getParameter("phone");
			//String checkItem = request.getParameter("checkItem");
			String checkItem = new String(request.getParameter("checkItem").getBytes("ISO-8859-1"),"utf-8");
//			System.out.println("Get");
//			System.out.println(phone);
//			System.out.println(checkItem);
			check.setPhone(phone);
			check.setCheckItem(checkItem);

			int rows = this.checkDao.deleteCheckItem(check);
			Gson gson = new Gson();
			String param = gson.toJson(rows);
			out.println(param);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		try {
			Check check = null;
			check=new Check();
			String phone = request.getParameter("phone");
			//System.out.println("Post");
			//System.out.println(phone);
			String checkItem = request.getParameter("checkItem");
			//System.out.println(checkItem);
			check.setPhone(phone);
			check.setCheckItem(checkItem);

			int rows = this.checkDao.deleteCheckItem(check);
			Gson gson = new Gson();
			String param = gson.toJson(rows);
			out.println(param);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

}
