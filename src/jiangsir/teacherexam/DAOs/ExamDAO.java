package jiangsir.teacherexam.DAOs;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.codehaus.jackson.map.ObjectMapper;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.Exam;

public class ExamDAO extends SuperDAO<Exam> {
	ObjectMapper mapper = new ObjectMapper();

	public Exam getExamById(String examid) {
		if (examid == null || !examid.matches("[0-9]+")) {
			return null;
		}
		return this.getExamById(Integer.parseInt(examid));
	}

	public Exam getExamById(int examid) {
		String sql = "SELECT * FROM exams WHERE id=" + examid;
		for (Exam exam : executeQuery(sql, Exam.class)) {
			return exam;
		}
		return new Exam();
	}

	/**
	 * 取得的第一個 active Exam, 如果沒有的話，回傳 null
	 * 
	 * @return
	 */
	private Exam getActiveExam_NOUSE() throws DataException {
		String sql = "SELECT * FROM exams WHERE isactive=true ORDER BY id DESC";
		for (Exam exam : this.executeQuery(sql, Exam.class)) {
			return exam;
		}
		return null;
	}

	/**
	 * 取得 active Exams<br>
	 * ORDER BY id DESC
	 * 
	 * @return
	 */
	public ArrayList<Exam> getActiveExams() throws DataException {
		String sql = "SELECT * FROM exams WHERE isactive=true ORDER BY id DESC";
		return this.executeQuery(sql, Exam.class);
	}

	/**
	 * 依據登入身分找到加入的最新的 active Exam
	 * 
	 * @return
	 */
	public Exam getLatestActiveExamByPid(String pid) {
		for (Application application : new ApplicationDAO().getApplicationsByPid(pid)) {
			if (application.getExam().isIsactive()) {
				return application.getExam();
			}
		}
		return null;
//		String sql = "SELECT * FROM exams WHERE pid=? AND isactive=true ORDER BY id DESC";
//		try {
//			PreparedStatement pstmt = getConnection().prepareStatement(sql);
//			pstmt.setString(1, pid);
//			for (Exam exam : this.executeQuery(pstmt, Exam.class)) {
//				return exam;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	/**
	 * 取得所有甄試列表
	 * 
	 * @return
	 */
	public ArrayList<Exam> getAllExams() {
		String sql = "SELECT * FROM exams ORDER BY id DESC";
		return this.executeQuery(sql, Exam.class);
	}

	@Override
	public synchronized int insert(Exam exam) throws DataException {
		String sql = "INSERT INTO exams (`title`, `applybegin`, "
				+ "`applyend`, `startline`, `deadline`, firsttest, secondtest, "
				+ "`scoreboardbegin`, `step2scoreboardbegin`, money, examroom,"
				+ "uploadform, note, formnote, isexamformprintable, isapplicationalwayseditable, "
				+ "isactive) VALUES (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?);";
		int id = 0;
		PreparedStatement pstmt;
		try {
			pstmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, exam.getTitle());
			pstmt.setTimestamp(2, exam.getApplybegin());
			pstmt.setTimestamp(3, exam.getApplyend());
			pstmt.setTimestamp(4, exam.getStartline());
			pstmt.setDate(5, exam.getDeadline());
			pstmt.setTimestamp(6, exam.getFirsttest());
			pstmt.setTimestamp(7, exam.getSecondtest());
			pstmt.setTimestamp(8, exam.getScoreboardbegin());
			pstmt.setTimestamp(9, exam.getStep2scoreboardbegin());
			pstmt.setInt(10, exam.getMoney());
			pstmt.setString(11, exam.getExamroom());
			pstmt.setString(12, exam.getUploadform());
			pstmt.setString(13, exam.getNote());
			pstmt.setString(14, exam.getFormnote());
			pstmt.setBoolean(15, exam.getIsexamformprintable());
			pstmt.setBoolean(16, exam.getIsApplicationAlwaysEditable());
			pstmt.setBoolean(17, exam.getIsactive());
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
	public synchronized int update(Exam exam) throws DataException {
		String SQL = "UPDATE exams SET title=?, applybegin=?, applyend=?, "
				+ "startline=?, deadline=?, firsttest=?, secondtest=?, "
				+ "scoreboardbegin=?, step2scoreboardbegin=?, money=?,  "
				+ "examroom=?, uploadform=?, note=?, formnote=?, isexamformprintable=?, "
				+ "isactive=?, isapplicationalwayseditable=? WHERE id=?";
		int result = -1;
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(SQL);
			pstmt.setString(1, exam.getTitle());
			pstmt.setTimestamp(2, exam.getApplybegin());
			pstmt.setTimestamp(3, exam.getApplyend());
			pstmt.setTimestamp(4, exam.getStartline());
			pstmt.setDate(5, exam.getDeadline());
			pstmt.setTimestamp(6, exam.getFirsttest());
			pstmt.setTimestamp(7, exam.getSecondtest());
			pstmt.setTimestamp(8, exam.getScoreboardbegin());
			pstmt.setTimestamp(9, exam.getStep2scoreboardbegin());
			pstmt.setInt(10, exam.getMoney());
			pstmt.setString(11, exam.getExamroom());
			pstmt.setString(12, exam.getUploadform());
			pstmt.setString(13, exam.getNote());
			pstmt.setString(14, exam.getFormnote());
			pstmt.setBoolean(15, exam.getIsexamformprintable());
			pstmt.setBoolean(16, exam.getIsactive());
			pstmt.setBoolean(17, exam.getIsApplicationAlwaysEditable());
			pstmt.setInt(18, exam.getId());
			result = executeUpdate(pstmt);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public synchronized boolean delete(int i) {
		return false;
	}

	public void disactiveExam(int examid) {
		String sql = "UPDATE exams SET isactive=false WHERE id=" + examid;
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			executeUpdate(pstmt);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 啓動一個甄試，並結束其它的
	 * 
	 * @param examid
	 */
	public void setActiveExam(int examid) {
		// 取消全部改成 false 允許多個 active
//		String sql = "UPDATE exams SET isactive=false";
//		try {
//			PreparedStatement pstmt = getConnection().prepareStatement(sql);
//			executeUpdate(pstmt);
//			pstmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		Exam exam = getExamById(examid);
		exam.setIsactive(true);
		try {
			update(exam);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 匯出 exam 的 applications 到 xls 檔
	 * 
	 * @return
	 * @throws IOException
	 */
	public void exportApplications_XLS(int examid, Boolean isPaid, OutputStream out) throws IOException {
		Exam exam = this.getExamById(examid);
		ArrayList<Application> applications;
		if (isPaid) {
			applications = new ApplicationDAO().getPaidApplicationsByExamid(examid);
		} else {
			applications = exam.getApplications();
		}
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook();// 建立一個Excel活頁簿
		HSSFSheet sheet = workbook.createSheet(exam.getId() + ""); // 在活頁簿中建立一個Sheet

		HSSFRow row = sheet.createRow(0);
		// "甄選科別,姓名,身分證字號,生日,性別,地址,email,電話,服務學校,職務,教師證書,證書日期,證書字號,學歷(大學),學歷(大學科系),大學起,大學訖,"
		int col = 0;
		row.createCell(col++).setCellValue("甄選科別");
		row.createCell(col++).setCellValue("姓名");
		row.createCell(col++).setCellValue("身分證字號");
		row.createCell(col++).setCellValue("生日");
		row.createCell(col++).setCellValue("性別");
		row.createCell(col++).setCellValue("地址");
		row.createCell(col++).setCellValue("email");
		row.createCell(col++).setCellValue("電話");
		row.createCell(col++).setCellValue("服務學校");
		row.createCell(col++).setCellValue("職務");
		row.createCell(col++).setCellValue("教師證書");
		row.createCell(col++).setCellValue("證書日期");
		row.createCell(col++).setCellValue("證書字號");
		row.createCell(col++).setCellValue("學歷(大學)");
		row.createCell(col++).setCellValue("學歷(大學科系)");
		row.createCell(col++).setCellValue("大學起");
		row.createCell(col++).setCellValue("大學訖");
		// "學歷(研究所),學歷(研究所科系),研究所起,研究所訖,學歷(博士),學歷(博士科系),博士起,博士訖,經歷1(單位),經歷1(職務),經歷1(起),經歷1(訖),"
		row.createCell(col++).setCellValue("學歷(研究所)");
		row.createCell(col++).setCellValue("學歷(研究所科系)");
		row.createCell(col++).setCellValue("研究所起");
		row.createCell(col++).setCellValue("研究所訖");
		row.createCell(col++).setCellValue("學歷(博士)");
		row.createCell(col++).setCellValue("學歷(博士科系)");
		row.createCell(col++).setCellValue("博士起");
		row.createCell(col++).setCellValue("博士訖");
		row.createCell(col++).setCellValue("經歷1(單位)");
		row.createCell(col++).setCellValue("經歷1(職務)");
		row.createCell(col++).setCellValue("經歷1(起)");
		row.createCell(col++).setCellValue("經歷1(訖)");
		row.createCell(col++).setCellValue("經歷2(單位)");
		row.createCell(col++).setCellValue("經歷2(職務)");
		row.createCell(col++).setCellValue("經歷2(起)");
		row.createCell(col++).setCellValue("經歷2(訖)");
		row.createCell(col++).setCellValue("經歷3(單位)");
		row.createCell(col++).setCellValue("經歷3(職務)");
		row.createCell(col++).setCellValue("經歷3(起)");
		row.createCell(col++).setCellValue("經歷3(訖)");
		// "特殊教育1,特酥教育1(時間),特殊教育2,特酥教育2(時間),相關證照1,相關證照2,相關證照3,補充說明,附註\n"
		row.createCell(col++).setCellValue("特殊教育1");
		row.createCell(col++).setCellValue("特酥教育1(時間)");
		row.createCell(col++).setCellValue("特殊教育2");
		row.createCell(col++).setCellValue("特酥教育2(時間)");
		row.createCell(col++).setCellValue("相關證照1");
		row.createCell(col++).setCellValue("相關證照2");
		row.createCell(col++).setCellValue("相關證照3");
		row.createCell(col++).setCellValue("補充說明");
		row.createCell(col++).setCellValue("附註");

		for (int r = 0; r < applications.size(); r++) {
			row = sheet.createRow(r + 1);
			// for (int c = 0; c < 2; c++) {
			Application application = applications.get(r);
			// 取得活頁簿中的第3列第3欄(cell)
			int c = 0;
			row.createCell(c++).setCellValue(application.getSubject().getZhengshidaili().toString()
					+ application.getSubject().getNianduan() + application.getSubject().getName());
			row.createCell(c++).setCellValue(application.getName());
			row.createCell(c++).setCellValue(application.getPid());
			row.createCell(c++).setCellValue(application.getBirthday().toString());
			row.createCell(c++).setCellValue(application.getGender());
			row.createCell(c++).setCellValue(application.getAddress());
			row.createCell(c++).setCellValue(application.getEmail());
			row.createCell(c++).setCellValue(application.getCellphone());
			row.createCell(c++).setCellValue(application.getSchool());
			row.createCell(c++).setCellValue(application.getPosition());
			row.createCell(c++).setCellValue(application.getLicense());
			row.createCell(c++).setCellValue(application.getLicensedate());
			row.createCell(c++).setCellValue(application.getLicenseid());
			row.createCell(c++).setCellValue(application.getBachelorschool());
			row.createCell(c++).setCellValue(application.getBachelormajor());
			row.createCell(c++).setCellValue(application.getBachelorbegin());
			row.createCell(c++).setCellValue(application.getBachelorend());
			row.createCell(c++).setCellValue(application.getMasterschool());
			row.createCell(c++).setCellValue(application.getMastermajor());
			row.createCell(c++).setCellValue(application.getMasterbegin());
			row.createCell(c++).setCellValue(application.getMasterend());
			row.createCell(c++).setCellValue(application.getPhdschool());
			row.createCell(c++).setCellValue(application.getPhdmajor());
			row.createCell(c++).setCellValue(application.getPhdbegin());
			row.createCell(c++).setCellValue(application.getPhdend());
			row.createCell(c++).setCellValue(application.getSchool1());
			row.createCell(c++).setCellValue(application.getPosition1());
			row.createCell(c++).setCellValue(application.getSchool1begin());
			row.createCell(c++).setCellValue(application.getSchool1end());
			row.createCell(c++).setCellValue(application.getSchool2());
			row.createCell(c++).setCellValue(application.getPosition2());
			row.createCell(c++).setCellValue(application.getSchool2begin());
			row.createCell(c++).setCellValue(application.getSchool2end());
			row.createCell(c++).setCellValue(application.getSchool3());
			row.createCell(c++).setCellValue(application.getPosition3());
			row.createCell(c++).setCellValue(application.getSchool3begin());
			row.createCell(c++).setCellValue(application.getSchool3end());
			row.createCell(c++).setCellValue(application.getSpecial1());
			row.createCell(c++).setCellValue(application.getSpecial1date());
			row.createCell(c++).setCellValue(application.getSpecial2());
			row.createCell(c++).setCellValue(application.getSpecial2date());
			row.createCell(c++).setCellValue(application.getLicense1());
			row.createCell(c++).setCellValue(application.getLicense2());
			row.createCell(c++).setCellValue(application.getLicense3());
			row.createCell(c++).setCellValue(application.getOther());
			row.createCell(c++).setCellValue(application.getNote());
			// cell.setCellValue(application.getSubject().getName());

			// 設定儲存格格式
			CellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.YELLOW.index);// 填滿顏色
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平置中
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直置中
			// 設定字型
			Font font = workbook.createFont();
			font.setColor(HSSFColor.RED.index);// 顏色
			font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗體
			style.setFont(font);
			// 設定框線
			style.setBorderBottom((short) 1);
			style.setBorderTop((short) 1);
			style.setBorderLeft((short) 1);
			style.setBorderRight((short) 1);
			style.setWrapText(true);// 自動換行

			// cell.setCellStyle(style);// 將格式套用格式到指定的cell中

			// }
		}
		workbook.write(out);
	}
}
