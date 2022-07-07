/**
 * idv.jiangsir.DAOs - UserDAO.java
 * 2008/4/29 下午 05:46:51
 * jiangsir
 */
package jiangsir.teacherexam.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Tables.Scorecsv;

/**
 * @author jiangsir
 * 
 */
public class ScorecsvDAO extends SuperDAO<Scorecsv> {

	public synchronized int insert(Scorecsv sb) throws DataException {
		String sql = "INSERT INTO scorecsv (`examid`, `seatid`, `step1`, `step2`, `step3`, `step4`) "
				+ "VALUES (?,?,?,?,?,?);";
		int id = 0;
		PreparedStatement pstmt;
		try {
			pstmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, sb.getExamid());
			pstmt.setString(2, sb.getSeatid());
			pstmt.setString(3, sb.getStep1());
			pstmt.setString(4, sb.getStep2());
			pstmt.setString(5, sb.getStep3());
			pstmt.setString(6, sb.getStep4());
			this.executeUpdate(pstmt);
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			rs.close();
			pstmt.close();
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException(e);
		}
	}

	/**
	 * 取得指定 exam 的所有考生成績表。
	 * 
	 * @param examid
	 * @return
	 */
	public ArrayList<Scorecsv> getScorecsvByExamid(int examid) {
		String sql = "SELECT * FROM scorecsv WHERE examid=?";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setInt(1, examid);
			return this.executeQuery(pstmt, Scorecsv.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Scorecsv>();
	}

	/**
	 * 取得指定 exam 指定 身分證的單一個人所有成績表。包含初試、複試、三試、四試
	 * 
	 * @param examid
	 * @param seatid
	 * @return
	 */
	public Scorecsv getScorecsvByExamidSeatid(int examid, String seatid) {
		String sql = "SELECT * FROM scorecsv WHERE examid=? AND seatid=?";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setInt(1, examid);
			pstmt.setString(2, seatid);
			for (Scorecsv scorecsv : this.executeQuery(pstmt, Scorecsv.class)) {
				return scorecsv;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(Scorecsv scorecsv) throws DataException {
		String SQL = "UPDATE scorecsv SET step1=?, step2=?, step3=?, step4=? " + "WHERE id=?";
		int result = -1;
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(SQL);
			pstmt.setString(1, scorecsv.getStep1());
			pstmt.setString(2, scorecsv.getStep2());
			pstmt.setString(3, scorecsv.getStep3());
			pstmt.setString(4, scorecsv.getStep4());
			pstmt.setInt(5, scorecsv.getId());
			result = executeUpdate(pstmt);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int i) {
		// TODO Auto-generated method stub
		return false;
	}

}
