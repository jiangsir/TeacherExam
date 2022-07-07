package jiangsir.teacherexam.Tables;

import java.sql.Timestamp;
import jiangsir.teacherexam.Annotations.Persistent;

/**
 *  - User.java
 * 2008/4/29 下午 05:41:54
 * jiangsir
 */

/**
 * @author jiangsir
 * 
 */
public class Log {
	@Persistent(name = "id")
	private Integer id = 0;
	@Persistent(name = "method")
	private String method = "";
	@Persistent(name = "uri")
	private String uri = "";
	@Persistent(name = "session_pid")
	private String session_pid = "";
	@Persistent(name = "ip")
	private String ip = "";
	@Persistent(name = "title")
	private String title = "";
	@Persistent(name = "subtitle")
	private String subtitle = "";
	@Persistent(name = "stacktrace")
	private String stacktrace = "";
	@Persistent(name = "debug")
	private String debug = "";
	@Persistent(name = "timestamp")
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	public Log() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getSession_pid() {
		return session_pid;
	}

	public void setSession_pid(String sessionPid) {
		if (sessionPid == null) {
			session_pid = "";
		} else {
			session_pid = sessionPid;
		}

	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	public void setStacktrace(Throwable t) {
		StringBuffer sb = new StringBuffer(5000);
		StackTraceElement[] st = t.getStackTrace();
		for (int i = 0; i < st.length; i++) {
			sb.append(st[i] + "\n");
		}
		this.stacktrace = sb.toString();
	}

	public String getDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
