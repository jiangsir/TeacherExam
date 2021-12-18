package jiangsir.teacherexam.Filter;

import java.io.IOException;
import java.util.HashMap;
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
import jiangsir.teacherexam.Config.AppConfig;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Objects.IpAddress;
import jiangsir.teacherexam.Scopes.ApplicationScope;
import jiangsir.teacherexam.Scopes.SessionScope;
import jiangsir.teacherexam.Servlets.LogoutServlet;
import jiangsir.teacherexam.Tables.CurrentUser;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter(filterName = "BannedFilter", urlPatterns = { "/*" }, asyncSupported = true)
public class Filter3_BannedFilter implements Filter {
	Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * Default constructor.
	 */
	public Filter3_BannedFilter() {
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
		// HttpServletResponse resp = (HttpServletResponse) response;
		String servletPath = req.getServletPath();
		HashMap<String, HttpServlet> urlpatterns = ApplicationScope.getUrlpatterns();
		HttpServlet httpServlet = urlpatterns.get(servletPath);
		IpAddress ip = new IpAddress(request.getRemoteAddr());

		AppConfig appConfig = ApplicationScope.getAppConfig();

		if (httpServlet != null && !ip.getIsSubnetOf(appConfig.getAllowed_ip())) {
			logger.info("ip=" + ip + " is not subnet of allowedIP=" + appConfig.getAllowed_ip());
			throw new DataException("您所在的位置(" + ip + ")並未允許存取本網站。");
		}

		CurrentUser currentUser = new SessionScope(req).getCurrentUser();

		if (currentUser != null && currentUser.getIsHigherEqualThanMANAGER()
				&& !ip.getIsSubnetOf(appConfig.getManager_ip()) && httpServlet.getClass() != LogoutServlet.class) {
			logger.info("ip=" + ip + " is not subnet of manager_ip=" + appConfig.getManager_ip());
			throw new DataException("為安全起見，在預設環境下，只有本機桌面瀏覽器可用管理員身分登入。<br/>您目前的網路位置(" + ip + ")並未開放管理員登入，"
					+ "請管理員由本機瀏覽器登入系統，頁面右下角「管理」->「系統管理」->「管理員 IP」開放網段。");
		}
		chain.doFilter(req, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
