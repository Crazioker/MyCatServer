package com.mycat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycat.entity.User;
import com.mycat.util.DBHelper;

public class UserDao {
	// 获取所有用户
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		User user = null;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql1 = "select * from User ";
		try {
			stat = con.prepareStatement(sql1);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				user = new User();

				user.setPhone(rs.getString("phone"));
				user.setUsername(rs.getString("username"));
				user.setCatId(rs.getString("catId"));
				user.setGender(rs.getInt("gender"));
				//user.setCatId(rs.getString("catId"));
				user.setCode(rs.getString("code"));
				user.seteTime(rs.getString("eTime"));
				user.setNewUser(rs.getInt("newUser"));
				users.add(user);
			}
		} catch (SQLException ex) {
			System.out.println("dao异常" + ex.toString());
		} finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		return users;
	}

	// 根据用户手机号码获取用户
	public User findUserByPhone(String phone) {
		User user = null;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql2 = "select * from User where phone=?";
		try {
			stat = con.prepareStatement(sql2);
			stat.setString(1, phone);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setPhone(phone);
				user.setUsername(rs.getString("username"));
				user.setGender(rs.getInt("gender"));
				user.setCatId(rs.getString("catId"));
				user.setCode(rs.getString("code"));
				user.seteTime(rs.getString("eTime"));
				user.setNewUser(rs.getInt("newUser"));
			}
		} catch (SQLException ex) {
			System.out.println("dao异常" + ex.toString());
		} finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		return user;
	}
	// 用户获取验证码后存入数据库
	public int insertCode(User user) {
		int rows=0;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql = "select * from User where phone=?";
		String sql2 = "update User set code=? ,eTime=? ,newUser=0 where phone=?";
		String sql3 = "insert into User (phone, code, eTime) values(?,?,?)";
		
		try {
			String phone=user.getPhone();
			String code=user.getCode();
			String eTime=user.geteTime();
			
			stat = con.prepareStatement(sql);
			stat.setString(1, phone);
			ResultSet rs = stat.executeQuery();
			//先判断数据库中是否存在用户,存在则更新数据，不存在则添加新用户
			if (rs.next()) {
				PreparedStatement stat2 = con.prepareStatement(sql2);
				stat2.setString(1, code);
				stat2.setString(2, eTime);
				stat2.setString(3, phone);
				rows=stat2.executeUpdate();
			}else {
				PreparedStatement stat3 = con.prepareStatement(sql3);
				stat3.setString(1, phone);
				stat3.setString(2, code);
				stat3.setString(3, eTime);
				rows=stat3.executeUpdate();
			}
		} catch (SQLException ex) {
			System.out.println("dao异常" + ex.toString());
		} finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		return rows;
	}
	//更新用户名
	public boolean updateUsername(User user) {
		int rows=0;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql = "update User set username=? where phone=?";
		
		String phone=user.getPhone();
		String username=user.getUsername();
		
		try {
			stat = con.prepareStatement(sql);
			stat.setString(2, phone);
			stat.setString(1, username);
			rows=stat.executeUpdate();
		}catch (SQLException ex) {
			System.out.println("dao异常" + ex.toString());
		} finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		if(rows==1)
			return true;
		else
			return false;
	}
	//更新性别
	public boolean updateGender(User user) {
		int rows=0;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql = "update User set gender=? where phone=?";
		
		String phone=user.getPhone();
		int gender=user.getGender();
		
		try {
			stat = con.prepareStatement(sql);
			stat.setInt(1, gender);
			stat.setString(2, phone);
			rows=stat.executeUpdate();
		}catch (SQLException ex) {
			System.out.println("dao异常" + ex.toString());
		} finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		if(rows==1)
			return true;
		else
			return false;
	}
}
