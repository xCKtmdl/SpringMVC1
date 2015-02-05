package com.aaron.SpringMVC1.persistence;

import java.sql.*;

import javax.sql.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.support.*;
import java.util.*;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.aaron.SpringMVC1.domain.*;

public class SimpleJdbcSupportSpitterDao extends SimpleJdbcDaoSupport implements
		SpitterDao {

	@Autowired
	DataSource dataSource;

	private static final String SQL_INSERT_SPITTER = "insert into spitter (username, password, fullname, email, update_by_email) values (?, ?, ?, ?, ?)";

	private static final String SQL_UPDATE_SPITTER = "update spitter set username = ?, password = ?, fullname = ?, set email = ?"
			+ "where id = ?";

	private static final String SQL_SELECT_SPITTER = ""
			+ "select id, username, password, fullname, email from spitter";

	private static final String SQL_SELECT_SPITTER_BY_ID = SQL_SELECT_SPITTER
			+ " where id=?";

	// <start id="java_getSpitterById" />
	public Spitter getSpitterById(long id) {
		return getSimpleJdbcTemplate().queryForObject(SQL_SELECT_SPITTER_BY_ID,
				new ParameterizedRowMapper<Spitter>() {
					public Spitter mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Spitter spitter = new Spitter();
						spitter.setId(rs.getLong(1));
						spitter.setUsername(rs.getString(2));
						spitter.setPassword(rs.getString(3));
						spitter.setFullName(rs.getString(4));
						spitter.setEmail(rs.getString(5));
						return spitter;
					}
				}, id);
	}

	// <end id="java_getSpitterById" />

	// <start id="java_addSpitter" />
	public void addSpitter(Spitter spitter) {
		getSimpleJdbcTemplate().update(SQL_INSERT_SPITTER,
				spitter.getUsername(), spitter.getPassword(),
				spitter.getFullName(), spitter.getEmail(),
				spitter.isUpdateByEmail());
		spitter.setId(queryForIdentity());
	}

	// <end id="java_addSpitter" />

	public void saveSpitter(Spitter spitter) {
		getSimpleJdbcTemplate().update(SQL_UPDATE_SPITTER,
				spitter.getUsername(), spitter.getPassword(),
				spitter.getFullName(), spitter.getEmail(),

				spitter.getId());
	}

	// <start id="java_queryForIdentity" />
	private long queryForIdentity() {
		return getSimpleJdbcTemplate().queryForLong("call identity()");
	}

	// <end id="java_queryForIdentity" />

	public List<Spittle> getRecentSpittle(int count) {
		// TODO Auto-generated method stub
		/*
		 * int dkmckm=4; dkmckm=1+2; return null;
		 */

		List<Spittle> spittleList = new Vector<Spittle>();
		List<String> listTableNames = new Vector<String>();
		String sqlCommand = "select spittle_table from spitter_spittle order by spittle_id desc LIMIT "
				+ Integer.toString(count);

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sqlCommand);
			rs = stmt.executeQuery();

			while (rs.next()) {

				listTableNames.add(rs.getString("spittle_table"));
			}

		}

		catch (Exception e) {
			int integer1 = 2;
			integer1 = integer1 + 1;
		}

		finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {

			}
		}

		listTableNames.size();

		for (int i = 0; i < listTableNames.size(); i++) {

			String stringTableName = listTableNames.get(i);
			int spittleId = getSpittleIdFromTableName(stringTableName);
/*
			sqlCommand = "select * from " + stringTableName
					+ " where comment_id=1";
					*/
			
			sqlCommand = "SELECT t1.spittle_text,t1.comment_id,t1.comment_date,t2.spitter_id,t2.username,t2.fullname FROM " + stringTableName
				+ " t1 inner join spitter t2 on t1.spitter_id=t2.spitter_id"	+ " WHERE t1.comment_id = 1";
			
			conn = null;
			stmt = null;
			rs = null;
			Spitter spitter1 = new Spitter();
			
			
			Spittle spittle1 = new Spittle();
			spittle1.setId((long) spittleId);

			try {

				conn = dataSource.getConnection();
				stmt = conn.prepareStatement(sqlCommand);
				rs = stmt.executeQuery();

				while (rs.next()) {

					spittle1.setText(rs.getString("spittle_text"));
					int spitterId = rs.getInt("spitter_id");
					String spitterUsername = rs.getString("username");
					String spitterFullname = rs.getString("fullname");
					spittle1.setWhen(rs.getDate("comment_date"));
					spitter1.setFullName(spitterFullname);
					spitter1.setUsername(spitterUsername);
					spitter1.setId((long) spitterId); 
					spittle1.setSpitter(spitter1);
					spittleList.add(spittle1);

				}

			}

			catch (Exception e) {
				int integer1 = 2;
				integer1 = integer1 + 1;
			}

			finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {

				}
			}

		}

		return spittleList;

	}

	public void saveSpittle(Spittle spittle) {
		// TODO Auto-generated method stub

	}

	public List<Spittle> getSpittlesForSpitter(Spitter spitter) {
		// TODO Auto-generated method stub
		return null;
	}

	public Spitter getSpitterByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteSpittle(long id) {
		throw new UnsupportedOperationException();
	}

	public Spittle getSpittleById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Spitter> findAllSpitters() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getSpittleIdFromTableName(String stringTableName) {
		return Integer.parseInt(stringTableName.substring(
				stringTableName.indexOf("_") + 1, stringTableName.length()));
	}
}
