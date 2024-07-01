package com.kos.tr.board.vo;

public class TerraBoardVO {
	
	private String bnum;
	private String mnum;
	private String bsubject;
	private String bmemo;
	private String bfile;
	private int bhit;
	private String deleteyn;
	private String insertdate;
	private String updatedate;
	private String mname;
	
	private String pageSize;
	private String groupSize;
	private String curPage;
	private String totalCount;
	
	
	public TerraBoardVO() {
	
	}

	public TerraBoardVO(	String bnum, String mnum, String bsubject, 
							String bmemo, String bfile, int bhit,
							String deleteyn, String insertdate, String updatedate, String mname) {
		
		this.bnum = bnum;
		this.mnum = mnum;
		this.bsubject = bsubject;
		this.bmemo = bmemo;
		this.bfile = bfile;
		this.bhit = bhit;
		this.deleteyn = deleteyn;
		this.insertdate = insertdate;
		this.updatedate = updatedate;
		this.mname = mname;
	}

	public TerraBoardVO(	String pageSize, String groupSize, String curPage, 
							String totalCount) {

		this.pageSize = pageSize;
		this.groupSize = groupSize;
		this.curPage = curPage;
		this.totalCount = totalCount;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
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

	public String getBsubject() {
		return bsubject;
	}

	public void setBsubject(String bsubject) {
		this.bsubject = bsubject;
	}

	public String getBmemo() {
		return bmemo;
	}

	public void setBmemo(String bmemo) {
		this.bmemo = bmemo;
	}

	public String getBfile() {
		return bfile;
	}

	public void setBfile(String bfile) {
		this.bfile = bfile;
	}

	public int getBhit() {
		return bhit;
	}

	public void setBhit(int bhit) {
		this.bhit = bhit;
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
	
}
