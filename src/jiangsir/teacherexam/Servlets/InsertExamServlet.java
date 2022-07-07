package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Exceptions.JQueryException;
import jiangsir.teacherexam.Factories.UserFactory;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Tables.CurrentUser;
import jiangsir.teacherexam.Tables.Exam;
import jiangsir.teacherexam.Tables.Subject;
import jiangsir.teacherexam.Tables.Upfile;
import jiangsir.teacherexam.Annotations.RoleSetting;
import jiangsir.teacherexam.DAOs.*;
import jiangsir.teacherexam.Tools.Uploader;

@WebServlet(urlPatterns = { "/InsertExam.html" })
@RoleSetting
public class InsertExamServlet extends HttpServlet implements IAccessFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void AccessFilter(HttpServletRequest request) throws AccessException {
		// User session_user;
		// try {
		// session_user = new UserDAO().getUser(session_pid);
		// } catch (DataException e) {
		// e.printStackTrace();
		// return false;
		// }
		CurrentUser currentUser = UserFactory.getCurrentUser(request);
		if (currentUser.getIsAdmin()) {
			return;
		}
		throw new AccessException("您沒有這個權限");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// HttpSession session = request.getSession(false);
		// String session_pid = (String) session.getAttribute("session_pid");
		// User user;
		// try {
		// user = new UserDAO().getUser(session_pid);
		// } catch (DataException e) {
		// e.printStackTrace();
		// new AlertDispatcher(request, response).forward(new AlertBean(e));
		// return;
		// }

//		request.setAttribute("exam", new Exam());
//		request.setAttribute("user", UserFactory.getCurrentUser(request));
//		request.getRequestDispatcher("InsertExam.jsp").forward(request, response);

		Exam newexam = new Exam();
		newexam.setTitle("Default Exam");
		int examid = new ExamDAO().insert(newexam);
		Subject subject = new Subject();
		subject.setExamid(examid);
		new SubjectDAO().insert(subject);
		response.sendRedirect(
				"." + EditExamServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0] + "?examid=" + examid);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Exam newexam = new Exam();
//		Uploader uploader = new Uploader(request, response);
//
//		try {
//			newexam.setTitle(uploader.getParameter("title"));
//			// newexam.setSubjects(uploader.getParameterValues("subject"));
//			// newexam.setSeatpatterns(uploader.getParameterValues("seatpattern"));
//			newexam.setMoney(uploader.getParameter("money"));
//			newexam.setApplybegin(uploader.getParameter("applybegin"));
//			newexam.setApplyend(uploader.getParameter("applyend"));
//			newexam.setStartline(uploader.getParameter("startline"));
//			newexam.setDeadline(uploader.getParameter("deadline"));
//			newexam.setFirsttest(uploader.getParameter("firsttest"));
//			newexam.setSecondtest(uploader.getParameter("secondtest"));
//			newexam.setScoreboardbegin(uploader.getParameter("scoreboardbegin"));
//			newexam.setStep2scoreboardbegin(uploader.getParameter("step2scoreboardbegin"));
//			newexam.setExamroom(uploader.getParameter("examroom"));
//			newexam.setNote(uploader.getParameter("note"));
//			newexam.setFormnote(uploader.getParameter("formnote"));
//
//			newexam.setId(new ExamDAO().insert(newexam));
//
//			String[] subjectnames = uploader.getParameterValues("subjectname");
//			String[] seatpatterns = uploader.getParameterValues("seatpattern");
//			String[] nianduans = uploader.getParameterValues("nianduan");
//			String[] zhengshidailis = uploader.getParameterValues("zhengshidaili");
//			for (int i = 0; i < subjectnames.length; i++) {
//				Subject subject = new Subject();
//				subject.setExamid(newexam.getId());
//				subject.setNianduan(nianduans[i]);
//				subject.setZhengshidaili(zhengshidailis[i]);
//				subject.setName(subjectnames[i]);
//				subject.setSeatpattern(seatpatterns[i]);
//				new SubjectDAO().insert(subject);
//			}
//
//			int filecount = 0;
//			for (FileItem fileitem : uploader.getFileItems("upfiles")) {
//				if (fileitem.getName() == null || fileitem.getName().equals("")) {
//					continue;
//				}
//				// IE 會傳上完整的路徑，不好
//				String filename = fileitem.getName();
//				filename = filename.substring(filename.lastIndexOf("\\") + 1);
//
//				// 再 加入資料庫記錄
//				Upfile upfile = new Upfile();
//				upfile.setDescript(uploader.getParameterValues("descripts")[filecount++]);
//				upfile.setExamid(newexam.getId());
//				upfile.setFilename(filename);
//				upfile.setFiletype(fileitem.getContentType());
//				upfile.setFileblob(fileitem.getInputStream());
//				int id = new UpfileDAO().insert(upfile);
//				upfile.setId(id);
//			}
//		} catch (Exception e) {
//			throw new JQueryException(e);
//		}

		// response.sendRedirect("." +
		// AdminServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0]);
	}

}
