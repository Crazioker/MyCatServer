package com.mycat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycat.dao.CatDao;
import com.mycat.entity.Cat;

/**
 * Servlet implementation class UpdateCatName
 */
@WebServlet("/updateCatName")
public class UpdateCatName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CatDao catDao=new CatDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCatName() {
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
			Cat cat;
			cat=new Cat();
			String catId=request.getParameter("catId");
			String catName = new String(request.getParameter("catName").getBytes("ISO-8859-1"),"utf-8");

			cat.setCatId(catId);
			cat.setCatName(catName);
			
			boolean reslut = this.catDao.updateCatname(cat);

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
			Cat cat;
			cat=new Cat();
			String catId=request.getParameter("catId");
			String catName = request.getParameter("catName");

			cat.setCatId(catId);
			cat.setCatName(catName);
			
			boolean reslut = this.catDao.updateCatname(cat);

			out.println(reslut);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

}
