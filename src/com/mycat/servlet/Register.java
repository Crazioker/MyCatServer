package com.mycat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mycat.dao.UcjoinDao;
import com.mycat.entity.Ucjoin;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UcjoinDao ucjoinDao=new UcjoinDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
			Ucjoin uc;
			uc=new Ucjoin();
			String phone = request.getParameter("phone");
			String type = new String(request.getParameter("type").getBytes("ISO-8859-1"),"utf-8");
			String catId = new String(request.getParameter("catId").getBytes("ISO-8859-1"),"utf-8");
			String username = new String(request.getParameter("username").getBytes("ISO-8859-1"),"utf-8");
			int gender = Integer.parseInt(request.getParameter("gender").trim());

			uc.setPhone(phone);
			uc.setType(type);
			uc.setCatId(catId);
			uc.setUsername(username);
			uc.setGender(gender);

			boolean reslut = this.ucjoinDao.updateUC(uc);

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
			Ucjoin uc;
			uc=new Ucjoin();
			String phone = request.getParameter("phone");
			String type = request.getParameter("type");
			String catId = request.getParameter("catId");
			String username = request.getParameter("username");
			int gender = Integer.parseInt(request.getParameter("gender").trim());

			uc.setPhone(phone);
			uc.setType(type);
			uc.setCatId(catId);
			uc.setUsername(username);
			uc.setGender(gender);

			boolean reslut = this.ucjoinDao.updateUC(uc);

			out.println(reslut);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

}
