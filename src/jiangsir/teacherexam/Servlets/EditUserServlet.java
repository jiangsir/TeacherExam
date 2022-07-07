package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Factories.UserFactory;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Tables.CurrentUser;
import jiangsir.teacherexam.Tables.User;
import jiangsir.teacherexam.Annotations.RoleSetting;
import jiangsir.teacherexam.DAOs.*;

@WebServlet(urlPatterns = {"/EditUser"})
@RoleSetting
public class EditUserServlet extends HttpServlet implements IAccessFilter {

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
		request.setAttribute("user", new UserDAO().getUserById(request.getParameter("userid")));
		request.getRequestDispatcher("EditUser.jsp").forward(request, response);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO userDao = new UserDAO();
		User user = userDao.getUserById(request.getParameter("userid"));
		user.setPid(request.getParameter("pid"));
		user.setPasswd(request.getParameter("passwd1"), request.getParameter("passwd2"));
		userDao.update(user);

		response.sendRedirect("." + AdminServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0]);
	}

}
