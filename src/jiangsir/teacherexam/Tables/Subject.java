package jiangsir.teacherexam.Tables;

import jiangsir.teacherexam.Annotations.Persistent;

public class Subject {
	public enum ZHENGSHIDAILI {
		專任, //
		代理;//
	}

	public enum NIANDUAN {
		國小, //
		國中, //
		高中;//
	}

	@Persistent(name = "id")
	private Integer id = 0;
	@Persistent(name = "examid")
	private Integer examid = 0;
	@Persistent(name = "zhengshidaili")
	private ZHENGSHIDAILI zhengshidaili = ZHENGSHIDAILI.代理;
	@Persistent(name = "nianduan")
	private NIANDUAN nianduan = NIANDUAN.國中;
	@Persistent(name = "name")
	private String name = "";
	@Persistent(name = "seatpattern")
	private String seatpattern = "A000";

	// ========================================================================

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExamid() {
		return examid;
	}

	public void setExamid(Integer examid) {
		this.examid = examid;
	}

	public ZHENGSHIDAILI getZhengshidaili() {
		return zhengshidaili;
	}

	public void setZhengshidaili(ZHENGSHIDAILI zhengshidaili) {
		this.zhengshidaili = zhengshidaili;
	}

	public void setZhengshidaili(String zhengshidaili) {
		if (zhengshidaili != null) {
			this.setZhengshidaili(ZHENGSHIDAILI.valueOf(zhengshidaili));
		}
	}

	public NIANDUAN getNianduan() {
		return nianduan;
	}

	public void setNianduan(NIANDUAN nianduan) {
		this.nianduan = nianduan;
	}

	public void setNianduan(String nidanduan) {
		if (nianduan != null) {
			this.setNianduan(NIANDUAN.valueOf(nidanduan));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeatpattern() {
		return seatpattern;
	}

	public void setSeatpattern(String seatpattern) {
		this.seatpattern = seatpattern;
	}

	public NIANDUAN[] getNIANDUAN() {
		return NIANDUAN.values();
	}

	public ZHENGSHIDAILI[] getZHENGSHIDAILI() {
		return ZHENGSHIDAILI.values();
	}

	@Override
	public String toString() {
		return getZhengshidaili().toString() + getNianduan() + getName();
	}
}
