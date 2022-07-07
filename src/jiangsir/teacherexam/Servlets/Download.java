package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jiangsir.teacherexam.DAOs.UpfileDAO;
import jiangsir.teacherexam.Tables.Upfile;

/**
 * Servlet implementation class Download
 */
@WebServlet(urlPatterns = { "/Download" })
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpServletResponse response;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.response = response;
		this.response.setHeader("Pragma", "No-cache");
		this.response.setHeader("Cache-Control", "no-cache");
		this.response.setDateHeader("Expires", 0);
		this.response.setContentType("application/pdf");
		int upfileid = Integer.parseInt(request.getParameter("upfileid"));
		this.downloadUpfile(upfileid);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * 下載檔案
	 * 
	 * @param application
	 */
	private void downloadUpfile(int upfileid) {
		Upfile upfile = new UpfileDAO().getFileById(upfileid);
		ServletOutputStream out = null;
		// FileInputStream stream = null;
		try {
			out = response.getOutputStream();
			String filename = new String(
					(upfile.getFilename()).getBytes("Big5"), "ISO8859_1");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ filename + "\"");
			// response.setHeader("Content-Disposition", "inline; filename=\""
			// + filename + "\"");
			// File file = new PDFCreater().createPaymentForm(application);
			// stream = new FileInputStream(upfile.getBytes());
			response.setHeader("Content-Length",
					String.valueOf(upfile.getFileblob().available()));

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

}
