/**
 * jiangsir.teacherexam.Tables - Scoreboard.java
 * 2012/7/11 下午1:48:04
 * nknush-001
 */
package jiangsir.teacherexam.Tables;

import java.util.ArrayList;

import jiangsir.teacherexam.Annotations.Persistent;

/**
 * @author nknush-001
 * 
 */
public class Scorecsv {
	@Persistent(name = "id")
	private int id = 0;
	@Persistent(name = "examid")
	private int examid = 0;
	@Persistent(name = "seatid")
	private String seatid = "";
	@Persistent(name = "step1")
	private String step1 = "";
	@Persistent(name = "step2")
	private String step2 = "";
	@Persistent(name = "step3")
	private String step3 = "";
	@Persistent(name = "step4")
	private String step4 = "";

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getExamid() {
		return examid;
	}

	public void setExamid(int examid) {
		this.examid = examid;
	}

	public String getSeatid() {
		return seatid;
	}

	public void setSeatid(String seatid) {
		if (seatid == null) {
			return;
		}
		this.seatid = seatid.trim();
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<String[]> getSteps() {
		ArrayList<String[]> steps = new ArrayList<String[]>();
		steps.add(this.getStep1().split("\n"));
		steps.add(this.getStep2().split("\n"));
		return steps;
	}

	/**
	 * 一試 csv (初試)
	 * 
	 * @return
	 */
	public String getStep1() {
		return step1.trim();
	}

	public String[] getStep1Array() {
		return this.getStep1().split("\n");
	}

	public void setStep1(String step1) {
		if (step1 == null) {
			return;
		}
		this.step1 = step1.trim();
	}

	/**
	 * 二試 csv (複試)
	 * 
	 * @return
	 */
	public String getStep2() {
		return step2.trim();
	}

	public String[] getStep2Array() {
		return this.getStep2().split("\n");
	}

	public void setStep2(String step2) {
		if (step2 == null) {
			return;
		}
		this.step2 = step2.trim();
	}

	/**
	 * 三試 csv
	 * 
	 * @return
	 */
	public String getStep3() {
		return step3.trim();
	}

	public String[] getStep3Array() {
		return this.getStep3().split("\n");
	}

	public void setStep3(String step3) {
		this.step3 = step3;
	}

	/**
	 * 四試 csv
	 * 
	 * @return
	 */
	public String getStep4() {
		return step4.trim();
	}

	public String[] getStep4Array() {
		return this.getStep4().split("\n");
	}

	public void setStep4(String step4) {
		this.step4 = step4;
	}

}
