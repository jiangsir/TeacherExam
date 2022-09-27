package jiangsir.teacherexam.Filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

// import org.json.JSONException;
// import org.json.JSONObject;
import jiangsir.teacherexam.Exceptions.Alert;
import jiangsir.teacherexam.Exceptions.JQueryException;

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

			ObjectMapper mapper = new ObjectMapper();
			if (e instanceof JQueryException) {
				HashMap<String, String> jsonObject = new HashMap<>();
				jsonObject.put("type", alert.getType().toString());
				jsonObject.put("title", alert.getTitle());
				// setStatus 一定要先設定好 SERVER_ERROR ，後面才能送 getWriter()
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().print(mapper.writeValueAsString(jsonObject));
				// response.getWriter().write(mapper.writeValueAsString(alert));
				response.flushBuffer();
				return;
			}

			// 原始 alert
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
