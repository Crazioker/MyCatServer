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
 * Servlet implementation class GetCheck
 */
@WebServlet(urlPatterns = {"/getCheck"})
public class GetCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CheckDao checkDao=new CheckDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCheck() {
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
            List<Check> checks = this.checkDao.getChecks();
            Gson gson = new Gson();
            String param = gson.toJson(checks);
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
            List<Check> checks = this.checkDao.getChecks();
            Gson gson = new Gson();
            String param = gson.toJson(checks);
            out.println(param);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
    }

}
