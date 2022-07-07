package jiangsir.teacherexam.Listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import net.htmlparser.jericho.Source;
import jiangsir.teacherexam.DAOs.GeneralDAO;
import jiangsir.teacherexam.DAOs.UserDAO;
import jiangsir.teacherexam.Scopes.ApplicationScope;
import jiangsir.teacherexam.Tables.User;
import jiangsir.teacherexam.Tools.*;

@WebListener
public class InitializedListener implements ServletContextListener {
	Daemon daemon = null;

	/**
	 *
	 */
	public void contextInitialized(ServletContextEvent event) {
		try {
			// this.daemon = new Daemon();
			// Thread daemonthread = new Thread(daemon);
			// daemonthread.start();
			// qx 暫不進行應用程式初始化<br>
			// qx 可考慮在此將 properties.xml 全部讀入 Context Initialized
			ServletContext context = event.getServletContext();

			ApplicationScope.setAllAttributes(context);

			for (User.ROLE role : User.ROLE.values()) {
				ApplicationScope.getRoleMap().put(role, new HashSet<Class<? extends HttpServlet>>());
			}

			Map<String, ? extends ServletRegistration> registrations = context.getServletRegistrations();
			for (String key : registrations.keySet()) {
				String servletClassName = registrations.get(key).getClassName();
				WebServlet webServlet;
				try {
					if (Class.forName(servletClassName).newInstance() instanceof HttpServlet) {
						HttpServlet httpServlet = (HttpServlet) Class.forName(servletClassName).newInstance();
						webServlet = httpServlet.getClass().getAnnotation(WebServlet.class);
						if (webServlet != null) {
							for (String urlpattern : webServlet.urlPatterns()) {
								ApplicationScope.getUrlpatterns().put(urlpattern, httpServlet);
							}
						}
					}
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			for (String urlpattern : ApplicationScope.getUrlpatterns().keySet()) {
				System.out.println(
						"urlpattern=" + urlpattern + ", servlet=" + ApplicationScope.getUrlpatterns().get(urlpattern));
			}
			context.setAttribute("urlpatterns", ApplicationScope.getUrlpatterns());

			ENV.context = context;
			ENV.LastContextRestart = new Date();
			ENV.setAPP_REAL_PATH(context.getRealPath("/"));
			ENV.setPATH_METAINF(ENV.getAPP_REAL_PATH() + System.getProperty("file.separator") + "META-INF"
					+ System.getProperty("file.separator"));
			ENV.setPATH_WEBINF(ENV.getAPP_REAL_PATH() + System.getProperty("file.separator") + "WEB-INF"
					+ System.getProperty("file.separator"));

			InputStream is = new FileInputStream(new File(ENV.getPATH_WEBINF(), "web.xml"));
			ENV.source_webxml = new Source(is);

			ConfigDAO configDao = new ConfigDAO();
			SystemConfig systemConfig = configDao.getSystemConfig();
			// ENV.setSystemConfig(systemConfig);
			context.setAttribute("SystemConfig", systemConfig);
			// context.setAttribute("ActiveExam", new ExamDAO().getActiveExam());

			context.setAttribute("OnlineUsers", new Hashtable<String, HttpSession>());
			new GeneralDAO(event).initConnection(context);

			UserDAO userDao = new UserDAO();
			if (userDao.getAllUsers().size() == 0) {
				userDao.insertDefaultUsers();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// ApplicationScope.setInitException(e.getLocalizedMessage());
		}

	}

	public void contextDestroyed(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		while (!ENV.ThreadPool.isEmpty()) {
			Thread thread = ENV.ThreadPool.get(ENV.ThreadPool.firstKey());
			thread.interrupt();
			// System.out.println("關閉 thread: " + thread);
			ENV.ThreadPool.remove(ENV.ThreadPool.firstKey());
		}
		// TODO_DONE! qx 結束連結

		Connection conn = (Connection) context.getAttribute("conn");
		context.removeAttribute("conn");
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
			System.out.println(ENV.logHeader() + "資料庫連結關閉");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
