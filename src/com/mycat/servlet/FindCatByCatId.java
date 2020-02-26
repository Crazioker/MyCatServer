package com.mycat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mycat.dao.CatDao;
import com.mycat.entity.Cat;

/**
 * Servlet implementation class FindCatByCatId
 */
@WebServlet("/findCat")
public class FindCatByCatId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CatDao catDao = new CatDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindCatByCatId() {
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
			Cat cat = null;
			String catId = request.getParameter("catId");
			cat = this.catDao.findCatByCatId(catId);
			if (cat != null) {
				Gson gson = new Gson();
				String param = gson.toJson(cat);
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
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		try {
			Cat cat = null;
			String catId = request.getParameter("catId");
			cat = this.catDao.findCatByCatId(catId);
			if (cat != null) {
				Gson gson = new Gson();
				String param = gson.toJson(cat);
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
