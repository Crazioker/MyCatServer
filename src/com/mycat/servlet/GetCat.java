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
import com.mycat.dao.CatDao;
import com.mycat.entity.Cat;


/**
 * Servlet implementation class GetCat
 */
@WebServlet(urlPatterns = {"/getCat"})
public class GetCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CatDao catDao=new CatDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            List<Cat> cats = this.catDao.getCats();
            Gson gson = new Gson();
            String param = gson.toJson(cats);
            out.println(param);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            List<Cat> cats = this.catDao.getCats();
            Gson gson = new Gson();
            String param = gson.toJson(cats);
            out.println(param);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
    }

}
