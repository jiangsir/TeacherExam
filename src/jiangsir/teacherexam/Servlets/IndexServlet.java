package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import jiangsir.teacherexam.DAOs.ExamDAO;

@WebServlet(urlPatterns = { "/Index.html" })
public class IndexServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setAttribute("activeExam", new ExamDAO().getActiveExam());
		request.setAttribute("activeExams", new ExamDAO().getActiveExams());
		request.getRequestDispatcher("Index.jsp").forward(request, response);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
