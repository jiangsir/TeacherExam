package jiangsir.teacherexam.Factories;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jiangsir.teacherexam.Scopes.SessionScope;
import jiangsir.teacherexam.Tables.CurrentUser;
import jiangsir.teacherexam.Tables.User;

public class UserFactory {
	private static User nullUser = new User();

	public static User getNullUser() {
		return nullUser;
	}

	public static CurrentUser getNullCurrentUser() {
		return new CurrentUser();
	}

	public static CurrentUser getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return UserFactory.getCurrentUser(session);
	}

	/**
	 * 取出當前登入的使用者 sessionUser
	 * 
	 * @param session
	 * @return
	 */
	public static CurrentUser getCurrentUser(HttpSession session) {
		if (session == null) {
			// return UserFactory.getNullCurrentUser();
			return null;
		} else {
			CurrentUser currentUser = new SessionScope(session)
					.getCurrentUser();
			if (currentUser != null) {
				return currentUser;
			} else {
				return UserFactory.getNullCurrentUser();
			}
		}
	}

}
