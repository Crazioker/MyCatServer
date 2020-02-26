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
import com.mycat.dao.UcjoinDao;
import com.mycat.entity.Ucjoin;

/**
 * Servlet implementation class GetUC
 */
@WebServlet("/getUC")
public class GetUC extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UcjoinDao ucjoinDao=new UcjoinDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUC() {
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
        PrintWriter out = response.getWriter();
        try {
        	List<Ucjoin> ucs = this.ucjoinDao.getUcjoin();
            Gson gson = new Gson();
            String param = gson.toJson(ucs);
            out.println(param);

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	List<Ucjoin> ucs = this.ucjoinDao.getUcjoin();
            Gson gson = new Gson();
            String param = gson.toJson(ucs);
            out.println(param);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
	}

}
