package jiangsir.teacherexam.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Scopes.ApplicationScope;
import jiangsir.teacherexam.Tables.Subject;

public class SubjectDAO extends SuperDAO<Subject> {

	@Override
	public int insert(Subject subject) throws DataException {
		String sql = "INSERT INTO subjects (`examid`,`zhengshidaili`, `nianduan`, `name`, `seatpattern`)"
				+ " VALUES (?,?,?,?,?);";
		int id = 0;
		PreparedStatement pstmt;
		try {
			pstmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, subject.getExamid());
			pstmt.setString(2, subject.getZhengshidaili().toString());
			pstmt.setString(3, subject.getNianduan().toString());
			pstmt.setString(4, subject.getName());
			pstmt.setString(5, subject.getSeatpattern());
			executeUpdate(pstmt);
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException(e.getLocalizedMessage());
		}
		return id;
	}

	@Override
	public int update(Subject subject) throws DataException {
		String SQL = "UPDATE subjects SET zhengshidaili=?, nianduan=?, name=?, seatpattern=? WHERE id=?";
		int result = -1;
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(SQL);
			pstmt.setString(1, subject.getZhengshidaili().toString());
			pstmt.setString(2, subject.getNianduan().toString());
			pstmt.setString(3, subject.getName());
			pstmt.setString(4, subject.getSeatpattern());
			pstmt.setInt(5, subject.getId());
			result = executeUpdate(pstmt);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int subjectid) {
		String sql = "DELETE FROM subjects WHERE id=" + subjectid;
		return this.execute(sql);
	}

	public ArrayList<Subject> getSubjectsByExamid(int examid) {
		String sql = "SELECT * FROM subjects WHERE examid=" + examid;
		return this.executeQuery(sql, Subject.class);
	}

	public Subject getSubjectById(int id) {
		if (ApplicationScope.getCacheSubjects().containsKey(id)) {
			return ApplicationScope.getCacheSubjects().get(id);
		}

		String sql = "SELECT * FROM subjects WHERE id=" + id;
		for (Subject subject : this.executeQuery(sql, Subject.class)) {
			ApplicationScope.getCacheSubjects().put(id, subject);
			return subject;
		}
		return null;
	}

	/**
	 * 清除某個 exam 的所有 subject
	 * 
	 * @param examid
	 */
	public void deleteSubjectsByExamid(int examid) {

	}

}
