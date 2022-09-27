package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import jiangsir.teacherexam.Annotations.RoleSetting;
import jiangsir.teacherexam.DAOs.ApplicationDAO;
import jiangsir.teacherexam.DAOs.ExamDAO;
import jiangsir.teacherexam.DAOs.UpfileDAO;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.Exam;
import jiangsir.teacherexam.Tables.Upfile;

/**
 * Servlet implementation class Download
 */
@WebServlet(urlPatterns = { "/ExportCSV.api" })
@RoleSetting
public class ExportCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static enum TARGET {
		exam, // 匯出整個測驗的報部資料
		PaidXLS, // 列出已繳費的名冊
		PaidCSV; // 列出已繳費的名冊
	};

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TARGET target = TARGET.valueOf(request.getParameter("target"));
		switch (target) {
		case PaidXLS:
			Integer examid = Integer.parseInt(request.getParameter("examid"));
			// this.exportExamXLS(examid, true, response);
			this.exportPaidXLS(examid, response);
			break;
		case PaidCSV:
			examid = Integer.parseInt(request.getParameter("examid"));
			this.exportPaidCSV(examid, response);
			break;
		case exam:
			examid = Integer.parseInt(request.getParameter("examid"));
			this.exportExamXLS(examid, false, response);
			break;
		default:
			break;

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * 下載檔案
	 * 
	 * @param application
	 */
	private void downloadUpfile(int upfileid, HttpServletResponse response) {
		Upfile upfile = new UpfileDAO().getFileById(upfileid);
		ServletOutputStream out = null;
		// FileInputStream stream = null;
		try {
			out = response.getOutputStream();
			String filename = new String((upfile.getFilename()).getBytes("Big5"), "ISO8859_1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			// response.setHeader("Content-Disposition", "inline; filename=\""
			// + filename + "\"");
			// File file = new PDFCreater().createPaymentForm(application);
			// stream = new FileInputStream(upfile.getBytes());
			response.setHeader("Content-Length", String.valueOf(upfile.getFileblob().available()));

			// int bytesRead = 0;
			// byte[] buffer = new byte[8192];
			// while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) { //
			// writeatserverside
			// out.write(buffer, 0, bytesRead);
			// }
			out.write(upfile.getBytes());
			// stream.close();
			// response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 匯出 已繳費名冊 CSV
	 * 
	 * @param examid
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	private void exportPaidCSV(Integer examid, HttpServletResponse response) throws UnsupportedEncodingException {
		Exam exam = new ExamDAO().getExamById(examid);
		String fileName = exam.getId() + "_" + exam.getTitle() + "(已繳費名冊).csv";
		fileName = new String(fileName.getBytes("UTF-8"), "ISO8859_1");
		StringBuffer content = new StringBuffer(1000000);
		content.append("科目,姓名,身分證字號,准考證號碼,email,行動電話,虛擬帳號\n");
		for (Application application : new ApplicationDAO().getPaidApplicationsByExamid(examid)) {
			content.append(application.getSubject());
			content.append("," + application.getName());
			content.append("," + application.getPid());
			content.append("," + application.getSeatid());
			content.append("," + application.getEmail());
			content.append(",phone: " + application.getCellphone());
			content.append("," + application.getBankaccount());
			content.append("\n");
		}

		response.reset();
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			// writer.write(new String(content.toString().getBytes("UTF-8"),
			// "UTF-8"));
			// 加入 UTF8 BOM
			writer.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }));
			writer.write(content.toString());
			writer.flush();
			writer.close();
			response.setStatus(HttpServletResponse.SC_OK);
			response.flushBuffer();
		} catch (Exception e) {
		}
	}

	/**
	 * 匯出已繳費的 XLS
	 * 
	 * @param examid
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	private void exportPaidXLS(int examid, HttpServletResponse response) throws UnsupportedEncodingException {
		response.reset(); // Reset the response
		// response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setContentType("application/octet-stream;charset=UTF-8");

		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			Exam exam = new ExamDAO().getExamById(examid);
			String fileName = exam.getTitle() + "(已繳費名冊)" + ".xls";
			// fileName = new String(fileName.getBytes("UTF-8"), "ISO8859_1");
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859_1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			// response.setHeader("Content-Length", String.valueOf());
			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook();// 建立一個Excel活頁簿
			HSSFSheet sheet = workbook.createSheet(exam.getId() + ""); // 在活頁簿中建立一個Sheet

			HSSFRow row = sheet.createRow(0);
			// "甄選科別,姓名,身分證字號,生日,性別,地址,email,電話,服務學校,職務,教師證書,證書日期,證書字號,學歷(大學),學歷(大學科系),大學起,大學訖,"
			int col = 0;
			row.createCell(col++).setCellValue("S/N");
			row.createCell(col++).setCellValue("甄選科別");
			row.createCell(col++).setCellValue("姓名");
			row.createCell(col++).setCellValue("身分證字號");
			row.createCell(col++).setCellValue("准考證號碼");
			row.createCell(col++).setCellValue("email");
			row.createCell(col++).setCellValue("行動電話");
			row.createCell(col++).setCellValue("虛擬帳號");
			ArrayList<Application> paidapplications = new ApplicationDAO().getPaidApplicationsByExamid(examid);
			// for (Application paidapplication : new
			// ApplicationDAO().getPaidApplicationsByExamid(examid)) {
			for (int r = 0; r < paidapplications.size(); r++) {
				row = sheet.createRow(r + 1);
				// for (int c = 0; c < 2; c++) {
				Application application = paidapplications.get(r);
				// 取得活頁簿中的第3列第3欄(cell)
				int c = 0;
				row.createCell(c++).setCellValue(application.getId());
				row.createCell(c++).setCellValue(application.getSubject().toString());
				row.createCell(c++).setCellValue(application.getName());
				row.createCell(c++).setCellValue(application.getPid());
				row.createCell(c++).setCellValue(application.getSeatid());
				row.createCell(c++).setCellValue(application.getEmail());
				row.createCell(c++).setCellValue(application.getCellphone());
				row.createCell(c++).setCellValue(application.getBankaccount());
			}
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void exportExamXLS(int examid, Boolean isPaid, HttpServletResponse response)
			throws UnsupportedEncodingException {

		response.reset(); // Reset the response
		// response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setContentType("application/octet-stream;charset=UTF-8");

		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			String fileName = new ExamDAO().getExamById(examid).getTitle() + (isPaid ? "－已繳費" : "") + ".xls";
			// fileName = new String(fileName.getBytes("UTF-8"), "ISO8859_1");
			fileName = new String(fileName.getBytes("Big5"), "ISO8859_1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			// response.setHeader("Content-Length", String.valueOf());
			new ExamDAO().exportApplications_XLS(examid, isPaid, out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void exportExamCSV(Integer examid, HttpServletResponse response) throws UnsupportedEncodingException {

		String fileName = new ExamDAO().getExamById(examid).getTitle() + ".csv";
		fileName = new String(fileName.getBytes("Big5"), "ISO8859_1");
		StringBuffer content = new StringBuffer(100000);

		content.append("甄選科別,姓名,身分證字號,生日,性別,地址,email,電話,服務學校,職務,教師證書,證書日期,證書字號,學歷(大學),學歷(大學科系),大學起,大學訖,"
				+ "學歷(研究所),學歷(研究所科系),研究所起,研究所訖,學歷(博士),學歷(博士科系),博士起,博士訖,經歷1(單位),經歷1(職務),經歷1(起),經歷1(訖),"
				+ "特殊教育1,特酥教育1(時間),特殊教育2,特酥教育2(時間),相關證照1,相關證照2,相關證照3,補充說明,附註\n");
		Exam exam = new ExamDAO().getExamById(examid);
		for (Application application : exam.getApplications()) {
			content.append("\"" + application.getSubject().getZhengshidaili().toString()
					+ application.getSubject().getNianduan() + application.getSubject().getName());
			content.append(application.getName() + "\",\"");
			content.append(application.getPid() + "\",\"");
			content.append(application.getBirthday() + "\",\"");
			content.append(application.getGender() + "\",\"");
			content.append(application.getAddress() + "\",\"");
			content.append(application.getEmail() + "\",\"");
			content.append(application.getCellphone() + "\",\"");
			content.append(application.getSchool() + "\",\"");
			content.append(application.getPosition() + "\",\"");
			content.append(application.getLicense() + "\",\"");
			content.append(application.getLicensedate() + "\",\"");
			content.append(application.getLicenseid() + "\",\"");
			content.append(application.getBachelorschool() + "\",\"");
			content.append(application.getBachelormajor() + "\",\"");
			content.append(application.getBachelorbegin() + "\",\"");
			content.append(application.getBachelorend() + "\",\"");
			content.append(application.getMasterschool() + "\",\"");
			content.append(application.getMastermajor() + "\",\"");
			content.append(application.getMasterbegin() + "\",\"");
			content.append(application.getMasterend() + "\",\"");
			content.append(application.getPhdschool() + "\",\"");
			content.append(application.getPhdmajor() + "\",\"");
			content.append(application.getPhdbegin() + "\",\"");
			content.append(application.getPhdend() + "\",\"");
			content.append(application.getSchool1() + "\",\"");
			content.append(application.getPosition1() + "\",\"");
			content.append(application.getSchool1begin() + "\",\"");
			content.append(application.getSchool1end() + "\",\"");
			content.append(application.getSchool2() + "\",\"");
			content.append(application.getPosition2() + "\",\"");
			content.append(application.getSchool2begin() + "\",\"");
			content.append(application.getSchool2end() + "\",\"");
			content.append(application.getSchool3() + "\",\"");
			content.append(application.getPosition3() + "\",\"");
			content.append(application.getSchool3begin() + "\",\"");
			content.append(application.getSchool3end() + "\",\"");
			content.append(application.getSpecial1() + "\",\"");
			content.append(application.getSpecial1date() + "\",\"");
			content.append(application.getSpecial2() + "\",\"");
			content.append(application.getSpecial2date() + "\",\"");
			content.append(application.getLicense1() + "\",\"");
			content.append(application.getLicense2() + "\",\"");
			content.append(application.getLicense3() + "\",\"");
			content.append(application.getOther() + "\",\"");
			content.append(application.getNote() + "\"");
			content.append("\n");
		}

		// ***** Output strOut to Response ******
		response.reset(); // Reset the response
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		// response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			// writer.write(new String(content.toString().getBytes("UTF-8"),
			// "UTF-8"));
			writer.write(content.toString());
			writer.flush();
			writer.close();
			response.setStatus(HttpServletResponse.SC_OK);
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
