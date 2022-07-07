package jiangsir.teacherexam.Tables;

import java.io.Serializable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import jiangsir.teacherexam.Scopes.ApplicationScope;

public class CurrentUser extends User implements HttpSessionBindingListener,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8830679620579860915L;
	private HttpSession session = null;

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		ApplicationScope.getOnlineUsers().put(event.getSession().getId(), this);
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		ApplicationScope.getOnlineUsers().remove(event.getSession().getId());
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void doLogout() {
		if (session != null) {
			session.invalidate();
		}
	}

	public boolean getIsAdmin() {
		return this.getRole() == User.ROLE.ADMIN;
	}

	@Override
	public String toString() {
		return "[id=" + this.getId() + ", pid=" + this.getPid() + ", ROLE="
				+ this.getRole() + "]";
	}

}
