package com.mycat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycat.entity.Ucjoin;
import com.mycat.util.DBHelper;

public class UcjoinDao {
	// 获取User和Cat连接表的内容
	public List<Ucjoin> getUcjoin() {
		List<Ucjoin> ucs = new ArrayList<Ucjoin>();
		Ucjoin uc = null;
		Connection con = null;
		PreparedStatement stat = null;
		con = DBHelper.connect();
		String sql1 = "select * from User, Cat where User.catId=Cat.catId order by level desc";
		try {
			stat = con.prepareStatement(sql1);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				uc = new Ucjoin();
				uc.setCatId(rs.getString("catId"));
				uc.setCatName(rs.getString("catName"));
				uc.setType(rs.getString("type"));
				uc.setLevel(rs.getInt("level"));
				uc.setValue(rs.getInt("value"));
				uc.setPhone(rs.getString("phone"));
				uc.setUsername(rs.getString("username"));
				uc.setGender(rs.getInt("gender"));
				ucs.add(uc);
			}
		} catch (SQLException ex) {
			System.out.println("dao异常" + ex.toString());
		} finally {
			DBHelper.closePreparedStatement(stat);
			DBHelper.closeConneciton(con);
		}
		return ucs;
	}

	// 同时更新User和Cat表
	public boolean updateUC(Ucjoin uc) {
		int rows = 0;
		Connection con = null;
		PreparedStatement stat1 = null;
		PreparedStatement stat2 = null;
		con = DBHelper.connect();

		System.out.println(uc.getUsername() + " " + uc.getGender() + " " + uc.getCatId() + " " + uc.getType());

		String sql1 = "update MyCat.User set username=? ,gender=? ,catId=?, newUser=0 where phone=?";
		String sql2="insert into MyCat.Cat(catId, `type`, catName) values(?, ?, ?)";
		try {
			stat1 = con.prepareStatement(sql1);
			stat1.setString(1, uc.getUsername());
			stat1.setInt(2, uc.getGender());
			stat1.setString(3, uc.getCatId());
			stat1.setString(4, uc.getPhone());
			stat2 = con.prepareStatement(sql2);
			stat2.setString(1, uc.getCatId());
			stat2.setString(2, uc.getType());
			stat2.setString(3, uc.getCatId());
			rows = stat1.executeUpdate();
			
			rows+=stat2.executeUpdate();

//			System.out.println(uc.getUsername()+" "+uc.getGender()+" "+uc.getCatId()+" "+uc.getType());

			System.out.println(rows);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHelper.closePreparedStatement(stat1);
			DBHelper.closePreparedStatement(stat2);
			DBHelper.closeConneciton(con);
		}
		if (rows > 1)
			return true;
		else
			return false;
	}

}
