package com.mycat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mycat.dao.UserDao;
import com.mycat.entity.User;

/**
 * Servlet implementation class TokenVerify
 */
@WebServlet(name = "tokenVerify", urlPatterns = { "/tokenVerify" })
public class TokenVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDao userDao=new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TokenVerify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token=request.getHeader("token");
        PrintWriter out = response.getWriter();
		Token c=new Token();
		try {
		Map<String, Claim> claims =c.verifyToken(token);

        String phone=claims.get("user_id").asString();
        User user=userDao.findUserByPhone(phone);
        if(user==null)
        	out.print(2);
        else
        	out.print(1);
        
		}catch(Exception e){
			out.print(0);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token=request.getHeader("token");
        PrintWriter out = response.getWriter();
		Token c=new Token();
		try {
		Map<String, Claim> claims =c.verifyToken(token);

        String phone=claims.get("user_id").asString();
        User user=userDao.findUserByPhone(phone);
        if(user==null)
        	out.print(2);
        else
        	out.print(1);
        
		}catch(Exception e){
			out.print(0);
		}
		
	}

}
