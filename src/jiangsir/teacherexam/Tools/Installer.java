/**
 * idv.jiangsir.Tools - Installer.java
 * 2012/1/8 下午5:40:25
 * nknush-001
 */
package jiangsir.teacherexam.Tools;

import java.io.File;
import jiangsir.teacherexam.Exceptions.AccessException;
import jiangsir.teacherexam.Exceptions.DataException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author nknush-001
 * 
 */
public class Installer {
	private File webapps;
	private String dbuser;
	private String dbpassword;
	ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

	public Installer(File webapps, File consoledir, String dbuser, String dbpassword) {
		if (!webapps.isDirectory() || !consoledir.isDirectory()) {
			return;
		}
		this.webapps = webapps;
		this.dbuser = dbuser;
		this.dbpassword = dbpassword;
	}

	public void run() {
		this.initContextxml();
		this.initSystemConfig();
	}

	/**
	 * 初始化 SystemConfig
	 */
	private void initSystemConfig() {
		ConfigDAO configDao = new ConfigDAO(webapps);
		SystemConfig systemConfig = configDao.getSystemConfig();
		try {
			systemConfig.setTITLE(new SystemConfig().getTITLE());
		} catch (DataException e1) {
			e1.printStackTrace();
		}
		try {
			systemConfig.setTITLE_IMAGE(new SystemConfig().getTITLE_IMAGE());
		} catch (DataException e) {
			e.printStackTrace();
		}
		try {
			systemConfig.setHEADER(new SystemConfig().getHEADER());
		} catch (DataException e) {
			e.printStackTrace();
		}
		systemConfig.setSYSTEM_MAIL(new SystemConfig().getSYSTEM_MAIL());

		configDao.writeSystemConfig(systemConfig);
	}

	/**
	 * 初始化 context.xml, 定義資料庫名稱及密碼
	 * 
	 */
	private void initContextxml() {
		System.out.println("初始化 context.xml");
		XMLParser parser = new XMLParser(webapps);
		parser.setContextParam_docBase("zerojudge");
		parser.setContextParam("Resource", "username", dbuser);
		parser.setContextParam("Resource", "password", dbpassword);
		parser.setDBHost("127.0.0.1");
		parser.setDBName("zerojudge");
		try {
			parser.writeContextxml();
		} catch (AccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提供 ant 呼叫用
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length == 4 && new File(args[0]).exists()) {
			new Installer(new File(args[0]), new File(args[1]), args[2], args[3]).run();
		} else {
			System.out.println("args.length=" + args.length);
			System.out.println("參數個數不合，請檢查。");
		}
	}

}
