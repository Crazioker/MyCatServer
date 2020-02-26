package com.mycat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycat.entity.Cat;
import com.mycat.entity.Check;
import com.mycat.entity.User;
import com.mycat.util.DBHelper;

public class CheckDao {
	// 获取所有打卡项
	public List<Check> getChecks() {
		List<Check> checks = new ArrayList<Check>();
		Check check = null;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql1 = "SELECT * FROM MyCat.`Check` ";
		try {
			stat = con.prepareStatement(sql1);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				check = new Check();

				check.setCheckId(rs.getInt("checkId"));
				check.setPhone(rs.getString("phone"));
				check.setCheckItem(rs.getString("checkItem"));
				check.setDate(rs.getString("date"));
				check.setState(rs.getInt("state"));
				checks.add(check);
			}
		} catch (SQLException ex) {
			System.out.println("dao异常" + ex.toString());
		} finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		return checks;
	}

	// 根据用户的电话和打卡状态state=0获取特定打卡项
	public List<Check> getChecksByPhone(String phone) {
		List<Check> checks = new ArrayList<Check>();
		Check check = null;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql2 = "SELECT * FROM MyCat.`Check` where state=0 and phone=?";
		try {
			stat = con.prepareStatement(sql2);
			stat.setString(1, phone);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				check = new Check();

				check.setCheckId(rs.getInt("checkId"));
				check.setPhone(phone);
				check.setCheckItem(rs.getString("checkItem"));
				check.setDate(rs.getString("date"));
				check.setState(rs.getInt("state"));
				checks.add(check);
			}
		} catch (SQLException ex) {
			System.out.println("dao异常" + ex.toString());
		} finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		return checks;
	}

	// 添加打卡项
	public int addCheckItem(Check check) {
		int rows = 0;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql3 = "insert into MyCat.`Check` (phone,checkItem,state) values (?,?,0)";

		try {
			stat = con.prepareStatement(sql3);
			stat.setString(1, check.getPhone());
			stat.setString(2, check.getCheckItem());
			rows = stat.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		return rows;
	}

	// 删除打卡项
	public int deleteCheckItem(Check check) {
		int rows = 0;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql4 = "delete from MyCat.Check where phone=? and checkItem=?";

		try {
			stat = con.prepareStatement(sql4);
			stat.setString(1, check.getPhone());
			stat.setString(2, check.getCheckItem());
			rows = stat.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		return rows;
	}

	// 更新打卡项状态
	public int updateCheckItem(Check check) {
		int rows=0;
		Connection con=null;
		PreparedStatement stat=null;
		PreparedStatement stat1=null;
		con=DBHelper.connect();
		String sql5= "update MyCat.Check set state=1 ,date=? where phone=? and checkItem=?";
		
		//根据phone获取catId
		UserDao userDao=new UserDao();
		String phone=check.getPhone();
		User user=userDao.findUserByPhone(phone);
		String catId=user.getCatId();
		//根据catId获取cat对象
		CatDao catDao=new CatDao();
		Cat cat=catDao.findCatByCatId(catId);
		int value=cat.getValue()+50;
		int level=cat.getLevel();
		if(level==0)
			level=1;
		else
			level=(int)(Math.log(value/100)/Math.log(2))+1;
		String sql="update Cat set value=?, level=? where catId=?";
		
		
		try {
			stat=con.prepareStatement(sql5);
			stat.setString(1, check.getDate());
			stat.setString(2, check.getPhone());
			stat.setString(3, check.getCheckItem());
			rows = stat.executeUpdate();
			
			stat1=con.prepareStatement(sql);
			stat1.setInt(1, value);
			stat1.setInt(2, level);
			stat1.setString(3, catId);
			int res=stat1.executeUpdate();
			System.out.println(res);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		return rows;
	}

	// 根据用户的电话和打卡状态state=1获取特定打卡项
	public List<Check> getChecksByPhoneAndState(String phone) {
		List<Check> checks = new ArrayList<Check>();
		Check check = null;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql6 = "SELECT * FROM MyCat.`Check` where state=1 and phone=? order by date desc";
		try {
			stat = con.prepareStatement(sql6);
			stat.setString(1, phone);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				check = new Check();

				check.setCheckId(rs.getInt("checkId"));
				check.setPhone(phone);
				check.setCheckItem(rs.getString("checkItem"));
				check.setDate(rs.getString("date"));
				check.setState(rs.getInt("state"));
				checks.add(check);
			}
		} catch (SQLException ex) {
			System.out.println("dao异常" + ex.toString());
		} finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		return checks;
	}
}
