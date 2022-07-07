/**
 * idv.jiangsir.objects - Contest.java
 * 2008/2/19 下午 04:32:20
 * jiangsir
 */
package jiangsir.teacherexam.Tools;

import org.codehaus.jackson.map.ObjectMapper;
import jiangsir.teacherexam.Tools.ConfigDAO;
import jiangsir.teacherexam.Exceptions.DataException;

/**
 * @author jiangsir
 * 
 */
public class SystemConfig {
	private String[] MANAGER_DOMAINS = new String[] { "127.0.0.1",
			"0:0:0:0:0:0:0:1%0" };
	public final String KEY_MANAGER_DOMAINS = "MANAGER_DOMAINS";
	private String MESSAGE_SYSTEMCLOSE = "目前系統暫時關閉中！請稍候！";
	public final String KEY_MESSAGE_SYSTEMCLOSE = "MESSAGE_SYSTEMCLOSE";
	private boolean IS_SYSTEMOPEN = true;
	public final String KEY_IS_SYSTEMOPEN = "IS_SYSTEMOPEN";

	private String AUTHOR = "Qixun Jiang";
	public final String KEY_AUTHOR = "AUTHOR";
	private String JDBC = "mysql";
	public final String KEY_JDBC = "JDBC";
	private String AllowedIP = "*";
	public final String KEY_AllowedIP = "AllowedIP";
	private int PAGESIZE = 20;
	public final String KEY_PAGESIZE = "PAGESIZE";
	private int MAX_IP_CONNECTION = 1000;
	public final String KEY_MAX_IP_CONNECTION = "MAX_IP_CONNECTION";
	private String SYSTEM_MAIL = "jiangsir@tea.nknush.kh.edu.tw";
	public final String KEY_SYSTEM_MAIL = "SYSTEM_MAIL";
	private int JVM = 1000; // MB
	public final String KEY_JVM = "JVM";

	private String TITLE = "";
	public final String KEY_TITLE = "TITLE";
	private String TITLE_IMAGE = "";
	public final String KEY_TITLE_IMAGE = "TITLE_IMAGE";
	private String HEADER = "";
	public final String KEY_HEADER = "HEADER";
	private String HEADER2 = "";
	public final String KEY_HEADER2 = "HEADER2";
	// private Integer ACTIVE_EXAMID = 0;
	// public final String KEY_ACTIVE_EXAMID = "ACTIVE_EXAMID";

	// ///////////////////////////////////////////////////////////
	ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

	public SystemConfig() {
	}

	// public String getMANAGER_DOMAINS() {
	// return MANAGER_DOMAINS;
	// }
	//
	// public void setMANAGER_DOMAINS(String mANAGERDOMAINS) throws
	// DataException {
	// if (mANAGERDOMAINS == null) {
	// throw new DataException(new ConfigDAO().file.getPath() + " KEY "
	// + this.KEY_MANAGER_DOMAINS + " is missing.");
	// }
	// MANAGER_DOMAINS = mANAGERDOMAINS;
	// }

	public String getMESSAGE_SYSTEMCLOSE() {
		return MESSAGE_SYSTEMCLOSE;
	}

	public String[] getMANAGER_DOMAINS() {
		return MANAGER_DOMAINS;
	}

	public void setMANAGER_DOMAINS(String[] mANAGER_DOMAINS) {
		MANAGER_DOMAINS = mANAGER_DOMAINS;
	}

	public void setMANAGER_DOMAINS(String mANAGER_DOMAINS) throws DataException {
		if (mANAGER_DOMAINS == null) {
			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
					+ this.KEY_MANAGER_DOMAINS + " is missing.");
		}
		String[] domains = mANAGER_DOMAINS.split(",");
		for (int i = 0; i < domains.length; i++) {
			domains[i] = domains[i].trim();
		}
		this.setMANAGER_DOMAINS(domains);
	}

	public void setMESSAGE_SYSTEMCLOSE(String mESSAGESYSTEMCLOSE)
			throws DataException {
		if (mESSAGESYSTEMCLOSE == null) {
			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
					+ this.KEY_MESSAGE_SYSTEMCLOSE + " is missing.");
		}
		MESSAGE_SYSTEMCLOSE = Utils.removeHtmlTag(mESSAGESYSTEMCLOSE);
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) throws DataException {
		if (tITLE == null) {
			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
					+ this.KEY_TITLE + " is missing.");
		}
		TITLE = Utils.removeHtmlTag(tITLE);
	}

	public String getHEADER() {
		return HEADER;
	}

	public void setHEADER(String hEADER) throws DataException {
		if (hEADER == null) {
			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
					+ this.KEY_HEADER + " is missing.");
		}
		HEADER = hEADER;
	}

	public String getHEADER2() {
		return HEADER2;
	}

	public void setHEADER2(String hEADER2) throws DataException {
		if (hEADER2 == null) {
			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
					+ this.KEY_HEADER2 + " is missing.");
		}
		HEADER2 = hEADER2;
	}

	public String getTITLE_IMAGE() {
		return TITLE_IMAGE;
	}

	public void setTITLE_IMAGE(String tITLEIMAGE) throws DataException {
		if (tITLEIMAGE == null) {
			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
					+ this.KEY_TITLE_IMAGE + " is missing.");
		}
		TITLE_IMAGE = tITLEIMAGE;
	}

	public boolean getIS_SYSTEMOPEN() {
		return IS_SYSTEMOPEN;
	}

	public void setIS_SYSTEMOPEN(String iSSYSTEMOPEN) throws DataException {
		if (iSSYSTEMOPEN == null) {
			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
					+ this.KEY_IS_SYSTEMOPEN + " is missing.");
		}
		try {
			IS_SYSTEMOPEN = Boolean.parseBoolean(iSSYSTEMOPEN);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException(e.getLocalizedMessage());
		}
	}

	public String getKEY_HEADER2() {
		return KEY_HEADER2;
	}

	public void setMAX_IP_CONNECTION(int mAXIPCONNECTION) {
		MAX_IP_CONNECTION = mAXIPCONNECTION;
	}

	public void setJVM(int jVM) {
		JVM = jVM;
	}

//	public int getACTIVE_EXAMID() {
//		return ACTIVE_EXAMID;
//	}
//
//	public void setACTIVE_EXAMID(String aCTIVE_EXAMID) throws DataException {
//		if (aCTIVE_EXAMID == null) {
//			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
//					+ this.KEY_ACTIVE_EXAMID + " is missing.");
//		}
//		ACTIVE_EXAMID = Integer.parseInt(aCTIVE_EXAMID.trim());
//	}

	public void setPAGESIZE(int pAGESIZE) {
		PAGESIZE = pAGESIZE;
	}

	public String getKEY_MANAGER_DOMAINS() {
		return KEY_MANAGER_DOMAINS;
	}

	public String getKEY_MESSAGE_SYSTEMCLOSE() {
		return KEY_MESSAGE_SYSTEMCLOSE;
	}

	public String getKEY_TITLE() {
		return KEY_TITLE;
	}

	public String getKEY_IS_SYSTEMOPEN() {
		return KEY_IS_SYSTEMOPEN;
	}

	public String getKEY_HEADER() {
		return KEY_HEADER;
	}

	public String getKEY_TITLE_IMAGE() {
		return KEY_TITLE_IMAGE;
	}

	public String getAUTHOR() {
		return AUTHOR;
	}

	public void setAUTHOR(String aUTHOR) throws DataException {
		if (aUTHOR == null || "".equals(aUTHOR) || "null".equals(aUTHOR)) {
			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
					+ this.KEY_AUTHOR + " is missing.");
		}
		AUTHOR = aUTHOR;
	}

	public String getJDBC() {
		return JDBC;
	}

	public void setJDBC(String jDBC) throws DataException {
		if (jDBC == null || "".equals(jDBC) || "null".equals(jDBC)) {
			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
					+ this.KEY_JDBC + " is missing.");
		}
		JDBC = jDBC;
	}

	public String getAllowedIP() {
		return AllowedIP;
	}

	public void setAllowedIP(String allowedIP) throws DataException {
		if (allowedIP == null || "".equals(allowedIP)) {
			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
					+ this.KEY_AllowedIP + " is missing.");
		}
		AllowedIP = allowedIP;
	}

	public int getPAGESIZE() {
		return PAGESIZE;
	}

	public void setPAGESIZE(String pAGESIZE) throws DataException {
		if (pAGESIZE == null) {
			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
					+ this.KEY_PAGESIZE + " is missing.");
		}
		try {
			PAGESIZE = Integer.parseInt(pAGESIZE);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
					+ this.KEY_PAGESIZE + " is not an Integer.");
		}
	}

	public int getMAX_IP_CONNECTION() {
		return MAX_IP_CONNECTION;
	}

	public void setMAX_IP_CONNECTION(String mAXIPCONNECTION)
			throws DataException {
		if (mAXIPCONNECTION == null) {
			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
					+ this.KEY_MAX_IP_CONNECTION + " is missing.");
		}
		MAX_IP_CONNECTION = Integer.parseInt(mAXIPCONNECTION.trim());
	}

	public String getSYSTEM_MAIL() {
		return SYSTEM_MAIL;
	}

	public void setSYSTEM_MAIL(String sYSTEMMAIL) {
		SYSTEM_MAIL = sYSTEMMAIL;
	}

	public int getJVM() {
		return JVM;
	}

	public void setJVM(String jVM) throws DataException {
		if (jVM == null) {
			throw new DataException(new ConfigDAO().file.getPath() + " KEY "
					+ this.KEY_JVM + " is missing.");
		}
		JVM = Integer.parseInt(jVM.trim());
	}

	public String getKEY_AUTHOR() {
		return KEY_AUTHOR;
	}

	public String getKEY_JDBC() {
		return KEY_JDBC;
	}

	public String getKEY_AllowedIP() {
		return KEY_AllowedIP;
	}

	public String getKEY_PAGESIZE() {
		return KEY_PAGESIZE;
	}

	public String getKEY_MAX_IP_CONNECTION() {
		return KEY_MAX_IP_CONNECTION;
	}

	public String getKEY_SYSTEM_MAIL() {
		return KEY_SYSTEM_MAIL;
	}

	public String getKEY_JVM() {
		return KEY_JVM;
	}

	public void setIS_SYSTEMOPEN(boolean iSSYSTEMOPEN) {
		IS_SYSTEMOPEN = iSSYSTEMOPEN;
	}

}
