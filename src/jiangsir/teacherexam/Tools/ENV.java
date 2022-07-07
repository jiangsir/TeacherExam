package jiangsir.teacherexam.Tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import net.htmlparser.jericho.Source;

/**
 * 本類別只供 InitializedListener 設定常數值<br>
 * 外部類別均以 ENV.REAL_PATH 方式取得
 * 
 * @author jiangsir
 * 
 */
public class ENV {

	// qx 此處做為常數使用，但因為初始化時必須設定值 <br>
	// TODO 因此無法定義為 final 再修正 <br>
	// 例：D:\Tomcat 5.5\webapps\ZeroJudge_Dev\
	public static String APP_REAL_PATH;
	private static String PATH_WEBINF;
	private static String PATH_METAINF;

	public static String SYSTEM_MONITOR_ACCOUNT;
	public static String SYSTEM_MONITOR_SELECTOR;
	public static String SYSTEM_MONITOR_IP;
	public static String APP_DIR;
	public static String APP_NAME;
	public static String COMMAND;
	public static int PAGESIZE;
	public static Hashtable<String, Integer> IP_CONNECTION = new Hashtable<String, Integer>();
	public static TreeMap<String, Thread> ThreadPool = new TreeMap<String, Thread>();
	public static String myPropertiesPath;
	public static ServletContext context = null;
	public static Date LastContextRestart;
	public static Source source_webxml = null;

	// public static SystemConfig systemConfig = null;

	// public static SystemConfig getSystemConfig() {
	// return systemConfig;
	// }
	//
	// public static void setSystemConfig(SystemConfig systemConfig) {
	// ENV.systemConfig = systemConfig;
	// }

	public static void setAPP_REAL_PATH(String app_real_path) {
		APP_REAL_PATH = app_real_path;
	}

	public static String getAPP_REAL_PATH() {
		return APP_REAL_PATH;
	}

	public static String getPATH_WEBINF() {
		return PATH_WEBINF;
	}

	public static void setPATH_WEBINF(String pATHWEBINF) {
		PATH_WEBINF = Utils.parsePath(pATHWEBINF);
	}

	public static String getPATH_METAINF() {
		return PATH_METAINF;
	}

	public static void setPATH_METAINF(String pATHMETAINF) {
		PATH_METAINF = Utils.parsePath(pATHMETAINF);
	}

	public static void setCOMMAND(String command) {
		COMMAND = command;
	}

	public static String datetime(long timestamp) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(
				timestamp));
	}

	public static Date datetime(String datestring) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(datestring);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Date();
		}
	}

	public static String getNow() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	private long timestamp = new Date().getTime();

	public void setTimestamp(String datestring) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = format.parse(datestring);
			this.timestamp = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.timestamp = new Date().getTime();
		}

	}

	public String getTimestamp() {
		return String.valueOf(this.timestamp);
	}

	public static void log(ServletConfig config, String message) {
		config.getServletContext().log(message);
	}

	private String CountryName;

	public String getCountryName() {
		return CountryName;
	}

	/**
	 * 英文縮寫國名與中文的對應
	 * 
	 * @param countryCode
	 * @return
	 */
	public void setCountryName(String countryCode) {
		if ("TW".equals(countryCode)) {
			CountryName = "台灣";
		} else if ("CN".equals(countryCode)) {
			CountryName = "中國";
		} else if ("HK".equals(countryCode)) {
			CountryName = "香港";
		} else {
			CountryName = countryCode;
		}
	}

	/**
	 * 定義所有固定要出現在 log 前面的字串
	 * 
	 * @return
	 */
	public static String logHeader() {
		return "[" + ENV.getNow() + " " + ENV.APP_NAME + "]: ";
	}

	public static void setAPP_DIR(String app_dir) {
		APP_DIR = app_dir;
	}

	public static void setAPP_NAME(String app_name) {
		APP_NAME = app_name;
	}

	public static int getPAGESIZE() {
		return PAGESIZE;
	}

	public static void setPAGESIZE(int pagesize) {
		PAGESIZE = pagesize;
	}

	public static ResourceBundle resource;

	/** *************** JAVA BEAN ************************** */

	private String FileLimitExceed = "檔案超過大小，請直接編輯測資檔";

	public String getFileLimitExceed() {
		return FileLimitExceed;
	}

	private String urlstring;

	public String getUrlstring() {
		return urlstring;
	}

	public void setUrlstring(String urlstring) {
		try {
			this.urlstring = URLEncoder.encode(urlstring, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String parseHTML;

	public String getParseHTML() {
		return parseHTML;
	}

	public String replydiv1 = "<div style=\"border-left:dotted;padding-left:20px;\">";

	public String replydiv2 = "</div>";

	/**
	 * 寫入資料庫的資料如果需要用 HTML 的方式顯示, 許多特殊符號就必須轉換才行
	 * 
	 * @param content
	 * @return
	 */
	public void setParseHTML(String parseHTML) {
		if (parseHTML == null) {
			this.parseHTML = null;
			return;
		}
		parseHTML = parseHTML.replaceAll("&", "&amp;");
		parseHTML = parseHTML.replaceAll("<", "&lt;");
		parseHTML = parseHTML.replaceAll(">", "&gt;");
		parseHTML = parseHTML.replaceAll(" ", "&nbsp;");
		parseHTML = parseHTML.replaceAll("\"", "&quot;");
		this.parseHTML = parseHTML;
	}

	private String myProperty = "";

	public String getMyProperty() {
		return this.myProperty;
	}

	/**
	 * 在 java Bean 裡會使用到，在 JSTL 頁面
	 * 
	 * @param key
	 */
	public void setMyProperty(String key) {
		MyProperties myProperties = new MyProperties();
		this.myProperty = myProperties.getProperty(key);
	}

	private static String APP_BIN_PATH;

	public static String getAPP_BIN_PATH() {
		return APP_BIN_PATH;
	}

	public static void setAPP_BIN_PATH(String app_bin_path) {
		APP_BIN_PATH = app_bin_path;
	}

	public static String getSYSTEM_MONITOR_ACCOUNT() {
		return SYSTEM_MONITOR_ACCOUNT;
	}

	public static void setSYSTEM_MONITOR_ACCOUNT(String system_monitor_account) {
		SYSTEM_MONITOR_ACCOUNT = system_monitor_account;
	}

	public static String getSYSTEM_MONITOR_IP() {
		return SYSTEM_MONITOR_IP;
	}

	public static void setSYSTEM_MONITOR_IP(String system_monitor_ip) {
		SYSTEM_MONITOR_IP = system_monitor_ip;
	}

	public static String getSYSTEM_MONITOR_SELECTOR() {
		return SYSTEM_MONITOR_SELECTOR;
	}

	public static void setSYSTEM_MONITOR_SELECTOR(String system_monitor_selector) {
		SYSTEM_MONITOR_SELECTOR = system_monitor_selector;
	}

}
