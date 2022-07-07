package jiangsir.teacherexam.Listener;

import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import jiangsir.teacherexam.DAOs.UserDAO;

@WebListener
public class MyHttpSessionListener implements
		javax.servlet.http.HttpSessionListener, ServletRequestListener {

	private HttpServletRequest request;

	public void requestDestroyed(ServletRequestEvent event) {

	}

	public void requestInitialized(ServletRequestEvent event) {
		request = (HttpServletRequest) event.getServletRequest();
	}

	@SuppressWarnings("unchecked")
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		// TODO 20090210 在此分析 IP 連線數
		String ipfrom = request.getRemoteAddr();
		session.setAttribute("remoteAddr", ipfrom);
		session.setAttribute("Locale", request.getLocale().toString());

		HashMap loggingmap = new HashMap();
		Enumeration enu = request.getHeaderNames();
		while (enu.hasMoreElements()) {
			String HeaderName = enu.nextElement().toString();
			// qx 觀察 getHeader
			// System.out.println(ENV.logHeader() + HeaderName + " = "
			// + request.getHeader(HeaderName));
			loggingmap.put(HeaderName, request.getHeader(HeaderName));
		}

	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		new UserDAO().doLogout(session);
	}

}
