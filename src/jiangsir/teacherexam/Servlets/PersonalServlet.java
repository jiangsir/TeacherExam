package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Factories.UserFactory;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Scopes.ApplicationScope;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.CurrentUser;
import jiangsir.teacherexam.Tables.Exam;
import jiangsir.teacherexam.Tables.User;
import jiangsir.teacherexam.Annotations.RoleSetting;
import jiangsir.teacherexam.DAOs.*;

@WebServlet(urlPatterns = { "/Personal.html" })
@RoleSetting(allowHigherThen = User.ROLE.USER)
public class PersonalServlet extends HttpServlet implements IAccessFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Exam activeexam = new ExamDAO().getActiveExam();

	public void AccessFilter(HttpServletRequest request) throws AccessException {
		String examid = request.getParameter("examid");
		CurrentUser currentUser = UserFactory.getCurrentUser(request);
		if (currentUser.getIsAdmin()) {
			return;
		}
		Exam exam;
		if (examid == null) {
			exam = new ExamDAO().getLatestActiveExamByPid(currentUser.getPid());
		} else {
			exam = new ExamDAO().getExamById(examid);
		}
		if (exam == null) {
			throw new AccessException("您(" + currentUser.getPid() + ")未參加任何進行中的甄選。");
		}

		Application application = new ApplicationDAO().getApplicationByPidExamid(currentUser.getPid(),
				String.valueOf(exam.getId()));

		if (application == null) {
			// exam = new ExamDAO().getExamById(examid);
			throw new AccessException("您(" + currentUser.getPid() + ")可能沒有報名本次甄選(" + exam.getTitle() + ")，請查明，或聯繫本校"
					+ ApplicationScope.getAppConfig().getRenshi() + "！");
		}
		return;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CurrentUser currentUser = UserFactory.getCurrentUser(request);
		String examid = request.getParameter("examid");
		Exam exam;
		if (examid == null) {
			exam = new ExamDAO().getLatestActiveExamByPid(currentUser.getPid());
		} else {
			exam = new ExamDAO().getExamById(examid);
		}
		if (!exam.getIsactive()) {
			throw new DataException("本甄選(" + exam.getTitle() + ") 已經關閉！");
		}
//		Application application;
//		if (currentUser.getIsAdmin()) {
//			String pid = request.getParameter("pid");
//			application = new ApplicationDAO().getApplicationByPidExamid(pid, String.valueOf(exam.getId()));
//		} else {
//			application = new ApplicationDAO().getApplicationByPidExamid(currentUser.getPid(),
//					String.valueOf(exam.getId()));
//		}
		Application application;
		if (currentUser.getIsAdmin()) {
			String pid = request.getParameter("pid");
			application = new ApplicationDAO().getApplicationByPidExamid(pid, String.valueOf(exam.getId()));
		} else {
			application = new ApplicationDAO().getApplicationByPidExamid(currentUser.getPid(),
					String.valueOf(exam.getId()));
		}
		request.setAttribute("application", application);
		request.setAttribute("user", currentUser);
		request.getRequestDispatcher("Personal.jsp").forward(request, response);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
