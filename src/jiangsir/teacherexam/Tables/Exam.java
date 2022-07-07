package jiangsir.teacherexam.Tables;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import jiangsir.teacherexam.Annotations.Persistent;
import jiangsir.teacherexam.DAOs.ApplicationDAO;
import jiangsir.teacherexam.DAOs.SubjectDAO;
import jiangsir.teacherexam.DAOs.UpfileDAO;
import jiangsir.teacherexam.Exceptions.DataException;
import org.codehaus.jackson.map.ObjectMapper;

public class Exam {

	@Persistent(name = "id")
	private Integer id = 0;
	@Persistent(name = "title")
	private String title = "";
	// @Persistent(name = "zhengshidailis")
	// private ZHENGSHIDAILI[] zhengshidailis = new ZHENGSHIDAILI[] {
	// ZHENGSHIDAILI.代理 };
	// @Persistent(name = "nianduans")
	// private NIANDUAN[] nianduans = new NIANDUAN[] { NIANDUAN.國中 };
	// @Persistent(name = "subjects")
	// private String[] subjects = new String[] { "" };
	@Persistent(name = "applybegin")
	private Timestamp applybegin = new Timestamp(System.currentTimeMillis());
	@Persistent(name = "applyend")
	private Timestamp applyend = new Timestamp(System.currentTimeMillis());
	@Persistent(name = "startline")
	private Timestamp startline = new Timestamp(System.currentTimeMillis());
	@Persistent(name = "deadline")
	private Date deadline = new Date(System.currentTimeMillis());
	@Persistent(name = "firsttest")
	private Timestamp firsttest = new Timestamp(System.currentTimeMillis());
	@Persistent(name = "secondtest")
	private Timestamp secondtest = new Timestamp(System.currentTimeMillis());
	@Persistent(name = "scoreboardbegin")
	private Timestamp scoreboardbegin = new Timestamp(System.currentTimeMillis());
	@Persistent(name = "step2scoreboardbegin")
	private Timestamp step2scoreboardbegin = new Timestamp(System.currentTimeMillis());
	@Persistent(name = "uploadform")
	private String uploadform = "";
	@Persistent(name = "examroom")
	private String examroom = "";
	@Persistent(name = "note")
	private String note = "";
	@Persistent(name = "formnote")
	private String formnote = "";
	@Persistent(name = "money")
	private Integer money = 0;
	// @Persistent(name = "seatpatterns")
	// private String[] seatpatterns = new String[] { "" }; // 要搭配 subjects 的數量
	@Persistent(name = "isactive")
	private Boolean isactive = false;
	@Persistent(name = "isexamformprintable")
	private Boolean isexamformprintable = false;

	// 是否無條件開放修改 Application Form, 若否（預設），擇報名截止後就不能修改了。
	@Persistent(name = "isapplicationalwayseditable")
	private Boolean isApplicationAlwaysEditable = false;
	@Persistent(name = "visible")
	private Integer visible = 1;

	// ========================================================================
	ObjectMapper mapper = new ObjectMapper();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null || "".equals(title.trim())) {
			throw new DataException("沒有填寫【甄試標題】。");
		}
		this.title = title.trim();
	}

	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	/**
	 * 報名開始日
	 * 
	 * @return
	 */
	public Timestamp getApplybegin() {
		return applybegin;
	}

	public void setApplybegin(Timestamp applybegin) {
		this.applybegin = applybegin;
	}

	public void setApplybegin(String applybegin) {
		try {
			this.setApplybegin(Timestamp.valueOf(applybegin));
		} catch (Exception e) {
			throw new DataException("開始報名日期設定有誤，(" + applybegin + ") 不是正確的日期格式");
		}
	}

	/**
	 * 報名截止日
	 * 
	 * @return
	 */
	public Timestamp getApplyend() {
		return applyend;
	}

	public void setApplyend(Timestamp applyend) {
		this.applyend = applyend;
	}

	public void setApplyend(String applyend) {
		try {
			this.setApplyend(Timestamp.valueOf(applyend));
		} catch (Exception e) {
			throw new DataException("報名截止日期設定有誤，(" + applyend + ") 不是正確的日期格式");
		}
	}

	/**
	 * 繳款開始日
	 * 
	 * @return
	 */
	public Timestamp getStartline() {
		return startline;
	}

	public void setStartline(Timestamp startline) {
		this.startline = startline;
	}

	public void setStartline(String startline) {
		try {
			this.setStartline(Timestamp.valueOf(startline));
		} catch (Exception e) {
			throw new DataException("繳費開始日期設定有誤，(" + startline + ") 不是正確的日期格式");
		}
	}

	/**
	 * 繳款截止日
	 * 
	 * @return
	 */
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public void setDeadline(String deadline) {
		try {
			this.setDeadline(Date.valueOf(deadline));
		} catch (Exception e) {
			throw new DataException("【繳費截止】日期設定有誤，(" + deadline + ") 不是正確的日期格式");
		}
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public void setMoney(String money) {
		if (money == null || !money.matches("[0-9]+")) {
			throw new DataException("繳費金額有誤！");
		}
		this.setMoney(Integer.parseInt(money));
	}

	// public void setSeatpatterns(String seatpatterns) {
	// this.seatpatterns = seatpatterns;
	// }
	//
	// public void setSeatpatterns(String[] seatpatterns) {
	// try {
	// this.seatpatterns = mapper.writeValueAsString(seatpatterns);
	// } catch (JsonGenerationException e) {
	// e.printStackTrace();
	// } catch (JsonMappingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public String[] getSeatpatterns() {
	// try {
	// return mapper.readValue(seatpatterns, String[].class);
	// } catch (JsonParseException e) {
	// e.printStackTrace();
	// } catch (JsonMappingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return new String[0];
	// }

	// public String[] getSeatpatterns() {
	// return seatpatterns;
	// }
	//
	// public void setSeatpatterns(String[] seatpatterns) {
	// this.seatpatterns = seatpatterns;
	// }
	//
	// public void setSeatpatterns(String seatpatterns) {
	// try {
	// this.seatpatterns = mapper.readValue(seatpatterns, String[].class);
	// } catch (JsonParseException e) {
	// e.printStackTrace();
	// } catch (JsonMappingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	public Timestamp getScoreboardbegin() {
		return scoreboardbegin;
	}

	public void setScoreboardbegin(Timestamp scoreboardbegin) {
		this.scoreboardbegin = scoreboardbegin;
	}

	public void setScoreboardbegin(String scoreboardbegin) {
		try {
			this.setScoreboardbegin(Timestamp.valueOf(scoreboardbegin));
		} catch (Exception e) {
			throw new DataException("【公佈初試成績日期】設定有誤，(" + scoreboardbegin + ") 不是正確的日期格式");
		}

	}

	public Timestamp getStep2scoreboardbegin() {
		return step2scoreboardbegin;
	}

	public void setStep2scoreboardbegin(Timestamp step2scoreboardbegin) {
		this.step2scoreboardbegin = step2scoreboardbegin;
	}

	public void setStep2scoreboardbegin(String step2scoreboardbegin) {
		try {
			this.setStep2scoreboardbegin(Timestamp.valueOf(step2scoreboardbegin));
		} catch (Exception e) {
			throw new DataException("【公佈複試成績日期】設定有誤，(" + step2scoreboardbegin + ") 不是正確的日期格式");
		}

	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public Boolean getIsexamformprintable() {
		return isexamformprintable;
	}

	public void setIsexamformprintable(Boolean isexamformprintable) {
		this.isexamformprintable = isexamformprintable;
	}

	public Timestamp getFirsttest() {
		return firsttest;
	}

	public void setFirsttest(Timestamp firsttest) {
		this.firsttest = firsttest;
	}

	public void setFirsttest(String firsttest) {
		if (firsttest == null || "".equals(firsttest)) {
			return;
		}
		try {
			this.setFirsttest(Timestamp.valueOf(firsttest));
		} catch (Exception e) {
			e.printStackTrace();
			this.setFirsttest(new Timestamp(new java.util.Date().getTime()));
		}
	}

	public String getUploadform() {
		return uploadform;
	}

	public void setUploadform(String uploadform) {
		if (uploadform == null) {
			return;
		}
		this.uploadform = uploadform.trim();
	}

	public String getExamroom() {
		return examroom;
	}

	public void setExamroom(String examroom) {
		if (examroom == null) {
			return;
		}
		this.examroom = examroom;
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

	public String getFormnote() {
		return formnote;
	}

	public void setFormnote(String formnote) {
		if (formnote == null) {
			return;
		}
		this.formnote = formnote;
	}

	public Timestamp getSecondtest() {
		return secondtest;
	}

	public void setSecondtest(Timestamp secondtest) {
		this.secondtest = secondtest;
	}

	public void setSecondtest(String secondtest) {
		if (secondtest == null || "".equals(secondtest)) {
			return;
		}

		try {
			this.setSecondtest(Timestamp.valueOf(secondtest));
		} catch (Exception e) {
			e.printStackTrace();
			this.setSecondtest(new Timestamp(new java.util.Date().getTime()));
		}
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public Boolean getIsApplicationAlwaysEditable() {
		return isApplicationAlwaysEditable;
	}

	public void setIsApplicationAlwaysEditable(Boolean isApplicationAlwaysEditable) {
		this.isApplicationAlwaysEditable = isApplicationAlwaysEditable;
	}

	public void setIsApplicationAlwaysEditable(String isApplicationAlwaysEditable) {
		this.setIsApplicationAlwaysEditable(Boolean.valueOf(isApplicationAlwaysEditable));
	}

	/**
	 * 取得這個 exam 所屬的所有 application 報名表
	 * 
	 * @return
	 */
	public ArrayList<Application> getApplications() {
		return new ApplicationDAO().getApplicationsByExamid(this.getId());
	}

	public ArrayList<Upfile> getUpfiles() {
		return new UpfileDAO().getFilesByExamid(this.getId());
	}

	public ArrayList<Subject> getSubjects() {
		ArrayList<Subject> subjects = new SubjectDAO().getSubjectsByExamid(this.getId());
		if (subjects.size() == 0) {
			subjects.add(new Subject());
		}
		return subjects;
	}

	public int getPaidCountBySubjectid(int subjectid) {
		return new ApplicationDAO().getPaidApplicationsByExamidSubjectid(this.getId(), subjectid).size();
	}

	public int getCountBySubjectid(int subjectid) {
		return new ApplicationDAO().getApplicationsByExamidSubjectid(this.getId(), subjectid).size();
	}

	/**
	 * 是否已經開始繳費
	 * 
	 * @return
	 */
	public boolean After_Startline() {
		// return startline.before(new Date(new java.util.Date().getTime()));
		return new Timestamp(System.currentTimeMillis()).after(this.getStartline());
		// return new Date(new java.util.Date().getTime()).after(startline);
	}

	/**
	 * 早於開始繳費時間
	 * 
	 * @return
	 */
	public boolean Before_Startline() {
		return new Timestamp(System.currentTimeMillis()).before(this.getStartline());
	}

	/**
	 * 是否正在報名期間?
	 * 
	 * @return
	 */
	public boolean isApplyRunning() {
		return After_Applybegin() && Before_Applyend();
	}

	/**
	 * 是否已經開始報名
	 * 
	 * @return
	 */
	public boolean After_Applybegin() {
		return new Timestamp(System.currentTimeMillis()).after(this.getApplybegin());
	}

	/**
	 * 是否報名尚未結束
	 * 
	 * @return
	 */
	public boolean Before_Applyend() {
		return new Timestamp(System.currentTimeMillis()).before(this.getApplyend());
	}

	/**
	 * 是否還不能開始報名。
	 * 
	 * @return
	 */
	public boolean Before_Applybegin() {
		return new Timestamp(System.currentTimeMillis()).before(this.getApplybegin());
	}

	/**
	 * 是否已過報名期間。
	 * 
	 * @return
	 */
	public boolean After_Applyend() {
		return new Timestamp(System.currentTimeMillis()).after(this.getApplyend());
	}

	public boolean hasUploadform() {
		if (this.getUploadform() == null || "".equals(this.getUploadform())) {
			return false;
		}
		return true;
	}

}
