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
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Tables.Scoreboard;

/**
 * @author jiangsir
 * 
 */
public class ScoreboardDAO extends SuperDAO<Scoreboard> {

	public synchronized int insert(Scoreboard sb) throws DataException {
		String sql = "INSERT INTO scoreboard (`pid`, `examid`, `teach_score`,"
				+ " `benke_score`, `teach_tscore`, `benke_tscore`, "
				+ "`teach_tscore_percent`, `benke_tscore_percent`, `total_score`, `result`) "
				+ "VALUES (?,?,?,?,?, ?,?,?,?,?);";
		int id = 0;
		PreparedStatement pstmt;
		try {
			pstmt = getConnection().prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, sb.getPid());
			pstmt.setString(2, sb.getExamid());
			pstmt.setDouble(3, sb.getTeach_score());
			pstmt.setDouble(4, sb.getBenke_score());
			pstmt.setDouble(5, sb.getTeach_tscore());
			pstmt.setDouble(6, sb.getBenke_tscore());
			pstmt.setDouble(7, sb.getTeach_tscore_percent());
			pstmt.setDouble(8, sb.getBenke_tscore_percent());
			pstmt.setDouble(9, sb.getTotal_score());
			pstmt.setString(10, sb.getResult());
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

	// private synchronized int executeUpdate(PreparedStatement pstmt)
	// throws DataException {
	// long starttime = System.currentTimeMillis();
	// int result = -1;
	// try {
	// result = pstmt.executeUpdate();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// if (e.getLocalizedMessage().contains(
	// "Packet for query is too large")) {
	// throw new DataException("檔案太大不能上傳！");
	// } else {
	// throw new DataException(e.getLocalizedMessage());
	// }
	// }
	// System.out.println("PSTMT_SQL=" + pstmt.toString() + " 共耗時 "
	// + (System.currentTimeMillis() - starttime) + " ms");
	// return result;
	// }

	public Scoreboard getScoreboardByPid(String pid) {
		String sql = "SELECT * FROM scoreboard WHERE pid=?";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, pid);
			for (Scoreboard scoreboard : this.executeQuery(pstmt,
					Scoreboard.class)) {
				return scoreboard;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Scoreboard();
	}

	@Override
	public int update(Scoreboard t) throws DataException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(int i) {
		// TODO Auto-generated method stub
		return false;
	}

}
