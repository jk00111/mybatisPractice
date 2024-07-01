package com.kos.tr.app.vo;

public class AppDraftVO {

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
	private String enddate;
	private String aconfirm;
	
	private String dsubject;
	private String dmemo;
	private String dfile;
	
	private String mdeptno;
	private String mname;
	private String findate;
	
	public AppDraftVO() {
	
	}

	public AppDraftVO(String anum, String mnum, String aapp, String aref, String alevel, String aauth, String atype,
			String insertdate, String updatedate, String deletedate, String enddate, String aconfirm, String dsubject, String dmemo,
			String dfile, String mdeptno, String mname, String findate) {
	
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
		this.enddate = enddate;
		this.aconfirm = aconfirm;
		this.dsubject = dsubject;
		this.dmemo = dmemo;
		this.dfile = dfile;
		this.mdeptno = mdeptno;
		this.mname = mname;
		this.findate = findate;
	}
	
	public String getAconfirm() {
		return aconfirm;
	}

	public void setAconfirm(String aconfirm) {
		this.aconfirm = aconfirm;
	}

	public String getFindate() {
		return findate;
	}

	public void setFindate(String findate) {
		this.findate = findate;
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

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getDsubject() {
		return dsubject;
	}

	public void setDsubject(String dsubject) {
		this.dsubject = dsubject;
	}

	public String getDmemo() {
		return dmemo;
	}

	public void setDmemo(String dmemo) {
		this.dmemo = dmemo;
	}

	public String getDfile() {
		return dfile;
	}

	public void setDfile(String dfile) {
		this.dfile = dfile;
	}
	
	
}
