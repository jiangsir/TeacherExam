package jiangsir.teacherexam.Tables;

import java.sql.Timestamp;
import java.util.Date;

import jiangsir.teacherexam.Annotations.Persistent;
import org.codehaus.jackson.map.ObjectMapper;

public class Bankdata {
	@Persistent(name = "id")
	private Integer id = 0;
	@Persistent(name = "bankname")
	private BANK bankname = BANK.BOT;
	@Persistent(name = "bankaccount")
	private String bankaccount = "";
	@Persistent(name = "filename")
	private String filename = "";
	@Persistent(name = "rowdata")
	private String rowdata = "";
	@Persistent(name = "receivetime")
	private Timestamp receivetime = new Timestamp(new Date().getTime());

	public static class Bot {
		public static enum FIELDS {
			System("系統保留"), //
			TrnDt("交易日期"), //
			TrnTime("繳款時間"), //
			RCPTId("虛擬帳號"), //
			CurAmt("繳費金額"), //
			Code("代收類別"), //
			UserData("備註資料"), //
			CName("收款單位名稱"), //
			PName("繳款人姓名"), //
			TrnType("繳費方式"), //
			SITDate("繳款日期"), //
			CLLBR("代收行"); //
			private String value;

			private FIELDS(String value) {
				this.value = value;
			}

			public String getValue() {
				return value;
			}

		}

		private String System = "";
		private String TrnDt = "";
		private String TrnTime = "";
		private String RCPTId = "";
		private String CurAmt = "";
		private String Code = "";
		private String UserData = "";
		private String CName = "";
		private String PName = "";
		private String TrnType = "";
		private String SITDate = "";
		private String CLLBR = "";

		public String getSystem() {
			return System;
		}

		public void setSystem(String system) {
			System = system;
		}

		public String getTrnDt() {
			return TrnDt;
		}

		public void setTrnDt(String trnDt) {
			TrnDt = trnDt;
		}

		public String getTrnTime() {
			return TrnTime;
		}

		public void setTrnTime(String trnTime) {
			TrnTime = trnTime;
		}

		public String getRCPTId() {
			return RCPTId;
		}

		public void setRCPTId(String rCPTId) {
			RCPTId = rCPTId;
		}

		public String getCurAmt() {
			return CurAmt;
		}

		public void setCurAmt(String curAmt) {
			CurAmt = curAmt;
		}

		public String getCode() {
			return Code;
		}

		public void setCode(String code) {
			Code = code;
		}

		public String getUserData() {
			return UserData;
		}

		public void setUserData(String userData) {
			UserData = userData;
		}

		public String getCName() {
			return CName;
		}

		public void setCName(String cName) {
			CName = cName;
		}

		public String getPName() {
			return PName;
		}

		public void setPName(String pName) {
			PName = pName;
		}

		public String getTrnType() {
			return TrnType;
		}

		public void setTrnType(String trnType) {
			TrnType = trnType;
		}

		public String getSITDate() {
			return SITDate;
		}

		public void setSITDate(String sITDate) {
			SITDate = sITDate;
		}

		public String getCLLBR() {
			return CLLBR;
		}

		public void setCLLBR(String cLLBR) {
			CLLBR = cLLBR;
		}

	}

	// ========================================================================
	public static enum BANK {
		BOT; // 台灣銀行
	}

	ObjectMapper mapper = new ObjectMapper();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BANK getBankname() {
		return bankname;
	}

	public void setBankname(BANK bankname) {
		this.bankname = bankname;
	}

	public void setBankname(String bankname) {
		this.setBankname(BANK.valueOf(bankname));
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getRowdata() {
		return rowdata;
	}

	public void setRowdata(String rowdata) {
		this.rowdata = rowdata;
	}

	public Bot getBot() {
		String[] fields = this.getRowdata().split(",");
		Bot bot = new Bot();
		bot.setSystem(fields[0]);
		bot.setTrnDt(fields[1]);
		bot.setTrnTime(fields[2]);
		bot.setRCPTId(fields[3]);
		bot.setCurAmt(fields[4]);
		bot.setCode(fields[5]);
		bot.setUserData(fields[6]);
		bot.setCName(fields[7]);
		bot.setPName(fields[8]);
		bot.setTrnType(fields[9]);
		bot.setSITDate(fields[10]);
		bot.setCLLBR(fields[11]);
		return bot;
	}

	public Timestamp getReceivetime() {
		return receivetime;
	}

	public void setReceivetime(Timestamp receivetime) {
		this.receivetime = receivetime;
	}

}
