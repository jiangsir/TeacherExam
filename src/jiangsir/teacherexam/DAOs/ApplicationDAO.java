/**
 * idv.jiangsir.DAOs - UserDAO.java
 * 2008/4/29 下午 05:46:51
 * jiangsir
 */
package jiangsir.teacherexam.DAOs;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Scopes.ApplicationScope;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.Exam;
import jiangsir.teacherexam.Tables.Subject;

/**
 * @author jiangsir
 * 
 */
public class ApplicationDAO extends SuperDAO<Application> {
	ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

	public synchronized int insert(Application application) throws DataException {
		String sql = "INSERT INTO applications (`pid`,`name`,`birthday`,`gender`,"
				+ "`address`,`email`,`cellphone`,`teloffice`,`subjectid`,`school`,"
				+ "`position`, `license`, `licensedate`,`licenseid`,`bachelorschool`,`bachelormajor`,"
				+ "`bachelorbegin`,`bachelorend`,`masterschool`,`mastermajor`,`masterbegin`,`masterend`,"
				+ "`phdschool`,`phdmajor`,`phdbegin`,`phdend`,`school1`,`position1`,`school1begin`,"
				+ "`school1end`,`school2`,`position2`,`school2begin`,`school2end`,"
				+ "`school3`,`position3`,`school3begin`,`school3end`,`special1`,"
				+ "`special1date`,`special2`,`special2date`,`license1`,`license2`,"
				+ "`license3`, `honors`, `other`, `note`, `pictureid`, `result`, `step2result`, `timestamp`, `examid`, `seatid`, `bankaccount`) "
				+ "VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, "
				+ "?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?);";
		int id = 0;
		PreparedStatement pstmt;
		try {
			pstmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, application.getPid());
			pstmt.setString(2, application.getName());
			pstmt.setDate(3, application.getBirthday());
			pstmt.setString(4, application.getGender());
			pstmt.setString(5, application.getAddress());
			pstmt.setString(6, application.getEmail());
			pstmt.setString(7, application.getCellphone());
			pstmt.setString(8, application.getTeloffice());
			pstmt.setInt(9, application.getSubjectid());
			pstmt.setString(10, application.getSchool());
			pstmt.setString(11, application.getPosition());
			pstmt.setString(12, application.getLicense());
			pstmt.setString(13, application.getLicensedate());
			pstmt.setString(14, application.getLicenseid());
			pstmt.setString(15, application.getBachelorschool());
			pstmt.setString(16, application.getBachelormajor());
			pstmt.setString(17, application.getBachelorbegin());
			pstmt.setString(18, application.getBachelorend());
			pstmt.setString(19, application.getMasterschool());
			pstmt.setString(20, application.getMastermajor());
			pstmt.setString(21, application.getMasterbegin());
			pstmt.setString(22, application.getMasterend());
			pstmt.setString(23, application.getPhdschool());
			pstmt.setString(24, application.getPhdmajor());
			pstmt.setString(25, application.getPhdbegin());
			pstmt.setString(26, application.getPhdend());

			pstmt.setString(27, application.getSchool1());
			pstmt.setString(28, application.getPosition1());
			pstmt.setString(29, application.getSchool1begin());
			pstmt.setString(30, application.getSchool1end());
			pstmt.setString(31, application.getSchool2());
			pstmt.setString(32, application.getPosition2());
			pstmt.setString(33, application.getSchool2begin());
			pstmt.setString(34, application.getSchool2end());
			pstmt.setString(35, application.getSchool3());
			pstmt.setString(36, application.getPosition3());
			pstmt.setString(37, application.getSchool3begin());
			pstmt.setString(38, application.getSchool3end());

			pstmt.setString(39, application.getSpecial1());
			pstmt.setString(40, application.getSpecial1date());
			pstmt.setString(41, application.getSpecial2());
			pstmt.setString(42, application.getSpecial2date());
			pstmt.setString(43, application.getLicense1());
			pstmt.setString(44, application.getLicense2());
			pstmt.setString(45, application.getLicense3());
			try {
				pstmt.setString(46, mapper.writeValueAsString(application.getHonors()));
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			pstmt.setString(47, application.getOther());
			pstmt.setString(48, application.getNote());
			pstmt.setInt(49, application.getPictureid());
			pstmt.setString(50, application.getResult());
			pstmt.setString(51, application.getStep2result());
			// pstmt.setBinaryStream(43, application.getPicture(), application
			// .getPicture().available());
			// pstmt.setString(44, application.getFiletype());
			// pstmt.setString(43, "");
			pstmt.setTimestamp(52, new Timestamp(application.getTimestamp().getTime()));
			pstmt.setInt(53, application.getExamid());
			pstmt.setString(54, application.getSeatid());
			pstmt.setString(55, application.getBankaccount());

			executeUpdate(pstmt);
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			// application.getPicture().close();
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getLocalizedMessage().startsWith("Duplicate entry")) {
				throw new DataException("您(" + application.getPid() + ") 已經報名本次甄選(" + application.getExam().getTitle()
						+ ")，請進入<a href=\"Personal.html?examid=" + application.getExamid() + "\">個人頁面</a>進行修改。謝謝。");
			} else {
				throw new DataException(e.getLocalizedMessage());
			}
		}

		// 立刻產生繳款帳號，insert 仍要把""的bankaccnount 加進去，否則資料庫會錯誤，沒有預設資料。
		application.setId(id);
		application.setBankaccount(this.makeBankaccount(id));
		this.update(application);
		return id;
	}

	/**
	 * 取得某一次 exam 的所有 application
	 * 
	 * @return
	 */
	public ArrayList<Application> getApplicationsByExamid(int examid) {
		String sql = "SELECT * FROM applications WHERE examid=" + examid + " ORDER BY id ASC";
		return this.executeQuery(sql, Application.class);
	}

	/**
	 * 取得所有已繳款的 application
	 * 
	 * @return
	 */
	public ArrayList<Application> getPaidApplicationsByExamid(int examid) {
		String sql = "SELECT * FROM applications WHERE ispaid=true AND examid=" + examid + " ORDER BY id ASC";
		return this.executeQuery(sql, Application.class);
	}

	/**
	 * 取得所有已繳款的同一科目 application
	 * 
	 * @return
	 */
	public ArrayList<Application> getPaidApplicationsByExamidSubjectid(int examid, int subjectid) {
		String sql = "SELECT * FROM applications WHERE ispaid=true AND examid=" + examid + " AND subjectid=" + subjectid
				+ " ORDER BY id ASC";
		return this.executeQuery(sql, Application.class);
	}

	/**
	 * 取得給定 exam 給定 科目的所有 applications
	 * 
	 * @return
	 */
	public ArrayList<Application> getApplicationsByExamidSubjectid(int examid, int subjectid) {
		String sql = "SELECT * FROM applications WHERE examid=" + examid + " AND subjectid=" + subjectid
				+ " ORDER BY id ASC";
		return this.executeQuery(sql, Application.class);
	}

	/**
	 * 取得 准考證編號
	 * 
	 * @param examid
	 * @param applicationid
	 * @param subject
	 * @return
	 */
	public int getSeatnumber(int examid, int applicationid, Subject subject) {
		String sql = "SELECT * FROM applications WHERE ispaid=true AND examid=" + examid + " AND subjectid="
				+ subject.getId() + " AND id=" + applicationid;
		if (this.executeQuery(sql, Application.class).size() == 0) {
			return 0;
		}
		String sql2 = "SELECT * FROM applications WHERE ispaid=true " + "AND examid=" + examid + " AND subjectid="
				+ subject.getId() + " AND id<=" + applicationid;
		return this.executeQuery(sql2, Application.class).size();
	}

	public synchronized int update(Application application) throws DataException {
		String SQL = "UPDATE applications SET name=?, bankaccount=?, birthday=?, gender=?, "
				+ "address=?, email=?, cellphone=?, teloffice=?, subjectid=?, "
				+ "school=?, position=?, `license`=?, `licensedate`=?,`licenseid`=?,`bachelorschool`=?,`bachelormajor`=?,"
				+ "`bachelorbegin`=?,`bachelorend`=?,`masterschool`=?,`mastermajor`=?,`masterbegin`=?,`masterend`=?,"
				+ "`phdschool`=?,`phdmajor`=?,`phdbegin`=?,`phdend`=?,`school1`=?,`position1`=?,`school1begin`=?,"
				+ "`school1end`=?,`school2`=?,`position2`=?,`school2begin`=?,`school2end`=?,"
				+ "`school3`=?,`position3`=?,`school3begin`=?,`school3end`=?,`special1`=?,"
				+ "`special1date`=?,`special2`=?,`special2date`=?,`license1`=?,`license2`=?,"
				+ "`license3`=?, `honors`=?, `other`=?, `note`=?, `score_teach`=?, `score_subject`=?, `tscore_teach`=?, "
				+ "`tscore_subject`=?, `percent_teach`=?, `percent_subject`=?, `totalscore`=?, `result`=?, `step2totalscore`=?, `step2result`=?, "
				+ "timestamp=?, seatid=?, ispaid=?, pictureid=? WHERE id=?";
		int result = -1;
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(SQL);
			pstmt.setString(1, application.getName());
			// application
			// .setBankaccount(this.makeBankaccount(application.getId()));
			pstmt.setString(2, application.getBankaccount());
			pstmt.setDate(3, application.getBirthday());
			pstmt.setString(4, application.getGender());
			pstmt.setString(5, application.getAddress());
			pstmt.setString(6, application.getEmail());
			pstmt.setString(7, application.getCellphone());
			pstmt.setString(8, application.getTeloffice());
			pstmt.setInt(9, application.getSubjectid());
			pstmt.setString(10, application.getSchool());
			pstmt.setString(11, application.getPosition());
			pstmt.setString(12, application.getLicense());
			pstmt.setString(13, application.getLicensedate());
			pstmt.setString(14, application.getLicenseid());
			pstmt.setString(15, application.getBachelorschool());
			pstmt.setString(16, application.getBachelormajor());
			pstmt.setString(17, application.getBachelorbegin());
			pstmt.setString(18, application.getBachelorend());
			pstmt.setString(19, application.getMasterschool());
			pstmt.setString(20, application.getMastermajor());
			pstmt.setString(21, application.getMasterbegin());
			pstmt.setString(22, application.getMasterend());
			pstmt.setString(23, application.getPhdschool());
			pstmt.setString(24, application.getPhdmajor());
			pstmt.setString(25, application.getPhdbegin());
			pstmt.setString(26, application.getPhdend());
			pstmt.setString(27, application.getSchool1());
			pstmt.setString(28, application.getPosition1());
			pstmt.setString(29, application.getSchool1begin());
			pstmt.setString(30, application.getSchool1end());
			pstmt.setString(31, application.getSchool2());
			pstmt.setString(32, application.getPosition2());
			pstmt.setString(33, application.getSchool2begin());
			pstmt.setString(34, application.getSchool2end());
			pstmt.setString(35, application.getSchool3());
			pstmt.setString(36, application.getPosition3());
			pstmt.setString(37, application.getSchool3begin());
			pstmt.setString(38, application.getSchool3end());
			pstmt.setString(39, application.getSpecial1());
			pstmt.setString(40, application.getSpecial1date());
			pstmt.setString(41, application.getSpecial2());
			pstmt.setString(42, application.getSpecial2date());
			pstmt.setString(43, application.getLicense1());
			pstmt.setString(44, application.getLicense2());
			pstmt.setString(45, application.getLicense3());
			try {
				pstmt.setString(46, mapper.writeValueAsString(application.getHonors()));
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pstmt.setString(47, application.getOther());
			pstmt.setString(48, application.getNote());
			pstmt.setDouble(49, application.getScore_teach());
			pstmt.setDouble(50, application.getScore_subject());
			pstmt.setDouble(51, application.getTscore_teach());
			pstmt.setDouble(52, application.getTscore_subject());
			pstmt.setDouble(53, application.getPercent_teach());
			pstmt.setDouble(54, application.getPercent_subject());
			pstmt.setDouble(55, application.getTotalscore());
			pstmt.setString(56, application.getResult());
			pstmt.setDouble(57, application.getStep2totalscore());
			pstmt.setString(58, application.getStep2result());
			pstmt.setTimestamp(59, new Timestamp(application.getTimestamp().getTime()));
			pstmt.setString(60, application.getSeatid());
			pstmt.setBoolean(61, application.getIspaid());
			pstmt.setInt(62, application.getPictureid());
			pstmt.setInt(63, application.getId());
			result = executeUpdate(pstmt);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException(e);
		}
		return result;

	}

	public Application getApplicationByName(String name) {
		String sql = "SELECT * FROM applications WHERE name=?";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, name);
			for (Application application : this.executeQuery(pstmt, Application.class)) {
				return application;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 從 request 取得參數.
	 * 
	 * @param applicationid
	 * @return
	 */
	public Application getApplicationById(String applicationid) {
		if (applicationid == null || !applicationid.matches("[0-9]+")) {
			return null;
		}
		return new ApplicationDAO().getApplicationById(Integer.parseInt(applicationid));
	}

	public Application getApplicationById(int applicationid) {
		String sql = "SELECT * FROM applications WHERE id=" + applicationid;
		for (Application application : this.executeQuery(sql, Application.class)) {
			return application;
		}
		return null;
	}

	/**
	 * 用 pid 要取出 applications
	 * 
	 * @param pid
	 * @return
	 */
	public Application getApplicationByPid(String pid) {
		String sql = "SELECT * FROM applications WHERE pid=? ORDER BY id DESC";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, pid);
			for (Application application : this.executeQuery(pstmt, Application.class)) {
				return application;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		// return this.getApplicationByPidExamid(pid, String.valueOf(exam.getId()));
	}

	/**
	 * 用 pid 要取出 applications
	 * 
	 * @param pid
	 * @return
	 */
	public ArrayList<Application> getApplicationsByPid(String pid) {
		String sql = "SELECT * FROM applications WHERE pid=? ORDER BY id DESC";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, pid);
			return this.executeQuery(pstmt, Application.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Application>();
		// return this.getApplicationByPidExamid(pid, String.valueOf(exam.getId()));
	}

	public Application getApplicationByPidExamid(String pid, String examidString) {
		int examid;
		if (examidString != null && examidString.matches("[0-9]+")) {
			examid = Integer.parseInt(examidString);
		} else {
			// examid = new ExamDAO().getActiveExam().getId();
			return null;
		}
		String sql = "SELECT * FROM applications WHERE pid=? AND examid=? ORDER BY id DESC";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, pid);
			pstmt.setInt(2, examid);
			for (Application application : this.executeQuery(pstmt, Application.class)) {
				return application;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Application getApplicationBySeatid(int examid, String seatid) {
		String sql = "SELECT * FROM applications WHERE examid=? AND seatid=?";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setInt(1, examid);
			pstmt.setString(2, seatid);
			for (Application application : this.executeQuery(pstmt, Application.class)) {
				return application;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String applicationid) {
		if (applicationid == null || !applicationid.matches("[0-9]+")) {
			return false;
		}
		return delete(Integer.parseInt(applicationid));
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM applications WHERE id=?";
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

	/**
	 * 清除所有 seatid 重新編排。
	 * 
	 * @param examid
	 */
	public void cleanSeatid(int examid) {
		String sql = "UPDATE applications SET seatid=? WHERE examid=?";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, new Application().getSeatid());
			pstmt.setInt(2, examid);
			executeUpdate(pstmt);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 將一筆報名資料設定為"已繳費"
	 * 
	 * @param applicationid
	 */
	public void setPaid(String bankaccount) {
		String SQL = "UPDATE applications SET ispaid=true WHERE bankaccount=?";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(SQL);
			pstmt.setString(1, bankaccount);
			executeUpdate(pstmt);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 將一筆報名資料設定為"未繳費"
	 * 
	 * @param applicationid
	 */
	public void setUnPaid(String bankaccount) {
		String SQL = "UPDATE applications SET ispaid=false WHERE bankaccount=?";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(SQL);
			pstmt.setString(1, bankaccount);
			executeUpdate(pstmt);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得所有的（包含歷史資料）報名資料。
	 * 
	 * @return
	 */
	public ArrayList<Application> getAllApplications() {
		String sql = "SELECT * FROM applications";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			return this.executeQuery(pstmt, Application.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 因為 bankaccount 是後來才加入，以前的報名表都沒有包含 bankaccount<br/>
	 * 因此要一次把以前的 bankaccount 都建立起來。
	 * 
	 */
	public void updateAllBankaccount() {
		for (Application application : this.getAllApplications()) {
			if (application.getBankaccount() == null || application.getBankaccount().equals("")) {
				application.setBankaccount(this.makeBankaccount(application.getId()));
				this.update(application);
			}
		}
	}

	/**
	 * 每次新增一筆報名單的時候，都要製造一個 bankaccount
	 * 
	 * @param applicationid
	 * @return
	 */
	private String makeBankaccount(int applicationid) {
		Exam exam = this.getApplicationById(applicationid).getExam();
		String daihao = ApplicationScope.getAppConfig().getBankprefix();
		int chk1 = 0;
		int chk2 = 0;
		String year = "x";
		String days = "xxx";
		String money = new DecimalFormat("0000000000").format(exam.getMoney());
		String serial = new DecimalFormat("0000").format(applicationid);
		Date bank_deadline = exam.getDeadline();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.util.Date(bank_deadline.getTime()));
		year = calendar.get(Calendar.YEAR) % 10 + "";
		days = new DecimalFormat("000").format(calendar.get(Calendar.DAY_OF_YEAR));
		String chk = daihao + year + days + serial + money;
		for (int i = 0; i < chk.length(); i++) {
			int sum = (chk.charAt(i) - '0') * ("12".charAt(i % 2) - '0');
			if (sum >= 10) {
				sum = sum % 10 + sum / 10;
			}
			chk1 += sum;
		}
		for (int i = 0; i < chk.length(); i++) {
			chk2 += (chk.charAt(i) - '0') * ("137".charAt(i % 3) - '0');
		}
		return daihao + (chk1 % 10) + (chk2 % 10) + year + days + serial;

	}

}
