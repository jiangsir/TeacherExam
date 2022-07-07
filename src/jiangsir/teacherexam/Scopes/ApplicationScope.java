package jiangsir.teacherexam.Scopes;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;

import jiangsir.teacherexam.Config.AppConfig;
import jiangsir.teacherexam.Config.AppConfigService;
import jiangsir.teacherexam.Servlets.LoginServlet;
import jiangsir.teacherexam.Tables.Bankdata;
import jiangsir.teacherexam.Tables.Subject;
import jiangsir.teacherexam.Tables.User;

public class ApplicationScope {
	public static ServletContext servletContext = null;

	private static HashMap<String, HttpSession> onlineSessions = new HashMap<String, HttpSession>();
	private static HashMap<String, User> onlineUsers = new HashMap<String, User>();
	private static HashMap<String, HttpServlet> urlpatterns = new HashMap<String, HttpServlet>();
	private static String defaultLogin = LoginServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0];
	private static HashMap<User.ROLE, HashSet<Class<? extends HttpServlet>>> roleMap = new HashMap<User.ROLE, HashSet<Class<? extends HttpServlet>>>();
	private static File appRoot = null;
	private static AppConfig appConfig = null;
	private static String version = null;
	private static String built = null;
	private static String initException = "";
	private static String memoryInfo = "";
	// ===========================
	public static Hashtable<String, Bankdata> cacheBankdatas = new Hashtable<String, Bankdata>();
	public static Hashtable<Integer, Subject> cacheSubjects = new Hashtable<Integer, Subject>();

	public static void setAllAttributes(ServletContext servletContext) {
		ApplicationScope.servletContext = servletContext;

		ApplicationScope.setAppRoot();
		ApplicationScope.setOnlineSessions(onlineSessions);
		ApplicationScope.setOnlineUsers(onlineUsers);
		ApplicationScope.setCacheBankdatas(cacheBankdatas);
		ApplicationScope.setCacheSubjects(cacheSubjects);
		ApplicationScope.setUrlpatterns(urlpatterns);
		ApplicationScope.setDefaultLogin(defaultLogin);
		ApplicationScope.setRoleMap(roleMap);
		ApplicationScope.setAppConfig(new AppConfigService().getAppConfig());
		// ApplicationScope.setCanBookup();
		ApplicationScope.setVersion();
		ApplicationScope.setBuilt();
		ApplicationScope.setMemoryInfo();

	}

	public static HashMap<String, HttpSession> getOnlineSessions() {
		return onlineSessions;
	}

	public static void setOnlineSessions(HashMap<String, HttpSession> onlineSessions) {
		ApplicationScope.onlineSessions = onlineSessions;
		servletContext.setAttribute("onlineSessions", onlineSessions);
	}

	public static Hashtable<String, Bankdata> getCacheBankdatas() {
		return cacheBankdatas;
	}

	public static void setCacheBankdatas(Hashtable<String, Bankdata> cacheBankdatas) {
		ApplicationScope.cacheBankdatas = cacheBankdatas;
		servletContext.setAttribute("cacheBankdatas", cacheBankdatas);
	}

	public static Hashtable<Integer, Subject> getCacheSubjects() {
		return cacheSubjects;
	}

	public static void setCacheSubjects(Hashtable<Integer, Subject> cacheSubjects) {
		ApplicationScope.cacheSubjects = cacheSubjects;
		servletContext.setAttribute("cacheSubjects", cacheSubjects);
	}

	public static HashMap<String, User> getOnlineUsers() {
		return onlineUsers;
	}

	public static void setOnlineUsers(HashMap<String, User> onlineUsers) {
		ApplicationScope.onlineUsers = onlineUsers;
		servletContext.setAttribute("onlineUsers", onlineUsers);
	}

	public static HashMap<String, HttpServlet> getUrlpatterns() {
		return urlpatterns;
	}

	public static void setUrlpatterns(HashMap<String, HttpServlet> urlpatterns) {
		ApplicationScope.urlpatterns = urlpatterns;
		servletContext.setAttribute("urlpatterns", urlpatterns);
	}

	public static File getAppRoot() {
		return appRoot;
	}

	public static void setAppRoot(File appRoot) {
		ApplicationScope.appRoot = appRoot;
	}

	public static void setAppRoot() {
		ApplicationScope.appRoot = new File(servletContext.getRealPath("/"));
		ApplicationScope.servletContext.setAttribute("appRoot", appRoot);
	}

	public static String getDefaultLogin() {
		return defaultLogin;
	}

	public static void setDefaultLogin(String defaultLogin) {
		ApplicationScope.defaultLogin = defaultLogin;
		servletContext.setAttribute("defaultLogin", defaultLogin);
	}

	public static HashMap<User.ROLE, HashSet<Class<? extends HttpServlet>>> getRoleMap() {
		return roleMap;
	}

	public static void setRoleMap(HashMap<User.ROLE, HashSet<Class<? extends HttpServlet>>> roleMap) {
		ApplicationScope.roleMap = roleMap;
		servletContext.setAttribute("roleMap", roleMap);
	}

	public static AppConfig getAppConfig() {
		return appConfig;
	}

	public static void setAppConfig(AppConfig appConfig) {
		ApplicationScope.appConfig = appConfig;
		servletContext.setAttribute("appConfig", appConfig);
	}

	/**
	 * 取得目前系統的版本。
	 */
	public static String getVersion() {
		if (ApplicationScope.version == null) {
			setVersion();
		}
		return ApplicationScope.version;
	}

	/**
	 * 取得目前系統的版本。
	 */
	public static void setVersion() {
		try {
			ApplicationScope.version = FileUtils
					.readFileToString(new File(ApplicationScope.appRoot + File.separator + "META-INF", "Version.txt"))
					.trim();
		} catch (IOException e) {
			e.printStackTrace();
			// ApplicationScope.version = "";
		}
		servletContext.setAttribute("version", ApplicationScope.version);
	}

	public static String getBuilt() {
		if (ApplicationScope.built == null) {
			setBuilt();
		}
		return ApplicationScope.built;
	}

	public static void setBuilt() {
		ApplicationScope.built = new SimpleDateFormat("yyMMdd")
				.format(new Date(ApplicationScope.getAppRoot().lastModified()));
		servletContext.setAttribute("built", ApplicationScope.built);
	}

	public static String getInitException() {
		return initException;
	}

	public static void setInitException(String initException) {
		ApplicationScope.initException = initException;
		ApplicationScope.servletContext.setAttribute("initException", initException);
	}

	public static String getMemoryInfo() {
		ApplicationScope.setMemoryInfo();
		return memoryInfo;
	}

	public static void setMemoryInfo() {
		int freeMemory = (int) (Runtime.getRuntime().freeMemory() / 1024 / 1024);
		int totalMemory = (int) (Runtime.getRuntime().totalMemory() / 1024 / 1024);
		double percent = ((totalMemory - freeMemory) / (double) totalMemory) * 100;
		ApplicationScope.memoryInfo = (totalMemory - freeMemory) + "/" + totalMemory + " MB ("
				+ String.format("%.1f", percent) + "%)";
		servletContext.setAttribute("memoryInfo", ApplicationScope.memoryInfo);
	}

}
