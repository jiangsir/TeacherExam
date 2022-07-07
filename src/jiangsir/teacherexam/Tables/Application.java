package jiangsir.teacherexam.Tables;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import jiangsir.teacherexam.Annotations.Persistent;
import jiangsir.teacherexam.DAOs.BankdataDAO;
import jiangsir.teacherexam.DAOs.ExamDAO;
import jiangsir.teacherexam.DAOs.PictureDAO;
import jiangsir.teacherexam.DAOs.ScorecsvDAO;
import jiangsir.teacherexam.DAOs.SubjectDAO;
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
public class Application {
	public static class Honor {
		private String reason = "";
		private String award = "";

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

		public String getAward() {
			return award;
		}

		public void setAward(String award) {
			this.award = award;
		}
	}

	@Persistent(name = "id")
	private Integer id = 0;
	@Persistent(name = "examid")
	private Integer examid = new Exam().getId();
	@Persistent(name = "seatid")
	private String seatid = "";
	@Persistent(name = "pid")
	private String pid = ""; // 身份證字號
	@Persistent(name = "bankaccount")
	private String bankaccount = ""; // 銀行 虛擬賬戶
	@Persistent(name = "name")
	private String name = ""; // 姓名
	@Persistent(name = "birthday")
	private java.sql.Date birthday = new java.sql.Date(new Date().getTime());
	@Persistent(name = "gender")
	private String gender = ""; // 性別
	@Persistent(name = "address")
	private String address = ""; // 通訊處
	@Persistent(name = "email")
	private String email = ""; // 電子郵件
	@Persistent(name = "cellphone")
	private String cellphone = ""; // 行動電話
	@Persistent(name = "teloffice")
	private String teloffice = ""; // 聯絡電話 辦公室
	@Persistent(name = "subject")
	private String subject = ""; // 已不使用。僅為資料庫舊欄位避免 log 錯誤訊息之用。
	@Persistent(name = "subjectid")
	private Integer subjectid = 0; // 報名科目
	@Persistent(name = "school")
	private String school = ""; // 現職服務學校
	@Persistent(name = "position")
	private String position = ""; // 專任、代理
	@Persistent(name = "license")
	private String license = ""; // 教師證書 科別
	@Persistent(name = "licensedate")
	private String licensedate = ""; // 教師證書
	// 發證日期
	@Persistent(name = "licenseid")
	private String licenseid = ""; // 教師證書 證書字號
	@Persistent(name = "bachelorschool")
	private String bachelorschool = ""; // 學歷 學士 校名
	@Persistent(name = "bachelormajor")
	private String bachelormajor = ""; // 學歷 學士
	@Persistent(name = "bachelorbegin")
	private String bachelorbegin = "";
	// 學歷 學士 自
	@Persistent(name = "bachelorend")
	private String bachelorend = "";
	// 學歷 學士 至
	@Persistent(name = "masterschool")
	private String masterschool = ""; // 學歷 碩士
	@Persistent(name = "mastermajor")
	private String mastermajor = ""; // 學歷 碩士
	@Persistent(name = "masterbegin")
	private String masterbegin = "";
	// 學歷 碩士 自
	@Persistent(name = "masterend")
	private String masterend = "";
	// 學歷 碩士 至
	@Persistent(name = "phdschool")
	private String phdschool = ""; // 學歷 博士
	@Persistent(name = "phdmajor")
	private String phdmajor = ""; // 學歷 博士
	@Persistent(name = "phdbegin")
	private String phdbegin = ""; // 學歷 博士 自
	@Persistent(name = "phdend")
	private String phdend = ""; // 學歷 博士 至

	@Persistent(name = "school1")
	private String school1 = ""; // 經歷 學校1
	@Persistent(name = "position1")
	private String position1 = ""; // 經歷 職務1
	@Persistent(name = "school1begin")
	private String school1begin = ""; //
	@Persistent(name = "school1end")
	private String school1end = ""; //

	@Persistent(name = "school2")
	private String school2 = ""; // 經歷 學校2
	@Persistent(name = "position2")
	private String position2 = ""; // 經歷 職務2
	@Persistent(name = "school2begin")
	private String school2begin = ""; //
	@Persistent(name = "school2end")
	private String school2end = ""; //

	@Persistent(name = "school3")
	private String school3 = ""; // 經歷 學校3
	@Persistent(name = "position3")
	private String position3 = ""; // 經歷 職務3
	@Persistent(name = "school3begin")
	private String school3begin = ""; //
	@Persistent(name = "school3end")
	private String school3end = ""; //

	@Persistent(name = "special1")
	private String special1 = ""; // 特殊教育
	@Persistent(name = "special1date")
	private String special1date = ""; //
	@Persistent(name = "special2")
	private String special2 = ""; //
	@Persistent(name = "special2date")
	private String special2date = ""; //

	@Persistent(name = "license1")
	private String license1 = ""; // 專長、檢定
	@Persistent(name = "license2")
	private String license2 = ""; //
	@Persistent(name = "license3")
	private String license3 = ""; //
	@Persistent(name = "honors")
	private Honor[] honors = { new Honor(), new Honor(), new Honor() };

	@Persistent(name = "other")
	private String other = "";
	@Persistent(name = "note")
	private String note = "";

	@Persistent(name = "pictureid")
	private Integer pictureid = 0;
	// private InputStream picture = null; // 相片
	// private String filetype = ""; // 相片格式

	@Persistent(name = "ispaid")
	private Boolean ispaid = false; // 是否完成繳款

	@Persistent(name = "score_teach")
	private Double score_teach = 0.0;
	@Persistent(name = "score_subject")
	private Double score_subject = 0.0;
	@Persistent(name = "tscore_teach")
	private Double tscore_teach = 0.0;
	@Persistent(name = "tscore_subject")
	private Double tscore_subject = 0.0;
	@Persistent(name = "percent_teach")
	private Double percent_teach = 0.0;
	@Persistent(name = "percent_subject")
	private Double percent_subject = 0.0;
	@Persistent(name = "totalscore")
	private Double totalscore = 0.0;
	@Persistent(name = "result")
	private String result = "";
	@Persistent(name = "step2totalscore")
	private Double step2totalscore = 0.0;
	@Persistent(name = "step2result")
	private String step2result = "";
	@Persistent(name = "timestamp")
	private Timestamp timestamp = new Timestamp(new Date().getTime());

	ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

	public Application() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExamid() {
		return examid;
	}

	public void setExamid(String examid) {
		if (examid != null && examid.matches("[0-9]+")) {
			this.setExamid(Integer.parseInt(examid));
		}
	}

	public void setExamid(Integer examid) {
		this.examid = examid;
	}

	public String getSeatid() {
		return seatid;
	}

	public void setSeatid(String seatid) {
		this.seatid = seatid;
	}

	public Integer getSubjectid() {
		return subjectid;
	}

	// public void setSubject(String subject) {
	// // 不做任何事。 subject 欄位已不使用
	// }

	public void setSubjectid(Integer subjectid) {
		this.subjectid = subjectid;
	}

	public void setSubjectid(String subjectid) {
		if (subjectid == null || !subjectid.matches("[0-9]+")) {

		} else {
			this.setSubjectid(Integer.parseInt(subjectid));
		}

	}

	// public String getSubject() {
	// return subject;
	// }
	//
	// public void setSubject(String subject) throws DataException {
	// if (subject == null || "".equals(subject)) {
	// throw new DataException("報名科目未填！");
	// }
	// this.subject = subject;
	// }

	public String getName() {
		return name;
	}

	public void setName(String name) throws DataException {
		if (name == null || "".equals(name)) {
			throw new DataException("姓名欄位未填！");
		}
		this.name = name;
	}

	public String getTeloffice() {
		return teloffice;
	}

	public void setTeloffice(String teloffice) throws DataException {
		if (teloffice != null) {
			this.teloffice = teloffice;
		}
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) throws DataException {
		if (gender == null || "".equals(gender)) {
			throw new DataException("性別欄位未填！");
		}
		this.gender = gender;
	}

	public java.sql.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.sql.Date birthday) throws DataException {
		if (birthday == null) {
			throw new DataException("生日欄位未填！");
		}
		Calendar birthyear = Calendar.getInstance();
		birthyear.setTime(new Date(birthday.getTime()));

		if ((Calendar.getInstance().get(Calendar.YEAR) - birthyear.get(Calendar.YEAR) < 18
				|| Calendar.getInstance().get(Calendar.YEAR) - birthyear.get(Calendar.YEAR) > 99)) {
			throw new DataException("您的生日年份填寫錯誤(" + birthday + ")！");
		}
		this.birthday = birthday;
	}

	public void setBirthday(String birthday) throws DataException {
		try {
			this.setBirthday(java.sql.Date.valueOf(birthday));
		} catch (Exception e) {
			throw new DataException(DataException.DATE_FORMAT_EXCEPTION + "(" + birthday + ")");
		}
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) throws DataException {
		if (pid == null || "".equals(pid.trim())) {
			throw new DataException("身份證字號未填！");
		}
		pid = pid.trim();
		if (!pid.matches("[a-zA-Z]\\d{9}")) {
			throw new DataException("身份證字號格式 (" + pid + ") 不正確，請重新填寫！");
		}
		this.pid = pid;
	}

	public String getBankaccount() {
		this.setBankaccount(bankaccount);
		return this.bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) throws DataException {
		if (school != null) {
			this.school = school;
		}
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) throws DataException {
		if (position != null) {
			this.position = position;
		}
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) throws DataException {
		if (cellphone == null || "".equals(cellphone)) {
			throw new DataException("行動電話號碼未填！");
		}
		this.cellphone = cellphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws DataException {
		if (address == null || "".equals(address)) {
			throw new DataException("通訊地址未填！");
		}
		this.address = address;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getLicensedate() {
		return licensedate;
	}

	public void setLicensedate(String licensedate) {
		this.licensedate = licensedate;
	}

	public String getBachelorbegin() {
		return bachelorbegin;
	}

	public void setBachelorbegin(String bachelorbegin) throws DataException {
		if (bachelorbegin == null || "".equals(bachelorbegin)) {
			throw new DataException("大學學歷自何年開始未填寫！");
		}
		this.bachelorbegin = bachelorbegin;
	}

	public String getBachelorend() {
		return bachelorend;
	}

	public void setBachelorend(String bachelorend) throws DataException {
		if (bachelorend == null || "".equals(bachelorend)) {
			throw new DataException("大學學歷何年畢業未填寫！");
		}
		this.bachelorend = bachelorend;
	}

	public String getMasterbegin() {
		return masterbegin;
	}

	public void setMasterbegin(String masterbegin) {
		this.masterbegin = masterbegin;
	}

	public String getMasterend() {
		return masterend;
	}

	public void setMasterend(String masterend) {
		this.masterend = masterend;
	}

	public String getPhdbegin() {
		return phdbegin;
	}

	public void setPhdbegin(String phdbegin) {
		this.phdbegin = phdbegin;
	}

	public String getPhdend() {
		return phdend;
	}

	public void setPhdend(String phdend) {
		this.phdend = phdend;
	}

	public String getSchool1begin() {
		return school1begin;
	}

	public void setSchool1begin(String school1begin) {
		this.school1begin = school1begin;
	}

	public String getSchool1end() {
		return school1end;
	}

	public void setSchool1end(String school1end) {
		this.school1end = school1end;
	}

	public String getSchool2begin() {
		return school2begin;
	}

	public void setSchool2begin(String school2begin) {
		this.school2begin = school2begin;
	}

	public String getSchool2end() {
		return school2end;
	}

	public void setSchool2end(String school2end) {
		this.school2end = school2end;
	}

	public String getSchool3begin() {
		return school3begin;
	}

	public void setSchool3begin(String school3begin) {
		this.school3begin = school3begin;
	}

	public String getSchool3end() {
		return school3end;
	}

	public void setSchool3end(String school3end) {
		this.school3end = school3end;
	}

	public String getSpecial1date() {
		return special1date;
	}

	public void setSpecial1date(String special1date) {
		this.special1date = special1date;
	}

	public String getSpecial2date() {
		return special2date;
	}

	public void setSpecial2date(String special2date) {
		this.special2date = special2date;
	}

	public String getLicenseid() {
		return licenseid;
	}

	public void setLicenseid(String licenseid) {
		this.licenseid = licenseid;
	}

	public String getBachelormajor() {
		return bachelormajor;
	}

	public void setBachelormajor(String bachelormajor) throws DataException {
		if (bachelormajor == null || "".equals(bachelormajor)) {
			throw new DataException("大學科系未填！");
		}
		this.bachelormajor = bachelormajor;
	}

	public String getBachelorschool() {
		return bachelorschool;
	}

	public void setBachelorschool(String bachelorschool) {
		if (bachelorschool == null || "".equals(bachelorschool)) {
			throw new DataException("大學校名未填！");
		}
		this.bachelorschool = bachelorschool;
	}

	public String getMasterschool() {
		return masterschool;
	}

	public void setMasterschool(String masterschool) {
		this.masterschool = masterschool;
	}

	public String getPhdschool() {
		return phdschool;
	}

	public void setPhdschool(String phdschool) {
		if (phdschool == null) {
			return;
		}
		this.phdschool = phdschool;
	}

	public String getMastermajor() {
		return mastermajor;
	}

	public void setMastermajor(String mastermajor) {
		this.mastermajor = mastermajor;
	}

	public String getPhdmajor() {
		return phdmajor;
	}

	public void setPhdmajor(String phdmajor) {
		this.phdmajor = phdmajor;
	}

	public String getSchool1() {
		return school1;
	}

	public void setSchool1(String school1) {
		this.school1 = school1;
	}

	public String getSchool2() {
		return school2;
	}

	public void setSchool2(String school2) {
		this.school2 = school2;
	}

	public String getSchool3() {
		return school3;
	}

	public void setSchool3(String school3) {
		this.school3 = school3;
	}

	public String getPosition1() {
		return position1;
	}

	public void setPosition1(String position1) {
		this.position1 = position1;
	}

	public String getPosition2() {
		return position2;
	}

	public void setPosition2(String position2) {
		this.position2 = position2;
	}

	public String getPosition3() {
		return position3;
	}

	public void setPosition3(String position3) {
		this.position3 = position3;
	}

	public String getSpecial1() {
		return special1;
	}

	public void setSpecial1(String special1) {
		this.special1 = special1;
	}

	public String getSpecial2() {
		return special2;
	}

	public void setSpecial2(String special2) {
		this.special2 = special2;
	}

	public String getLicense1() {
		return license1;
	}

	public void setLicense1(String license1) {
		this.license1 = license1;
	}

	public String getLicense2() {
		return license2;
	}

	public void setLicense2(String license2) {
		this.license2 = license2;
	}

	public String getLicense3() {
		return license3;
	}

	public void setLicense3(String license3) {
		this.license3 = license3;
	}

	public Honor[] getHonors() {
		return honors;
	}

	public void setHonors(Honor[] honors) {
		this.honors = honors;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		if (note == null) {
			return;
		}
		this.note = note;
	}

	/**
	 * JSON
	 * 
	 * @param honors
	 */
	public void setHonors(String honors) {
		try {
			this.setHonors(mapper.readValue(honors, Honor[].class));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws DataException {
		if (email == null || "".equals(email)) {
			throw new DataException("電子郵件未填！");
		}
		this.email = email;
	}

	// public InputStream getPicture() {
	// return picture;
	// }
	//
	// public void setPicture(String picturepath) throws DataException {
	// if (picturepath == null || "".equals(picturepath)) {
	// throw new DataException("您並未選擇相片。");
	// }
	// }
	//
	// public void setPicture(InputStream picture) throws DataException {
	// if (picture == null) {
	// throw new DataException("上傳照片有誤！(picture==null)");
	// }
	// this.picture = picture;
	// }

	// public String getFiletype() {
	// return filetype;
	// }
	//
	// public void setFiletype(String filetype) throws DataException {
	// if (filetype == null || "".equals(filetype)) {
	// throw new DataException("檔案格式未設定。");
	// }
	// String[] allows = { "image/jpeg", "image/pjpeg", "image/png",
	// "image/x-png" };
	// if (Arrays.binarySearch(allows, filetype) < 0) {
	// throw new DataException("本系統僅支援 jpg or png 檔案作為相片圖檔。(type="
	// + filetype + ")");
	// }
	// this.filetype = filetype;
	// }

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Integer getPictureid() {
		return pictureid;
	}

	public void setPictureid(Integer pictureid) {
		this.pictureid = pictureid;
	}

	public void setIspaid(Boolean ispaid) {
		this.ispaid = ispaid;
	}

	public Double getScore_teach() {
		return score_teach;
	}

	public void setScore_teach(Double score_teach) {
		this.score_teach = score_teach;
	}

	public void setScore_teach(String score_teach) {
		try {
			this.setScore_teach(Double.parseDouble(score_teach.trim()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.setScore_teach(0.0);
		}
	}

	public Double getScore_subject() {
		return score_subject;
	}

	public void setScore_subject(Double score_subject) {
		this.score_subject = score_subject;
	}

	public void setScore_subject(String score_subject) {
		try {
			this.setScore_subject(Double.parseDouble(score_subject.trim()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.setScore_subject(0.0);
		}
	}

	public Double getTscore_teach() {
		return tscore_teach;
	}

	public void setTscore_teach(Double tscore_teach) {
		this.tscore_teach = tscore_teach;
	}

	public void setTscore_teach(String tscore_teach) {
		try {
			this.setTscore_teach(Double.parseDouble(tscore_teach.trim()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.setTscore_teach(0.0);
		}
	}

	public Double getTscore_subject() {
		return tscore_subject;
	}

	public void setTscore_subject(Double tscore_subject) {
		this.tscore_subject = tscore_subject;
	}

	public void setTscore_subject(String tscore_subject) {
		try {
			this.setTscore_subject(Double.parseDouble(tscore_subject.trim()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.setTscore_subject(0.0);
		}
	}

	public Double getPercent_teach() {
		return percent_teach;
	}

	public void setPercent_teach(Double percent_teach) {
		this.percent_teach = percent_teach;
	}

	public void setPercent_teach(String percent_teach) {
		try {
			this.setPercent_teach(Double.parseDouble(percent_teach.trim()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.setPercent_teach(0.0);
		}
	}

	public Double getPercent_subject() {
		return percent_subject;
	}

	public void setPercent_subject(Double percent_subject) {
		this.percent_subject = percent_subject;
	}

	public void setPercent_subject(String percent_subject) {
		try {
			this.setPercent_subject(Double.parseDouble(percent_subject.trim()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.setPercent_subject(0.0);
		}
	}

	public Double getTotalscore() {
		return totalscore;
	}

	public void setTotalscore(Double totalscore) {
		this.totalscore = totalscore;
	}

	public void setTotalscore(String totalscore) {
		try {
			this.setTotalscore(Double.parseDouble(totalscore.trim()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.setTotalscore(0.0);
		}
	}

	public Double getStep2totalscore() {
		return step2totalscore;
	}

	public void setStep2totalscore(Double step2totalscore) {
		this.step2totalscore = step2totalscore;
	}

	public void setStep2totalscore(String step2totalscore) {
		try {
			this.setStep2totalscore(Double.parseDouble(step2totalscore.trim()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.setStep2totalscore(0.0);
		}
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		if (result == null) {
			return;
		}
		this.result = result;
	}

	public String getStep2result() {
		return step2result;
	}

	public void setStep2result(String step2result) {
		if (step2result == null) {
			return;
		}
		this.step2result = step2result;
	}

	public Boolean getIspaid() {
		return ispaid;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@JsonIgnore
	public Picture getPicture() {
		return new PictureDAO().getPictureById(this.getPictureid());
	}

	@JsonIgnore
	public Exam getExam() {
		// if (this.getExamid() == new Exam().getId()) {
		// return new ExamDAO().getActiveExam();
		// }
		return new ExamDAO().getExamById(this.getExamid());
	}

	@JsonIgnore
	public ArrayList<Bankdata> getBankdatas() {
		return new BankdataDAO().getBankdatasByBankaccount(this.getBankaccount());
	}

	/**
	 * 取得 bankaccount 所有匯款金額總和。
	 * 
	 * @return
	 */
	@JsonIgnore
	public int getBankdatasSum() {
		int sum = 0;
		for (Bankdata bankdata : this.getBankdatas()) {
			sum += Integer.parseInt(bankdata.getBot().getCurAmt());
		}
		return sum;
	}

	@JsonIgnore
	public Subject getSubject() {
		return new SubjectDAO().getSubjectById(this.getSubjectid());
	}

	public void setSubject(String subject) {
		// 因應資料庫存留的舊資料。不做任何事。避免 cataline.out 錯誤訊息。
	}

	@JsonIgnore
	public Scorecsv getScorecsv() {
		return new ScorecsvDAO().getScorecsvByExamidSeatid(this.getExamid(), this.getSeatid());
	}

}
