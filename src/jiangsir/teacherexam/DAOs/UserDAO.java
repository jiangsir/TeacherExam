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
import javax.servlet.http.HttpSession;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Factories.UserFactory;
import jiangsir.teacherexam.Tables.User;
import jiangsir.teacherexam.Tables.User.ROLE;

/**
 * @author jiangsir
 * 
 */
public class UserDAO extends SuperDAO<User> {

	public synchronized int insert(User user) throws DataException {
		String sql = "INSERT INTO users (`pid`, `examid`, `passwd`, `group`, `role`) VALUES(?,?,md5(?),?,?);";
		int id = 0;
		PreparedStatement pstmt;
		try {
			pstmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getPid());
			pstmt.setInt(2, user.getExamid());
			pstmt.setString(3, user.getPasswd());
			pstmt.setString(4, user.getGroup().name());
			pstmt.setString(5, user.getRole().toString());

			executeUpdate(pstmt);
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getLocalizedMessage().startsWith("Duplicate entry")) {
				throw new DataException("您的個人資料已經存在，請使用修改的方式，而非再次新增。謝謝。");
			} else {
				throw new DataException(e.getLocalizedMessage());
			}
		}
		user.setId(id);
		return id;
	}

	public void insertDefaultUsers() {
		User user = new User();
		try {
			user.setPid("admin");
			user.setGroup(User.GROUP.GroupAdmin);
			user.setRole(ROLE.ADMIN);
			user.setPasswd("!@34TeacherExam");
			new UserDAO().insert(user);
		} catch (DataException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 取得所有 user
	 * 
	 * @return
	 */
	public ArrayList<User> getAllUsers() {
		String sql = "SELECT * FROM users";
		return this.executeQuery(sql, User.class);
	}

	public synchronized int update(User user) {
		if (user.getIsNullUser()) {
			return 0;
		}
		String SQL = "UPDATE users SET pid=?, passwd=md5(?), role=? WHERE id=?";
		int result = -1;
		PreparedStatement pstmt;
		try {
			pstmt = getConnection().prepareStatement(SQL);
			pstmt.setString(1, user.getPid());
			pstmt.setString(2, user.getPasswd());
			pstmt.setString(3, user.getRole().toString());
			pstmt.setInt(4, user.getId());
			result = executeUpdate(pstmt);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 取得所有 ROLE = "ADMIN" 的 User
	 * 
	 * @return
	 */
	public ArrayList<User> getUsersByRoleAdmin() {
		String sql = "SELECT * FROM users WHERE role='" + ROLE.ADMIN + "' AND examid=0 ORDER BY id DESC";
		return this.executeQuery(sql, User.class);
	}

	public User getUserByPid(String pid, int examid) throws DataException {
		String sql = "SELECT * FROM users WHERE pid=? AND examid=?";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, pid);
			pstmt.setInt(2, examid);
			for (User user : executeQuery(pstmt, User.class)) {
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return UserFactory.getNullUser();
	}

	// public User doLogin(String pid, String passwd) throws DataException {
	// // if (User.getIsAdmin(pid)) {
	// // return this.getAdmin();
	// // }
	//
	// Exam activeExam = new ExamDAO().getActiveExam();
	// if (!User.getIsAdmin(pid) && activeExam == null) {
	// throw new DataException("沒有任何啓動的競賽！無法登入。");
	// }
	// if (User.getIsAdmin(pid)) {
	// activeExam = new Exam();
	// }
	// String sql =
	// "SELECT * FROM users WHERE pid=? AND `passwd`=? AND examid=? ORDER BY id
	// DESC";
	// try {
	// PreparedStatement pstmt = getConnection().prepareStatement(sql);
	// pstmt.setString(1, pid);
	// pstmt.setString(2, passwd);
	// pstmt.setInt(3, activeExam.getId());
	// for (User user : executeQuery(pstmt, User.class)) {
	// return user;
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// throw new DataException(e.getLocalizedMessage());
	// }
	// throw new DataException("登入失敗！請重新再試。");
	// }

	public User getUserById(String id) {
		if (id == null || !id.matches("[0-9]+")) {
			return UserFactory.getNullUser();
		}
		return getUserById(Integer.valueOf(id));
	}

	public User getUserById(int id) {
		String sql = "SELECT * FROM users WHERE id=" + id;
		for (User user : this.executeQuery(sql, User.class)) {
			return user;
		}
		return UserFactory.getNullUser();
	}

	/**
	 * 用 account, passwd 取得 User, 找不到的話，則回傳 NullUser
	 * 
	 * @param account
	 * @param passwd
	 * @return
	 */
	public User getUserByPidPasswd(String pid, String passwd) {
		String sql = "SELECT * FROM users WHERE pid=? AND passwd=md5(?) ORDER BY id DESC;";
		try {
			PreparedStatement pstmt = this.getConnection().prepareStatement(sql);
			pstmt.setString(1, pid);
			pstmt.setString(2, passwd);
			for (User user : this.executeQuery(pstmt, User.class)) {
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return UserFactory.getNullUser();
	}

	/**
	 * 登出相關動作, 包含 session 逾時也執行 doLogout <br>
	 */
	public void doLogout(HttpSession session) {
		session.invalidate();
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM users WHERE id=?";
		PreparedStatement pstmt;
		boolean result = false;
		try {
			pstmt = getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean delete(String pid, int examid) {
		String sql = "DELETE FROM users WHERE pid=? AND examid=?";
		PreparedStatement pstmt;
		boolean result = false;
		try {
			pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, pid);
			pstmt.setInt(2, examid);
			result = pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
