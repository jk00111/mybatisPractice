package com.kos.tr.old.rboard.vo;

public class TerraRboardVO {
	
	public String rnum;
	public String bnum;
	public String mnum;
	public String mname;
	public String mdeptno;
	public String rmemo;
	public String deleteyn;
	public String insertdate;
	public String updatedate;
	
	
	
	public TerraRboardVO() {
		
	}

	public TerraRboardVO(	String rnum, String bnum, String mnum,
							String mname, String mdeptno, String rmemo,
							String deleteyn, String insertdate, String updatedate) {

		this.rnum = rnum;
		this.bnum = bnum;
		this.mnum = mnum;
		this.mname = mname;
		this.mdeptno = mdeptno;
		this.rmemo = rmemo;
		this.deleteyn = deleteyn;
		this.insertdate = insertdate;
		this.updatedate = updatedate;
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public String getBnum() {
		return bnum;
	}

	public void setBnum(String bnum) {
		this.bnum = bnum;
	}

	public String getMnum() {
		return mnum;
	}

	public void setMnum(String mnum) {
		this.mnum = mnum;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMdeptno() {
		return mdeptno;
	}

	public void setMdeptno(String mdeptno) {
		this.mdeptno = mdeptno;
	}

	public String getRmemo() {
		return rmemo;
	}

	public void setRmemo(String rmemo) {
		this.rmemo = rmemo;
	}

	public String getDeleteyn() {
		return deleteyn;
	}

	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
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
	
}
