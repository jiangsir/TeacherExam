package jiangsir.teacherexam.Listener;

import java.util.Hashtable;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;

import jiangsir.teacherexam.Tools.ENV;

@WebListener
public class MyHttpSessionAttributeListener implements
		javax.servlet.http.HttpSessionAttributeListener {

	@SuppressWarnings("unchecked")
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();
		// 20081021 if (name.equals("Userinfo")) {
		if (name.equals("UserObject")) {
			HttpSession session = event.getSession();
			System.out.println("Listener 加入 sessionid=" + session.getId()
					+ ", session=" + session);
			synchronized (ENV.context) {
				Hashtable OnlineUsers = (Hashtable) ENV.context
						.getAttribute("OnlineUsers");
				OnlineUsers.put(session.getId(), session);
				ENV.context.setAttribute("OnlineUsers", OnlineUsers);
				// 20090520 改由 applicationScope 來處理
				// ENV.OnlineUsers.put(session.getId(), session);
			}
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();
		// 20081021 if (name.equals("Userinfo")) {
		if (name.equals("UserObject")) {
			HttpSession session = event.getSession();
			System.out.println("Listener 移除 sessionid=" + session.getId()
					+ ", session=" + session);
			Hashtable OnlineUsers = (Hashtable) ENV.context
					.getAttribute("OnlineUsers");
			OnlineUsers.remove(session.getId());
			ENV.context.setAttribute("OnlineUsers", OnlineUsers);
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		// System.out.println("attribute Listener 置換!!!");
	}
}
