package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import jiangsir.teacherexam.Annotations.RoleSetting;
import jiangsir.teacherexam.DAOs.ApplicationDAO;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Factories.UserFactory;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.CurrentUser;
import jiangsir.teacherexam.Tables.Exam;
import jiangsir.teacherexam.Tables.User.ROLE;

@WebServlet(urlPatterns = { "/Step2Scoreboard.html" })
@RoleSetting(allowHigherThen = ROLE.USER)
public class Step2ScoreboardServlet extends HttpServlet implements IAccessFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void AccessFilter(HttpServletRequest request) throws AccessException {
		CurrentUser currentUser = UserFactory.getCurrentUser(request);
		if (currentUser.getIsAdmin()) {
			return;
		}
		String applicationid = request.getParameter("applicationid");
		if (applicationid == null || !applicationid.matches("[0-9]+")) {
			throw new AccessException("參數錯誤! 未指定【報名表】編號或編號錯誤!");
		}

		Application application = new ApplicationDAO().getApplicationById(Integer.parseInt(applicationid));
		if (!currentUser.getPid().equals(application.getPid())) {
			throw new AccessException("您不能查詢他人資料！");
		}
		Exam exam = application.getExam();

		Calendar cal = Calendar.getInstance();
		cal.clear();
		// cal.set(2012, 6, 11, 17, 00);
		// cal.setTime((java.util.Date) activeexam.getScoreboardbegin());

		if (exam.getStep2scoreboardbegin().after(new Date(System.currentTimeMillis()))) {
			throw new AccessException("本次甄選(" + exam.getTitle() + ") 尚未開放查詢複試成績。");
		}
		return;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CurrentUser currentUser = UserFactory.getCurrentUser(request);
		String applicationid = request.getParameter("applicationid");
		Application application = new ApplicationDAO().getApplicationById(Integer.parseInt(applicationid));
		request.setAttribute("application", application);
		request.setAttribute("user", currentUser);
		request.getRequestDispatcher("Step2Scoreboard.jsp").forward(request, response);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
