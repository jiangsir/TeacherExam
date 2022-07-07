package jiangsir.teacherexam.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Tables.Log;

public class LogDAO extends SuperDAO<Log> {

	public LogDAO() {
	}

	public ArrayList<Log> getLogsByIP(String ip) {
		String sql = "SELECT * FROM logs WHERE ip='" + ip
				+ "' ORDER BY id DESC";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, ip);
			return this.executeQuery(pstmt, Log.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Log>();
	}

	@Override
	public synchronized int insert(Log log) {
		String sql = "INSERT INTO logs(`method`, `uri`, `session_pid`, `ip`, `title`, `subtitle`, "
				+ "`stacktrace`, `debug`, `timestamp`) VALUES (?,?,?,?,?, ?,?,?,?);";
		PreparedStatement pstmt;
		int id = 0;
		try {
			pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, log.getMethod());
			pstmt.setString(2, log.getUri());
			pstmt.setString(3, log.getSession_pid());
			pstmt.setString(4, log.getIp());
			pstmt.setString(5, log.getTitle());
			pstmt.setString(6, log.getSubtitle());
			pstmt.setString(7, log.getStacktrace());
			pstmt.setString(8, log.getDebug());
			pstmt.setTimestamp(9, new Timestamp(log.getTimestamp().getTime()));
			executeUpdate(pstmt);
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public int update(Log t) throws DataException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(int i) {
		// TODO Auto-generated method stub
		return false;
	}

}
