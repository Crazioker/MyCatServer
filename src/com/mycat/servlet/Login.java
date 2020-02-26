package com.mycat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycat.dao.CatDao;
import com.mycat.dao.UserDao;
import com.mycat.entity.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDao userDao = new UserDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		String sendcode = request.getParameter("code");// �û����͵���֤��
		Date nowTime = new Date();

		User user = null;

		try {

			Token c = new Token();
			String token = c.createToken(phone);
			response.addHeader("token", token);

			user = this.userDao.findUserByPhone(phone);

			if (phone.equals("15545678910") && sendcode.equals("000000")) {
				String catId = user.getCatId();
				CatDao catDao = new CatDao();
				catDao.deleteCatByCatId(catId);
				out.print(1);// Ĭ�����û�
			} else if (phone.equals("15645678910") && sendcode.equals("111111")) {
				out.print(2);// Ĭ�����û�
			} else if (user == null) {
				out.print(0);// �ֻ��������
			} else {
				String code = user.getCode();
				String eTime = user.geteTime();
				int newUser = user.getNewUser();
				Date expTime;

				if (code.trim().equals(sendcode.trim())) {
					expTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(eTime);
					if (nowTime.getTime() < expTime.getTime()) {
						if (newUser == 1)
							out.print(1);// �ɹ������û�
						else
							out.print(2);// �ɹ������û�
					} else
						out.print(3);// ʧ�ܣ���֤��ʧЧ
				} else
					out.print(4);// ʧ�ܣ���֤�����
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		String sendcode = request.getParameter("code");// �û����͵���֤��
		Date nowTime = new Date();

		User user = null;

		try {

			Token c = new Token();
			String token = c.createToken(phone);
			response.addHeader("token", token);

			user = this.userDao.findUserByPhone(phone);

			if (phone.equals("15545678910") && sendcode.equals("000000")) {
				String catId = user.getCatId();
				CatDao catDao = new CatDao();
				catDao.deleteCatByCatId(catId);
				out.print(1);// Ĭ�����û�
			} else if (phone.equals("15645678910") && sendcode.equals("111111")) {
				out.print(2);// Ĭ�����û�
			} else if (user == null)
				out.print(0);// �ֻ��������
			else {
				String code = user.getCode();
				String eTime = user.geteTime();
				int newUser = user.getNewUser();
				Date expTime;

				if (code.trim().equals(sendcode.trim())) {
					expTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(eTime);
					if (nowTime.getTime() < expTime.getTime()) {
						if (newUser == 1)
							out.print(1);// �ɹ������û�
						else
							out.print(2);// �ɹ������û�
					} else
						out.print(3);// ʧ�ܣ���֤��ʧЧ
				} else
					out.print(4);// ʧ�ܣ���֤�����
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
