/**
 * idv.jiangsir.Beans - CourseBean.java
 * 2009/12/20 下午5:07:14
 * nknush-001
 */
package jiangsir.teacherexam.Beans;

import jiangsir.teacherexam.DAOs.UserDAO;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Tables.*;

/**
 * @author nknush-001
 * 
 */
public class UserBean_NOUSE {
	private User user = null;
	private String pid = null;

	public UserBean_NOUSE() {
	}

	public UserBean_NOUSE(User user) {
		this.setPid(user.getPid());
	}

	public UserBean_NOUSE(String pid) {
		this.pid = pid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
//		this.pid = pid;
//		if (this.user == null) {
//			try {
//				this.user = new UserDAO().getUserByPid(pid);
//			} catch (DataException e) {
//				e.printStackTrace();
//			}
//		}
	}

	// ===================================================================================

	public User getUser() {
//		if (this.user == null) {
//			try {
//				return new UserDAO().getUserByPid(pid);
//			} catch (DataException e) {
//				e.printStackTrace();
//			}
//		}
		return this.user;
	}

}
