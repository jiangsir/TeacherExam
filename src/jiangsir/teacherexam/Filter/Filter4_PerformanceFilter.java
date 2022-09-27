package jiangsir.teacherexam.Filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import jiangsir.teacherexam.DAOs.LogDAO;
import jiangsir.teacherexam.Scopes.ApplicationScope;
import jiangsir.teacherexam.Scopes.SessionScope;
import jiangsir.teacherexam.Tables.CurrentUser;
import jiangsir.teacherexam.Tables.Log;

/**
 * Servlet Filter implementation class PerformanceFilter
 */
@WebFilter(filterName = "PerformanceFilter", urlPatterns = { "/*" }, asyncSupported = true)
public class Filter4_PerformanceFilter implements Filter {
	Logger logger = Logger.getLogger(this.getClass().getName());
	static long lastMailtime = 0;

	/**
	 * Default constructor.
	 */
	public Filter4_PerformanceFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();
		HttpServlet httpServlet = ApplicationScope.getUrlpatterns().get(servletPath);
		if (httpServlet != null) {
			long begin = System.currentTimeMillis();
			request.setAttribute("ms", begin);

			ApplicationScope.setMemoryInfo();

			chain.doFilter(request, response);

			long spend = (System.currentTimeMillis() - begin);
			if (spend > 30 * 1000) {
				// AppConfig appConfig = ApplicationScope.getAppConfig();
				CurrentUser onlineUser = new SessionScope(req).getCurrentUser();
				Log log = new Log();
				log.setSession_pid(onlineUser == null ? "null" : onlineUser.toString());
				log.setTitle("耗時請求 (" + spend / 1000 + " s) requestURL=" + req.getMethod() + ": " + req.getRequestURL()
						+ (req.getQueryString() == null ? "" : "?" + req.getQueryString()));
				log.setUri(req.getRequestURI() + "?" + req.getQueryString());
				log.setMethod(req.getMethod());
				StringBuffer message = new StringBuffer(5000);
				// content.append(log.getTitle() + "\n");
				message.append("parameterMap=\n");
				for (String key : req.getParameterMap().keySet()) {
					String[] values = req.getParameterMap().get(key);
					message.append(key + "=" + Arrays.toString(values) + "\n");
				}
				message.append("onlineUser=" + onlineUser + "\n");
				message.append("remoteAddr=" + req.getRemoteAddr());

				log.setTitle(message.toString());
				new LogDAO().insert(log);
			}
			return;
		} else {
			chain.doFilter(request, response);
			return;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
