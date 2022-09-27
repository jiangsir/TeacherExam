package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import jiangsir.teacherexam.Annotations.RoleSetting;
import jiangsir.teacherexam.DAOs.ApplicationDAO;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Factories.UserFactory;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Tables.Application;
import jiangsir.teacherexam.Tables.CurrentUser;
import jiangsir.teacherexam.Tools.ConfigDAO;
import jiangsir.teacherexam.Tools.SystemConfig;
import jiangsir.teacherexam.Tools.Utils;

@WebServlet(urlPatterns = { "/ImportStep2Scoreboard" })
@RoleSetting
public class ImportStep2ScoreboardServlet extends HttpServlet implements IAccessFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void AccessFilter(HttpServletRequest request) throws AccessException {
		CurrentUser currentUser = UserFactory.getCurrentUser(request);

		String ip = request.getRemoteAddr();
		SystemConfig systemConfig = new ConfigDAO().getSystemConfig();
		if (currentUser.getIsAdmin() && Utils.isSubnetwork(systemConfig.getMANAGER_DOMAINS(), ip)) {
			return;
		}
		throw new AccessException("您無權使用這個區域。");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("ImportStep2Scoreboard.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationDAO applicationDao = new ApplicationDAO();
		int examid = 0;
		try {
			examid = Integer.parseInt(request.getParameter("examid"));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			throw new DataException("競賽編號有誤(examid=" + request.getParameter("examid") + ")！無法匯入成績。");
		}
		String[] lines = request.getParameter("step2scorecsv").split("\\n");
		for (String line : lines) {
			try {
				if (line == null || line.trim().equals("") || line.startsWith("#")) {
					continue;
				}
				String[] s = line.trim().split(",");
				Application application = applicationDao.getApplicationBySeatid(examid, s[0]);
				application.setStep2totalscore(s[1]);
				application.setStep2result(s[2]);
				applicationDao.update(application);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect(
				"." + ManageExamServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0] + "?examid=" + examid);
	}

}
