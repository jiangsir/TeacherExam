package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jiangsir.teacherexam.DAOs.CurrentUserDAO;
import jiangsir.teacherexam.DAOs.UserDAO;
import jiangsir.teacherexam.Scopes.SessionScope;
import jiangsir.teacherexam.Tables.*;

@WebServlet(urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		String pid = request.getParameter("pid");
		String passwd = request.getParameter("passwd");
		User user = new UserDAO().getUserByPidPasswd(pid, passwd);
		if (!user.getIsNullUser()) {
			SessionScope sessionScope = new SessionScope(session);
			sessionScope.setCurrentUser(new CurrentUserDAO().getCurrentUserById(user.getId(), session));
			response.sendRedirect(request.getContextPath() + sessionScope.getCurrentPage());
			return;
		} else {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			return;
		}

		// session.setAttribute("session_pid", user.getPid());
		// session.setAttribute("passed", "true");
		//
		// session.removeAttribute("OriginalURI");
		// String CurrentIP = request.getRemoteAddr();
		// Login checker = new Login();
		// // String theURI = targetURI.substring(targetURI.lastIndexOf('/') +
		// 1);
		// String CurrentPage = (String) session.getAttribute("CurrentPage");
		// if (CurrentPage == null) {
		// CurrentPage = "./";
		// }
		// CurrentPage = CurrentPage.substring(CurrentPage.lastIndexOf('/') +
		// 1);
		// // qx 如果 CurrentPage 是 Login 代表user 直接按 登入
		// if (CurrentPage != null && "/Login".equals(CurrentPage)) {
		// // theURI = CurrentPage;
		// // qx target 指向 前一頁, 也就是 Login 完 跳回原來那一頁
		// Utils.PreviousPage(session);
		// }
		// response.sendRedirect(Utils.CurrentPage(request));
	}
}
