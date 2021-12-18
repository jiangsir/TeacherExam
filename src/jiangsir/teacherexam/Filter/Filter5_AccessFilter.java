package jiangsir.teacherexam.Filter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jiangsir.teacherexam.Annotations.RoleSetting;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Interfaces.IAccessFilter;
import jiangsir.teacherexam.Scopes.ApplicationScope;
import jiangsir.teacherexam.Scopes.SessionScope;
import jiangsir.teacherexam.Tables.CurrentUser;
import jiangsir.teacherexam.Tables.User;

/**
 * Servlet Filter implementation class AccessFilter
 */
@WebFilter(filterName = "AccessFilter", urlPatterns = { "/*" }, asyncSupported = true)
public class Filter5_AccessFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public Filter5_AccessFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String servletPath = request.getServletPath();

		HttpServlet httpServlet = ApplicationScope.getUrlpatterns().get(servletPath);
		if (httpServlet == null && !servletPath.endsWith(".jsp")) {
			// 只有 servlet 才進去判斷 Role, AccessFilter
			// 有些頁面是不需要登入（沒有設定 RoleSetting），但仍需要過濾 AccessFilter 因此不能在這裡放過去。
			// || httpServlet.getClass().getAnnotation(RoleSetting.class) ==
			// null
			chain.doFilter(request, response);
			return;
		}
		System.out.println("AccessFilter: " + servletPath);
		if (httpServlet.getClass().getAnnotation(RoleSetting.class) != null) {
			// 有 RoleSetting 的才進來判斷有沒有登入，並判斷權限是否符合，否則轉到登入頁。
			HttpSession session = request.getSession(false);
			CurrentUser currentUser = new SessionScope(session).getCurrentUser();
			new SessionScope(session).addHistory(servletPath, request.getQueryString());
			if (currentUser == null || currentUser.getIsNullUser()) {
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
				return;
			}
			// CurrentUser currentUser = new
			// SessionScope(session).getCurrentUser();
			if (!this.isUserInRoles(currentUser, httpServlet.getClass().getAnnotation(RoleSetting.class))) {
				// Alert alert = new Alert(Alert.TYPE.ROLEERROR, "您("
				// + currentUser.getPid() + ", " + currentUser.getRole()
				// + ")沒有存取權限！", httpServlet.getClass()
				// .getAnnotation(WebServlet.class).urlPatterns()[0], "", null);
				// alert.getDebugs().add(
				// currentUser.getPid()
				// + "("
				// + currentUser.getPid()
				// + ") 嘗試存取沒有權限的頁面 \""
				// + httpServlet.getClass()
				// .getAnnotation(WebServlet.class)
				// .urlPatterns()[0] + "\"");
				// alert.getDebugs().add(
				// "ROLE="
				// + currentUser.getRole()
				// + ", servletSet("
				// + ApplicationScope.getRoleMap()
				// .get(currentUser.getRole()).size()
				// + "個)="
				// + ApplicationScope.getRoleMap().get(
				// currentUser.getRole()));
				// throw new AccessException(alert);
				throw new AccessException("您不具備這個頁面的權限！");
			}
		}

		/**
		 * 最後一關，確定該使用者已經具備存取這個頁面之後，才進行判斷。<br>
		 * 由 servlet 獲得的參數來決定是否可以存取。固定寫在 servlet.AccessFilter(request);
		 */
		for (Class<?> iface : httpServlet.getClass().getInterfaces()) {
			if (iface == IAccessFilter.class) {
				for (Method method : IAccessFilter.class.getMethods()) {
					try {
						Method servletMethod = httpServlet.getClass().getDeclaredMethod(method.getName(),
								HttpServletRequest.class);
						servletMethod.invoke(httpServlet.getClass().newInstance(), new Object[] { request });
					} catch (SecurityException e) {
						e.printStackTrace();
						throw new AccessException(e);
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
						throw new AccessException(e);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						throw new AccessException(e);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						throw new AccessException(e);
					} catch (InvocationTargetException e) {
						// if (e.getTargetException() instanceof
						// AccessException) {
						// throw (AccessException) e.getTargetException();
						// }
						throw new AccessException(e.getTargetException());
					} catch (InstantiationException e) {
						e.printStackTrace();
						throw new AccessException(e);
					}
				}
			}
		}

		chain.doFilter(request, response);
	}

	private boolean isUserInRoles(User user, RoleSetting servletRole) {
		HashSet<User.ROLE> roleSet = new HashSet<User.ROLE>();
		if (user == null || user.getIsNullUser() || servletRole == null) {
			return false;
		}

		// 加入 高於指定的 role
		for (User.ROLE role : User.ROLE.values()) {
			if (servletRole.allowHigherThen().ordinal() >= role.ordinal()) {
				roleSet.add(role);
			}
		}

		// 加入 個別指定的 role
		for (User.ROLE role : servletRole.allows()) {
			if (user.getRole() == role) {
				roleSet.add(role);
			}
		}

		// 移除 低於指定的 role
		for (User.ROLE role : User.ROLE.values()) {
			if (servletRole.denyLowerThen().ordinal() <= role.ordinal()) {
				roleSet.remove(role);
			}
		}

		// 移除個別指定的 role
		for (User.ROLE role : servletRole.denys()) {
			if (user.getRole() == role) {
				roleSet.remove(role);
			}
		}
		return roleSet.contains(user.getRole());
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
