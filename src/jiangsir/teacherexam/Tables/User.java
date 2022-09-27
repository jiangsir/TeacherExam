package jiangsir.teacherexam.Tables;

import java.io.Serializable;

import jiangsir.teacherexam.Annotations.Persistent;
import jiangsir.teacherexam.Exceptions.DataException;

/**
 *  - User.java
 * 2008/4/29 下午 05:41:54
 * jiangsir
 */

/**
 * @author jiangsir
 * 
 */
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3594066798332570336L;
	@Persistent(name = "id")
	private Integer id = 0;
	@Persistent(name = "pid")
	private String pid = ""; // 身份證字號
	@Persistent(name = "examid")
	private Integer examid = 0; // 考試編號
	@Persistent(name = "passwd")
	private String passwd = ""; // 密碼

	// public static String GROUP_USER = "GroupUser";
	// public static String GROUP_ADMIN = "GroupAdmin";

	public enum GROUP {
		GroupAdmin, GroupUser;
	}

	@Persistent(name = "group")
	private GROUP group = User.GROUP.GroupUser; // 所屬權限群組

	public enum ROLE {
		ADMIN("管理員權限"), // 管理權限
		MANAGER("普通管理員"), // 一般管理員
		USER("使用者"), // 一般使用者
		GUEST("訪客"); // 訪客，或未登入者

		private String value = "";

		ROLE(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public boolean isHigherEqualThan(ROLE role) {
			return this.ordinal() <= role.ordinal();
		}

		public boolean isLowerEqualThan(ROLE role) {
			return this.ordinal() >= role.ordinal();
		}
	}

	@Persistent(name = "role")
	private ROLE role = ROLE.GUEST;

	public User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GROUP getGroup() {
		return group;
	}

	public void setGroup(GROUP group) {
		this.group = group;
	}

	public void setGroup(String group) {
		this.setGroup(GROUP.valueOf(group));
	}

	public Integer getExamid() {
		return examid;
	}

	public void setExamid(Integer examid) {
		this.examid = examid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) throws DataException {
		/*
		 * User 的 setPid 會開放給 ROLE=ADMIN 變更，因此不限定身分證格式。但 Application.setPid 必須限定身分證格式。
		 */

		if (pid == null || pid.trim().equals("")) {
			return;
		}
		int MIN = 5;
		if (pid.length() < MIN) {
			throw new DataException("帳號長度應至少 " + MIN + "字元。");
		}
		this.pid = pid.trim();
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd, String passwd1) throws DataException {
		if (passwd == null || "".equals(passwd) || passwd1 == null || "".equals(passwd1)) {
			throw new DataException("密碼必須填寫兩次！");
		}
		if (!passwd.equals(passwd1)) {
			throw new DataException("兩次密碼必須相符！");
		}
		this.passwd = passwd;
	}

	public void setPasswd(String passwd) throws DataException {
		if (passwd == null || "".equals(passwd)) {
			throw new DataException("未發現密碼！");
		}
		this.passwd = passwd;
	}

	public boolean getIsAdmin(String pid) {
		if (ROLE.ADMIN == this.getRole()) {
			return true;
		}
		return false;
	}

	// public boolean getIsRoleAdmin() {
	// return this.getRole() == ROLE.ADMIN;
	// }
	public boolean getIsNullUser() {
		return this.getId() == new User().getId() && this.getPid() == new User().getPid();
	}

	public ROLE getRole() {
		return role;
	}

	public void setRole(ROLE role) {
		this.role = role;
	}

	public void setRole(String role) {
		this.setRole(User.ROLE.valueOf(role));
	}

	public boolean getIsHigherEqualThanMANAGER() {
		return this.getRole().isHigherEqualThan(ROLE.MANAGER);
	}

}
