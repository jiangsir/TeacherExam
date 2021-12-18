package jiangsir.teacherexam.Filter;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jiangsir.teacherexam.Exceptions.Alert;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter(filterName = "ExceptionHandlerFilter", urlPatterns = { "/*" }, asyncSupported = true)
public class Filter0_ExceptionHandlerFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public Filter0_ExceptionHandlerFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;

			Throwable rootCause = e;
			Alert alert = new Alert(e.getLocalizedMessage(), e);
			alert.getDebugs().add("由 " + this.getClass().getSimpleName() + " 所捕捉");
			ArrayList<String> list = alert.getList();
			while (rootCause.getCause() != null) {
				list.add(rootCause.getClass().getSimpleName() + ": " + rootCause.getLocalizedMessage());
				rootCause = rootCause.getCause();
			}
//			try {
//				alert.getUris().put("回前頁",
//						new URI(request.getContextPath() + new SessionScope(session).getPreviousPage()));
//			} catch (URISyntaxException e1) {
//				e1.printStackTrace();
//			}

			request.setAttribute("alert", alert);
			request.getRequestDispatcher("/Alert.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
