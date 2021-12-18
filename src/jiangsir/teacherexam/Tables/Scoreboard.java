/**
 * jiangsir.teacherexam.Tables - Scoreboard.java
 * 2012/7/11 下午1:48:04
 * nknush-001
 */
package jiangsir.teacherexam.Tables;

import jiangsir.teacherexam.Annotations.Persistent;

/**
 * @author nknush-001
 * 
 */
public class Scoreboard {
	@Persistent(name = "id")
	private int id;
	@Persistent(name = "pid")
	private String pid;
	@Persistent(name = "examid")
	private String examid;
	@Persistent(name = "teach_score")
	private Double teach_score;
	@Persistent(name = "benke_score")
	private Double benke_score;
	@Persistent(name = "teach_tscore")
	private Double teach_tscore;
	@Persistent(name = "benke_tscore")
	private Double benke_tscore;
	@Persistent(name = "teacher_tscore_percent")
	private Double teach_tscore_percent;
	@Persistent(name = "benke_tscore_percent")
	private Double benke_tscore_percent;
	@Persistent(name = "total_score")
	private Double total_score;
	@Persistent(name = "result")
	private String result = "";

	public int getId() {
		return id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getExamid() {
		return examid;
	}

	public void setExamid(String examid) {
		this.examid = examid;
	}

	public Double getTeach_score() {
		return teach_score;
	}

	public void setTeach_score(String teach_score) {
		try {
			this.teach_score = Double.parseDouble(teach_score);
		} catch (NumberFormatException e) {
			this.teach_score = 0.0;
		}
	}

	public void setTeach_score(Double teach_score) {
		this.teach_score = teach_score;
	}

	public Double getBenke_score() {
		return benke_score;
	}

	public void setBenke_score(String benke_score) {
		try {
			this.benke_score = Double.parseDouble(benke_score);
		} catch (NumberFormatException e) {
			this.benke_score = 0.0;
		}
	}

	public void setBenke_score(Double benke_score) {
		this.benke_score = benke_score;
	}

	public Double getTeach_tscore() {
		return teach_tscore;
	}

	public void setTeach_tscore(String teach_tscore) {
		try {
			this.teach_tscore = Double.parseDouble(teach_tscore);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.teach_tscore = 0.0;
		}
	}

	public void setTeach_tscore(Double teach_tscore) {
		this.teach_tscore = teach_tscore;
	}

	public Double getBenke_tscore() {
		return benke_tscore;
	}

	public void setBenke_tscore(String benke_tscore) {
		try {
			this.benke_tscore = Double.parseDouble(benke_tscore);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.benke_tscore = 0.0;
		}
	}

	public void setBenke_tscore(Double benke_tscore) {
		this.benke_tscore = benke_tscore;
	}

	public Double getTeach_tscore_percent() {
		return teach_tscore_percent;
	}

	public void setTeach_tscore_percent(String teach_tscore_percent) {
		try {
			this.teach_tscore_percent = Double
					.parseDouble(teach_tscore_percent);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.setTeach_tscore_percent(0.0);
		}
	}

	public void setTeach_tscore_percent(Double teach_tscore_percent) {
		this.teach_tscore_percent = teach_tscore_percent;
	}

	public Double getBenke_tscore_percent() {
		return benke_tscore_percent;
	}

	public void setBenke_tscore_percent(String benke_tscore_percent) {
		try {
			this.benke_tscore_percent = Double
					.parseDouble(benke_tscore_percent);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.setBenke_tscore_percent(0.0);
		}
	}

	public void setBenke_tscore_percent(Double benke_tscore_percent) {
		this.benke_tscore_percent = benke_tscore_percent;
	}

	public Double getTotal_score() {
		return total_score;
	}

	public void setTotal_score(String total_score) {
		try {
			this.total_score = Double.parseDouble(total_score);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.setTotal_score(0.0);
		}
	}

	public void setTotal_score(Double total_score) {
		this.total_score = total_score;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
