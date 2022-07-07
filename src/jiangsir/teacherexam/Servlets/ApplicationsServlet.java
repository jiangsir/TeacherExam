package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Factories.UserFactory;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.Exam;
import jiangsir.teacherexam.Annotations.RoleSetting;
import jiangsir.teacherexam.DAOs.*;

@WebServlet(urlPatterns = { "/Applications.html" })
@RoleSetting
public class ApplicationsServlet extends HttpServlet implements IAccessFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void AccessFilter(HttpServletRequest request) throws AccessException {
		return;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String examid = request.getParameter("examid");
		if (examid == null || !examid.matches("[0-9]+")) {
			throw new DataException("examid 參數不完整。");
		}
		Exam exam = new ExamDAO().getExamById(Integer.parseInt(examid));
		ArrayList<Application> applications = new ApplicationDAO().getPaidApplicationsByExamid(exam.getId());

		String subjectid = request.getParameter("subjectid");
		if (subjectid != null && subjectid.matches("[0-9]+")) {
			applications = new ApplicationDAO().getPaidApplicationsByExamidSubjectid(exam.getId(),
					Integer.parseInt(subjectid));
			request.setAttribute("subject", new SubjectDAO().getSubjectById(Integer.parseInt(subjectid)));
		}
		request.setAttribute("exam", exam);
		request.setAttribute("subjects", new SubjectDAO().getSubjectsByExamid(exam.getId()));
		request.setAttribute("applications", applications);

		request.setAttribute("user", UserFactory.getCurrentUser(request));
		Calendar end = Calendar.getInstance();
		end.setTime(new java.util.Date(exam.getApplyend().getTime()));
		end.add(Calendar.DATE, 3);

		request.setAttribute("begin", exam.getApplybegin());
		request.setAttribute("end", new Timestamp(end.getTime().getTime()));

		request.getRequestDispatcher("Applications_TABLE.jsp").forward(request, response);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
