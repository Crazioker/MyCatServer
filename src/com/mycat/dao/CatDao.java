package com.mycat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycat.entity.Cat;
import com.mycat.util.DBHelper;

public class CatDao {
	// 获取所有猫
	public List<Cat> getCats() {
		List<Cat> cats = new ArrayList<Cat>();
		Cat cat = null;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql1 = "select * from Cat ";
		try {
			stat = con.prepareStatement(sql1);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				cat = new Cat();
				cat.setCatId(rs.getString("catId"));
				cat.setCatName(rs.getString("catName"));
				cat.setType(rs.getString("type"));
				cat.setLevel(rs.getInt("level"));
				cat.setValue(rs.getInt("value"));
				cats.add(cat);
			}
		} catch (SQLException ex) {
			System.out.println("dao异常" + ex.toString());
		} finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		return cats;
	}

	// 根据猫的Id获取猫
	public Cat findCatByCatId(String catId) {
		Cat cat = null;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql2 = "select * from Cat where catId=?";
		try {
			stat = con.prepareStatement(sql2);
			stat.setString(1, catId);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				cat = new Cat();
				cat.setCatId(catId);
				cat.setCatName(rs.getString("catName"));
				cat.setType(rs.getString("type"));
				cat.setLevel(rs.getInt("level"));
				cat.setValue(rs.getInt("value"));
			}
		} catch (SQLException ex) {
			System.out.println("dao异常" + ex.toString());
		} finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		return cat;
	}
	// 根据猫的Id删除猫
	public boolean deleteCatByCatId(String catId) {
		int rows=0;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql = "delete from Cat where catId=?";
		try {
			stat = con.prepareStatement(sql);
			stat.setString(1, catId);
			rows=stat.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("dao异常" + ex.toString());
		} finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		if(rows>=0)
			return true;
		else
			return false;
	}
	
	// 根据猫的Id更新猫的名称
	public boolean updateCatname(Cat cat) {
		int rows=0;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql = "update Cat set catName=? where catId=?";
		try {
			String catName=cat.getCatName();
			String catId=cat.getCatId();
			stat = con.prepareStatement(sql);
			stat.setString(1, catName);
			stat.setString(2, catId);
			rows=stat.executeUpdate();

		} catch (SQLException ex) {
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
