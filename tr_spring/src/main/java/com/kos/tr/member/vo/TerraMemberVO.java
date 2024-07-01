package com.kos.tr.member.vo;

public class TerraMemberVO {
	
	private String mnum;
	private String mname;
	private String mid;
	private String mpw;
	private String mzipcode;
	private String mroadaddr;
	private String mroaddetail;
	private String mjibun;
	private String mhp;
	private String memail;
	private String mgender;
	private String mbirth;
	private String mdeptno;
	private String mgrno;
	private String mduty;
	private String mposition;
	private String mjob;
	private String mphoto;
	private String mholiday;
	private String hiredate;
	private String msign;
	private String mpassyn;
	private String mcuryn;
	private String mtel;
	private String insertdate;
	private String updatedate;
	private String mproxy;
	
	private String pageSize;
	private String groupSize;
	private String curPage;
	private String totalCount;
	
	// 생성자
	public TerraMemberVO() {
		// TODO Auto-generated constructor stub
	}

	public TerraMemberVO(String mnum, String mname, String mid, String mpw, String mzipcode, String mroadaddr,
			String mroaddetail, String mjibun, String mhp, String memail, String mgender, String mbirth,
			String mdeptno, String mgrno, String mduty, String mposition, String mjob, String mphoto, String mholiday,
			String hiredate, String msign, String mpassyn, String mcuryn, String mtel, String insertdate, String updatedate, String mproxy,
			String pageSize, String groupSize, String curPage, String totalCount) {
		
		this.mnum = mnum;
		this.mname = mname;
		this.mid = mid;
		this.mpw = mpw;
		this.mzipcode = mzipcode;
		this.mroadaddr = mroadaddr;
		this.mroaddetail = mroaddetail;
		this.mjibun = mjibun;
		this.mhp = mhp;
		this.memail = memail;
		this.mgender = mgender;
		this.mbirth = mbirth;
		this.mdeptno = mdeptno;
		this.mgrno = mgrno;
		this.mduty = mduty;
		this.mposition = mposition;
		this.mjob = mjob;
		this.mphoto = mphoto;
		this.mholiday = mholiday;
		this.hiredate = hiredate;
		this.msign = msign;
		this.mpassyn = mpassyn;
		this.mcuryn = mcuryn;
		this.mtel = mtel;
		this.insertdate = insertdate;
		this.updatedate = updatedate;
		this.mproxy = mproxy;
		this.pageSize = pageSize;
		this.groupSize = groupSize;
		this.curPage = curPage;
		this.totalCount = totalCount;
		
	}
	
	

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(String groupSize) {
		this.groupSize = groupSize;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getMcuryn() {
		return mcuryn;
	}

	public void setMcuryn(String mcuryn) {
		this.mcuryn = mcuryn;
	}

	public String getMproxy() {
		return mproxy;
	}

	public void setMproxy(String mproxy) {
		this.mproxy = mproxy;
	}

	public String getMnum() {
		return mnum;
	}

	public String getMname() {
		return mname;
	}

	public String getMid() {
		return mid;
	}

	public String getMpw() {
		return mpw;
	}

	public String getMzipcode() {
		return mzipcode;
	}

	public String getMroadaddr() {
		return mroadaddr;
	}

	public String getMroaddetail() {
		return mroaddetail;
	}

	public String getMjibun() {
		return mjibun;
	}

	public String getMhp() {
		return mhp;
	}

	public String getMemail() {
		return memail;
	}

	public String getMgender() {
		return mgender;
	}

	public String getMbirth() {
		return mbirth;
	}

	public String getMdeptno() {
		return mdeptno;
	}

	public String getMgrno() {
		return mgrno;
	}

	public String getMduty() {
		return mduty;
	}

	public String getMposition() {
		return mposition;
	}

	public String getMjob() {
		return mjob;
	}

	public String getMphoto() {
		return mphoto;
	}

	public String getMholiday() {
		return mholiday;
	}

	public String getHiredate() {
		return hiredate;
	}

	public String getMsign() {
		return msign;
	}

	public String getMpassyn() {
		return mpassyn;
	}

	public String getMtel() {
		return mtel;
	}

	public String getInsertdate() {
		return insertdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setMnum(String mnum) {
		this.mnum = mnum;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public void setMzipcode(String mzipcode) {
		this.mzipcode = mzipcode;
	}

	public void setMroadaddr(String mroadaddr) {
		this.mroadaddr = mroadaddr;
	}

	public void setMroaddetail(String mroaddetail) {
		this.mroaddetail = mroaddetail;
	}

	public void setMjibun(String mjibun) {
		this.mjibun = mjibun;
	}

	public void setMhp(String mhp) {
		this.mhp = mhp;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	public void setMgender(String mgender) {
		this.mgender = mgender;
	}

	public void setMbirth(String mbirth) {
		this.mbirth = mbirth;
	}

	public void setMdeptno(String mdeptno) {
		this.mdeptno = mdeptno;
	}

	public void setMgrno(String mgrno) {
		this.mgrno = mgrno;
	}

	public void setMduty(String mduty) {
		this.mduty = mduty;
	}

	public void setMposition(String mposition) {
		this.mposition = mposition;
	}

	public void setMjob(String mjob) {
		this.mjob = mjob;
	}

	public void setMphoto(String mphoto) {
		this.mphoto = mphoto;
	}

	public void setMholiday(String mholiday) {
		this.mholiday = mholiday;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public void setMsign(String msign) {
		this.msign = msign;
	}

	public void setMpassyn(String mpassyn) {
		this.mpassyn = mpassyn;
	}

	public void setMtel(String mtel) {
		this.mtel = mtel;
	}

	public void setInsertdate(String insertdate) {
		this.insertdate = insertdate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	@Override
	public String toString() {
		return "TerraMemberVO [mnum=" + mnum + ", mname=" + mname + ", mid=" + mid + ", mpw=" + mpw + ", mzipcode="
				+ mzipcode + ", mroadaddr=" + mroadaddr + ", mroaddetail=" + mroaddetail + ", mjibun=" + mjibun
				+ ", mhp=" + mhp + ", memail=" + memail + ", mgender=" + mgender + ", mbirth=" + mbirth + ", mdeptno="
				+ mdeptno + ", mgrno=" + mgrno + ", mduty=" + mduty + ", mposition=" + mposition + ", mjob=" + mjob
				+ ", mphoto=" + mphoto + ", mholiday=" + mholiday + ", hiredate=" + hiredate + ", msign=" + msign
				+ ", mpassyn=" + mpassyn + ", mtel=" + mtel + ", insertdate=" + insertdate + ", updatedate="
				+ updatedate + "]";
	}
	
	
}// end of class