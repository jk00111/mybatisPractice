package com.kos.tr.work.vo;

public class TerraWorkVO {
	
	// 근태 테이블 TR_WORK
	// 컬럼 8개
	// 근태번호, 사원번호, 시작시간, 종료시간, 근무상태, 삭제여부, 등록일, 수정일
	// WNUM, MNUM, STARTDATE, ENDDATE, WSTATE, DELETEYN, INSERTDATE, UPDATEDATE 
	private String wnum;
	private String mnum;
	private String startdate;
	private String enddate;
	private String wstate;
	private String deleteyn;
	private String insertdate;
	private String updatedate;
	
//	//데이터 넘기기용 테스트
	private String datashift;
	private String splitworknow;
	
	// constructor (1) 
	public TerraWorkVO() {
		
	} // end of TerraWorkVO()

	// constructor (2) : variable 8
	public TerraWorkVO(String wnum,   String mnum,     String startdate,  String enddate, 
					String wstate, String deleteyn, String insertdate, String updatedate) {
		this.wnum = wnum;
		this.mnum = mnum;
		this.startdate = startdate;
		this.enddate = enddate;
		this.wstate = wstate;
		this.deleteyn = deleteyn;
		this.insertdate = insertdate;
		this.updatedate = updatedate;
	} // end of TerraWorkVO constructor (2)
	
	// constructor (3) : variable 10
	public TerraWorkVO(	String wnum, 	String mnum, 	 String startdate,  String enddate, 
						String wstate, 	String deleteyn, String insertdate, String updatedate, 
						String datashift, String splitworknow) {

		this.wnum = wnum;
		this.mnum = mnum;
		this.startdate = startdate;
		this.enddate = enddate;
		this.wstate = wstate;
		this.deleteyn = deleteyn;
		this.insertdate = insertdate;
		this.updatedate = updatedate;
		this.datashift = datashift;
		this.splitworknow = splitworknow;
	}

	public String getWnum() {
		return wnum;
	}

	public void setWnum(String wnum) {
		this.wnum = wnum;
	}

	public String getMnum() {
		return mnum;
	}

	public void setMnum(String mnum) {
		this.mnum = mnum;
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

	public String getWstate() {
		return wstate;
	}

	public void setWstate(String wstate) {
		this.wstate = wstate;
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

	public String getDatashift() {
		return datashift;
	}

	public void setDatashift(String datashift) {
		this.datashift = datashift;
	}

	public String getSplitworknow() {
		return splitworknow;
	}

	public void setSplitworknow(String splitworknow) {
		this.splitworknow = splitworknow;
	}
	
} // end of TerraWorkVO
