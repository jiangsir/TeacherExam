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

@WebServlet(urlPatterns = { "/EditExam.html" })
@RoleSetting
public class EditExamServlet extends HttpServlet implements IAccessFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void AccessFilter(HttpServletRequest request) throws AccessException {
		CurrentUser currentUser = UserFactory.getCurrentUser(request);
		if (currentUser.getIsAdmin()) {
			return;
		}
		throw new AccessException("您沒有權限！");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// HttpSession session = request.getSession(false);
		// String session_pid = (String) session.getAttribute("session_pid");
		request.setAttribute("exam", new ExamDAO().getExamById(request.getParameter("examid")));
		// User user;
		// try {
		// user = new UserDAO().getUser(session_pid);
		// } catch (DataException e) {
		// e.printStackTrace();
		// new AlertDispatcher(request, response).forward(new AlertBean(e));
		// return;
		// }
		request.setAttribute("user", UserFactory.getCurrentUser(request));
		request.getRequestDispatcher("EditExam.jsp").forward(request, response);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Uploader uploader = new Uploader(request, response);
		System.out.println("doPost examid=" + uploader.getParameter("examid"));
		Exam exam = new ExamDAO().getExamById(uploader.getParameter("examid"));
		try {
			exam.setTitle(uploader.getParameter("title"));
			// exam.setSubjects(uploader.getParameterValues("subject"));
			// exam.setSeatpatterns(uploader.getParameterValues("seatpattern"));
			exam.setMoney(uploader.getParameter("money"));
			exam.setApplybegin(uploader.getParameter("applybegin"));
			exam.setApplyend(uploader.getParameter("applyend"));
			exam.setStartline(uploader.getParameter("startline"));
			exam.setDeadline(uploader.getParameter("deadline"));
			exam.setFirsttest(uploader.getParameter("firsttest"));
			exam.setSecondtest(uploader.getParameter("secondtest"));
			exam.setScoreboardbegin(uploader.getParameter("scoreboardbegin"));
			exam.setStep2scoreboardbegin(uploader.getParameter("step2scoreboardbegin"));
			exam.setExamroom(uploader.getParameter("examroom"));
			exam.setUploadform(uploader.getParameter("uploadform"));
			exam.setNote(uploader.getParameter("note"));
			exam.setFormnote(uploader.getParameter("formnote"));
			exam.setIsApplicationAlwaysEditable(uploader.getParameter("isapplicationalwayseditable"));

			new ExamDAO().update(exam);

			String[] subjectids = uploader.getParameterValues("subjectid");
			String[] subjectnames = uploader.getParameterValues("subjectname");
			String[] seatpatterns = uploader.getParameterValues("seatpattern");
			String[] nianduans = uploader.getParameterValues("nianduan");
			String[] zhengshidailis = uploader.getParameterValues("zhengshidaili");

			for (int i = 0; i < subjectnames.length; i++) {
				if (subjectids[i] == null || !subjectids[i].matches("[0-9]+")) {
					Subject subject = new Subject();
					subject.setExamid(exam.getId());
					subject.setNianduan(nianduans[i]);
					subject.setZhengshidaili(zhengshidailis[i]);
					subject.setName(subjectnames[i]);
					subject.setSeatpattern(seatpatterns[i]);
					new SubjectDAO().insert(subject);

				} else {
					Subject subject = new SubjectDAO().getSubjectById(Integer.parseInt(subjectids[i]));
					subject.setExamid(exam.getId());
					subject.setNianduan(nianduans[i]);
					subject.setZhengshidaili(zhengshidailis[i]);
					subject.setName(subjectnames[i]);
					subject.setSeatpattern(seatpatterns[i]);
					new SubjectDAO().update(subject);
				}
			}

			int filecount = 0;
			for (FileItem fileitem : uploader.getFileItems("upfiles")) {
				if (fileitem.getName() == null || fileitem.getName().equals("")) {
					continue;
				}
				// IE 會傳上完整的路徑，不好
				String filename = fileitem.getName();
				filename = filename.substring(filename.lastIndexOf("\\") + 1);

				// 再 加入資料庫記錄
				Upfile upfile = new Upfile();
				upfile.setExamid(exam.getId());
				upfile.setDescript(uploader.getParameterValues("descripts")[filecount++]);
				upfile.setFilename(filename);
				upfile.setFiletype(fileitem.getContentType());
				upfile.setFileblob(fileitem.getInputStream());
				int id = new UpfileDAO().insert(upfile);
				upfile.setId(id);
			}
		} catch (Exception e) {
			throw new JQueryException(e);
		}

		// response.sendRedirect("." +
		// AdminServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0]);
	}

}
