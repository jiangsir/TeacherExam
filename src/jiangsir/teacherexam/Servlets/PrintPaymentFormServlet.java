package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Factories.UserFactory;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.CurrentUser;
import jiangsir.teacherexam.Tables.Exam;
import jiangsir.teacherexam.Tables.User.ROLE;
import jiangsir.teacherexam.Annotations.RoleSetting;
import jiangsir.teacherexam.DAOs.*;

@WebServlet(urlPatterns = { "/PrintPaymentForm.html" })
@RoleSetting(allowHigherThen = ROLE.USER)
public class PrintPaymentFormServlet extends HttpServlet implements IAccessFilter {
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
			throw new DataException("參數錯誤! 未指定【報名表】編號或編號錯誤!");
		}

		Application application = new ApplicationDAO().getApplicationById(Integer.parseInt(applicationid));
		if (!currentUser.getPid().equals(application.getPid())) {
			throw new AccessException("您不能查詢他人資料！");
		}

		Exam exam = application.getExam();
		Timestamp startline = exam.getStartline();
		if (exam.Before_Startline()) {
			throw new DataException("繳費尚未開始！(" + startline + "開始)");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CurrentUser currentUser = UserFactory.getCurrentUser(request);
		String applicationid = request.getParameter("applicationid");
//		if (applicationid == null || !applicationid.matches("[0-9]+")) {
//			throw new DataException("參數錯誤! 未指定【報名表】編號或編號錯誤!");
//		}
		Application application = new ApplicationDAO().getApplicationById(Integer.parseInt(applicationid));
		request.setAttribute("application", application);
		request.setAttribute("user", currentUser);
		request.getRequestDispatcher("PrintPaymentForm.jsp").forward(request, response);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}