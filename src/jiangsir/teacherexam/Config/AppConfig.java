package jiangsir.teacherexam.Config;

import java.sql.Timestamp;
import java.util.Date;
import java.util.TreeSet;
import jiangsir.teacherexam.Objects.IpAddress;
import jiangsir.teacherexam.Tools.StringTool;
import org.codehaus.jackson.map.ObjectMapper;

public class AppConfig {
	public static enum FIELD {
		id, //
		title, // 系統標題
		schoolname, // 學校名稱
		principal, // 校長
		zhuji, // 主辦主計
		chuna, // 出納
		renshi, // 人事室
		renshiphone, // 人事室聯絡電話
		bankprefix, // 9227 台灣銀行 本校虛擬帳號代號。
		bankname, // 銀行名稱
		bankhuming, // 銀行戶名
		manager_ip, // 管理者可以進入的 ip 範圍
		allowed_ip, // 允許進入本系統的 ip 範圍
	}

	@AppConfigField(name = FIELD.id)
	private int id = 0;
	@AppConfigField(name = FIELD.title)
	private String title = "請設定系統標題";
	@AppConfigField(name = FIELD.schoolname)
	private String schoolname = "請設定學校名稱";
	@AppConfigField(name = FIELD.principal)
	private String principal = "請設定校長姓名";
	@AppConfigField(name = FIELD.zhuji)
	private String zhuji = "<<主計>>";
	@AppConfigField(name = FIELD.chuna)
	private String chuna = "<<出納>>";
	@AppConfigField(name = FIELD.renshi)
	private String renshi = "<<人事>>";
	@AppConfigField(name = FIELD.renshiphone)
	private String renshiphone = "<<人事室連絡電話>>";
	@AppConfigField(name = FIELD.bankprefix)
	private String bankprefix = "虛擬帳戶收款代碼";
	@AppConfigField(name = FIELD.bankname)
	private String bankname = "台灣銀行";
	@AppConfigField(name = FIELD.bankhuming)
	private String bankhuming = "";
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	@AppConfigField(name = FIELD.manager_ip)
	private TreeSet<IpAddress> manager_ip = new TreeSet<IpAddress>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5354900944796241866L;
		{
			new IpAddress("0.0.0.0/0");
		}
	};

	@AppConfigField(name = FIELD.allowed_ip)
	private TreeSet<IpAddress> allowed_ip = new TreeSet<IpAddress>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4870963168373183072L;
		{
			new IpAddress("0.0.0.0/0");
		}
	};
	// ////////////////////////////////////////////////////////////////////////////////////////////////////

	ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null) {
			return;
		}
		this.title = title;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		if (schoolname == null) {
			return;
		}
		this.schoolname = schoolname;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		if (principal == null) {
			return;
		}
		this.principal = principal;
	}

	public String getZhuji() {
		return zhuji;
	}

	public void setZhuji(String zhuji) {
		if (zhuji == null) {
			return;
		}
		this.zhuji = zhuji;
	}

	public String getChuna() {
		return chuna;
	}

	public void setChuna(String chuna) {
		if (chuna == null) {
			return;
		}
		this.chuna = chuna;
	}

	public String getRenshi() {
		return renshi;
	}

	public void setRenshi(String renshi) {
		if (renshi == null) {
			return;
		}
		this.renshi = renshi;
	}

	public String getRenshiphone() {
		return renshiphone;
	}

	public void setRenshiphone(String renshiphone) {
		if (renshiphone == null) {
			return;
		}
		this.renshiphone = renshiphone;
	}

	public String getBankprefix() {
		return bankprefix;
	}

	public void setBankprefix(String bankprefix) {
		if (bankprefix == null) {
			return;
		}
		this.bankprefix = bankprefix;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		if (bankname == null) {
			return;
		}
		this.bankname = bankname;
	}

	public String getBankhuming() {
		return bankhuming;
	}

	public void setBankhuming(String bankhuming) {
		if (bankhuming == null) {
			return;
		}
		this.bankhuming = bankhuming;
	}

	public TreeSet<IpAddress> getManager_ip() {
		return manager_ip;
	}

	public void setManager_ip(TreeSet<IpAddress> manager_ip) {
		if (manager_ip == null) {
			return;
		}
		this.manager_ip = manager_ip;
	}

	public void setManager_ip(String manager_ip) {
		if (manager_ip == null || "".equals(manager_ip.trim()) || "[]".equals(manager_ip.trim())) {
			this.setManager_ip(StringTool.String2IpAddressTreeSet("[]"));
			return;
		}
		this.setManager_ip(StringTool.String2IpAddressTreeSet(manager_ip));
	}

	public TreeSet<IpAddress> getAllowed_ip() {
		return allowed_ip;
	}

	public void setAllowed_ip(TreeSet<IpAddress> allowed_ip) {
		if (allowed_ip == null) {
			return;
		}
		this.allowed_ip = allowed_ip;
	}

	public void setAllowed_ip(String allowed_ip) {
		if (allowed_ip == null || "".equals(allowed_ip.trim()) || "[]".equals(allowed_ip.trim())) {
			this.setAllowed_ip(StringTool.String2IpAddressTreeSet("[]"));
			return;
		}
		this.setAllowed_ip(StringTool.String2IpAddressTreeSet(allowed_ip));
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
