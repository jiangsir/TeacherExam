package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import jiangsir.teacherexam.DAOs.ApplicationDAO;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Factories.UserFactory;
import jiangsir.teacherexam.Interfaces.IAccessFilter;

@WebServlet(urlPatterns = { "/PaymentForm.html" })
public class PaymentFormServlet extends HttpServlet implements IAccessFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void AccessFilter(HttpServletRequest request) throws AccessException {
		return;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// HttpSession session = request.getSession(false);
		// String session_pid = (String) session.getAttribute("session_pid");
		request.setAttribute("application", new ApplicationDAO()
				.getApplicationByPid(UserFactory.getCurrentUser(request)
						.getPid()));
		request.getRequestDispatcher("PaymentForm.jsp").forward(request,
				response);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
