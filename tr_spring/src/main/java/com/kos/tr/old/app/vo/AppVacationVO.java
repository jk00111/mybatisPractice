package com.kos.tr.old.app.vo;

public class AppVacationVO {
	private String anum;
	private String mnum;
	private String aapp;
	private String aref;
	private String alevel;
	private String aauth;
	private String atype;
	private String insertdate;
	private String updatedate;
	private String deletedate;
	private String findate;
	private String aconfirm;

	private String vsubject;
	private String vmemo;
	private String vfile;
	private String startdate;
	private String enddate;
	
	private String mdeptno;
	private String mname;
	
	public AppVacationVO() {
		
	}

	public AppVacationVO(String anum, String mnum, String aapp, String aref, String alevel, String aauth, String atype,
			String insertdate, String updatedate, String deletedate, String findate, String aconfirm, String vsubject, String vmemo,
			String vfile, String startdate, String enddate, String mdeptno, String mname) {
	
		this.anum = anum;
		this.mnum = mnum;
		this.aapp = aapp;
		this.aref = aref;
		this.alevel = alevel;
		this.aauth = aauth;
		this.atype = atype;
		this.insertdate = insertdate;
		this.updatedate = updatedate;
		this.deletedate = deletedate;
		this.findate = findate;
		this.aconfirm = aconfirm;
		this.vsubject = vsubject;
		this.vmemo = vmemo;
		this.vfile = vfile;
		this.startdate = startdate;
		this.enddate = enddate;
		this.mdeptno = mdeptno;
		this.mname = mname;
	}

	public String getMdeptno() {
		return mdeptno;
	}

	public void setMdeptno(String mdeptno) {
		this.mdeptno = mdeptno;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getAconfirm() {
		return aconfirm;
	}

	public void setAconfirm(String aconfirm) {
		this.aconfirm = aconfirm;
	}

	public String getAnum() {
		return anum;
	}

	public void setAnum(String anum) {
		this.anum = anum;
	}

	public String getMnum() {
		return mnum;
	}

	public void setMnum(String mnum) {
		this.mnum = mnum;
	}

	public String getAapp() {
		return aapp;
	}

	public void setAapp(String aapp) {
		this.aapp = aapp;
	}

	public String getAref() {
		return aref;
	}

	public void setAref(String aref) {
		this.aref = aref;
	}

	public String getAlevel() {
		return alevel;
	}

	public void setAlevel(String alevel) {
		this.alevel = alevel;
	}

	public String getAauth() {
		return aauth;
	}

	public void setAauth(String aauth) {
		this.aauth = aauth;
	}

	public String getAtype() {
		return atype;
	}

	public void setAtype(String atype) {
		this.atype = atype;
	}

	public String getInsertdate() {
		return insertdate;
	}

	public void setInsertdate(String insertdate) {
		this.insertdate = insertdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getDeletedate() {
		return deletedate;
	}

	public void setDeletedate(String deletedate) {
		this.deletedate = deletedate;
	}

	public String getFindate() {
		return findate;
	}

	public void setFindate(String findate) {
		this.findate = findate;
	}

	public String getVsubject() {
		return vsubject;
	}

	public void setVsubject(String vsubject) {
		this.vsubject = vsubject;
	}

	public String getVmemo() {
		return vmemo;
	}

	public void setVmemo(String vmemo) {
		this.vmemo = vmemo;
	}

	public String getVfile() {
		return vfile;
	}

	public void setVfile(String vfile) {
		this.vfile = vfile;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	
}
