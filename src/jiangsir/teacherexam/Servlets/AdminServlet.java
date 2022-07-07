package jiangsir.teacherexam.Servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import jiangsir.teacherexam.Annotations.RoleSetting;
import jiangsir.teacherexam.Config.AppConfig;
import jiangsir.teacherexam.Config.AppConfig.FIELD;
import jiangsir.teacherexam.Config.AppConfigService;
import jiangsir.teacherexam.DAOs.BankdataDAO;
import jiangsir.teacherexam.DAOs.ExamDAO;
import jiangsir.teacherexam.DAOs.UserDAO;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Factories.UserFactory;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Scopes.ApplicationScope;
import jiangsir.teacherexam.Scopes.SessionScope;
import jiangsir.teacherexam.Tables.CurrentUser;
import jiangsir.teacherexam.Tables.User;
import jiangsir.teacherexam.Tools.ConfigDAO;
import jiangsir.teacherexam.Tools.SystemConfig;
import jiangsir.teacherexam.Tools.Utils;

@WebServlet(urlPatterns = { "/Admin" })
@RoleSetting
public class AdminServlet extends HttpServlet implements IAccessFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jiangsir.teacherexam.Tools.IAccessible#isAccessible(javax.servlet.http
	 * .HttpServletRequest)
	 */
	public void AccessFilter(HttpServletRequest request) throws AccessException {
		CurrentUser currentUser = UserFactory.getCurrentUser(request);
		// User session_user;
		// try {
		// session_user = new UserDAO().getUser(session_pid);
		// } catch (DataException e) {
		// e.printStackTrace();
		// throw new AccessException(session_pid, e.getLocalizedMessage());
		// }
		String ip = request.getRemoteAddr();
		SystemConfig systemConfig = new ConfigDAO().getSystemConfig();
		if (currentUser.getRole() == User.ROLE.ADMIN && Utils.isSubnetwork(systemConfig.getMANAGER_DOMAINS(), ip)) {
			return;
		}
		throw new AccessException("您的 IP 無權使用這個頁面。(ip=" + ip + ", pid=" + currentUser.getPid() + ")");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("exams", new ExamDAO().getAllVisibleExams());
		request.setAttribute("users", new UserDAO().getUsersByRoleAdmin());
		request.setAttribute("bankdatas", new BankdataDAO().getBankdatas());
		String url = request.getRequestURL().toString().replaceFirst(request.getServletPath(), "")
				+ ReceiveData.class.getAnnotation(WebServlet.class).urlPatterns()[0];
		request.setAttribute("receiveDataUrl", url);
		request.getRequestDispatcher("Admin.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AppConfig appConfig = ApplicationScope.getAppConfig();
		appConfig.setTitle(request.getParameter(FIELD.title.name()));
		appConfig.setSchoolname(request.getParameter(FIELD.schoolname.name()));
		appConfig.setPrincipal(request.getParameter(FIELD.principal.name()));
		appConfig.setZhuji(request.getParameter(FIELD.zhuji.name()));
		appConfig.setChuna(request.getParameter(FIELD.chuna.name()));
		appConfig.setRenshi(request.getParameter(FIELD.renshi.name()));
		appConfig.setRenshiphone(request.getParameter(FIELD.renshiphone.name()));
		appConfig.setBankprefix(request.getParameter(AppConfig.FIELD.bankprefix.name()));
		appConfig.setBankname(request.getParameter(AppConfig.FIELD.bankname.name()));
		appConfig.setBankhuming(request.getParameter(AppConfig.FIELD.bankhuming.name()));
		appConfig.setManager_ip(request.getParameter(AppConfig.FIELD.manager_ip.name()));
		appConfig.setAllowed_ip(request.getParameter(AppConfig.FIELD.allowed_ip.name()));
		new AppConfigService().update(appConfig);
		response.sendRedirect(request.getContextPath() + new SessionScope(request).getCurrentPage());
	}

}
