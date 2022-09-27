package jiangsir.teacherexam.Servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.itextpdf.text.DocumentException;

import jiangsir.teacherexam.DAOs.ApplicationDAO;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Factories.UserFactory;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.CurrentUser;
import jiangsir.teacherexam.Tables.User;
import jiangsir.teacherexam.Tools.PDFCreater;

@WebServlet(urlPatterns = { "/DownloadForm" })
public class DownloadFormServlet extends HttpServlet implements IAccessFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletResponse response;

	public void AccessFilter(HttpServletRequest request) throws AccessException {
		CurrentUser currentUser = UserFactory.getCurrentUser(request);
		if (request.getParameter("applicationid") != null
				&& currentUser.getRole() != User.ROLE.ADMIN) {
			throw new AccessException("您無此權限！");
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.response = response;
		this.response.setHeader("Pragma", "No-cache");
		this.response.setHeader("Cache-Control", "no-cache");
		this.response.setDateHeader("Expires", 0);
		this.response.setContentType("application/pdf");

		int formid = Integer.parseInt(request.getParameter("formid"));
		HttpSession session = request.getSession(false);
		String applicationid = request.getParameter("applicationid");
		Application application;
		if (applicationid == null) {
			String session_pid = (String) session.getAttribute("session_pid");
			application = new ApplicationDAO().getApplicationByPid(session_pid);
		} else {
			application = new ApplicationDAO().getApplicationById(Integer
					.parseInt(applicationid));
		}

		if (formid == 0) {
			this.downloadApplicationForm(application);
		} else if (formid == 1) {
			this.downloadPidForm(application);
		} else if (formid == 2) {
			this.downloadGuaranteeForm(application);
		} else if (formid == 3) {
			this.downloadPaymentForm(application);
		} else if (formid == 5) {
			this.downloadExamForm(application);
		}
		// this.response.flushBuffer(); // ZeroBB 沒有這一行。
		return;
	}

	/**
	 * 第 5 表 准考證
	 * 
	 * @param application
	 */
	private void downloadExamForm(Application application) {
		ServletOutputStream out = null;
		FileInputStream stream = null;
		try {
			out = response.getOutputStream();
			// response.setContentType("application/octet-stream");
			String filename = new String(
					(application.getExamid() + "_准考證").getBytes("Big5"),
					"ISO8859_1")
					+ ".pdf";
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ filename + "\"");
			// response.setHeader("Content-Disposition", "inline; filename=\""
			// + filename + "\"");
			File file = new PDFCreater().createExamForm(application);
			stream = new FileInputStream(file);
			response.setHeader("Content-Length", String.valueOf(file.length()));

			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) { // writeatserverside
				out.write(buffer, 0, bytesRead);
			}
			stream.close();
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
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
	 * 第一表 報名表
	 * 
	 * @param application
	 */
	private void downloadApplicationForm(Application application) {
		ServletOutputStream out = null;
		FileInputStream stream = null;
		try {
			out = response.getOutputStream();
			// response.setContentType("application/octet-stream");
			String filename = new String(
					(application.getName() + "_報名表").getBytes("Big5"),
					"ISO8859_1")
					+ ".pdf";
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ filename + "\"");
			// response.setHeader("Content-Disposition", "inline; filename=\""
			// + filename + "\"");
			File file = new PDFCreater().createApplicationForm(application);
			stream = new FileInputStream(file);
			response.setHeader("Content-Length", String.valueOf(file.length()));

			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) { // writeatserverside
				out.write(buffer, 0, bytesRead);
			}
			stream.close();
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
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
	 * 第二表 身份證粘貼表
	 * 
	 * @param application
	 */
	private void downloadPidForm(Application application) {
		ServletOutputStream out = null;
		FileInputStream stream = null;
		try {
			out = response.getOutputStream();
			String filename = new String(
					(application.getName() + "_身份證粘貼表").getBytes("Big5"),
					"ISO8859_1")
					+ ".pdf";
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ filename + "\"");
			// response.setHeader("Content-Disposition", "inline; filename=\""
			// + filename + "\"");
			File file = new PDFCreater().createPidForm(application);
			stream = new FileInputStream(file);
			response.setHeader("Content-Length", String.valueOf(file.length()));

			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) { // writeatserverside
				out.write(buffer, 0, bytesRead);
			}
			stream.close();
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
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
	 * 第三表 切結書
	 * 
	 * @param application
	 */
	private void downloadGuaranteeForm(Application application) {
		ServletOutputStream out = null;
		FileInputStream stream = null;
		try {
			out = response.getOutputStream();
			// response.setContentType("application/octet-stream");
			String filename = new String(
					(application.getName() + "_切結書").getBytes("Big5"),
					"ISO8859_1")
					+ ".pdf";
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ filename + "\"");
			// response.setHeader("Content-Disposition", "inline; filename=\""
			// + filename + "\"");
			File file = new PDFCreater().createGuaranteeForm(application);
			stream = new FileInputStream(file);
			response.setHeader("Content-Length", String.valueOf(file.length()));

			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) { // writeatserverside
				out.write(buffer, 0, bytesRead);
			}
			stream.close();
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
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
	 * 第四表 繳款單
	 * 
	 * @param application
	 */
	private void downloadPaymentForm(Application application) {
		ServletOutputStream out = null;
		FileInputStream stream = null;
		try {
			out = response.getOutputStream();
			String filename = new String(
					(application.getName() + "_繳款單").getBytes("Big5"),
					"ISO8859_1")
					+ ".pdf";
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ filename + "\"");
			// response.setHeader("Content-Disposition", "inline; filename=\""
			// + filename + "\"");
			File file = new PDFCreater().createPaymentForm(application);
			stream = new FileInputStream(file);
			response.setHeader("Content-Length", String.valueOf(file.length()));

			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) { // writeatserverside
				out.write(buffer, 0, bytesRead);
			}
			stream.close();
			// response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
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

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
