package com.kos.tr.app.vo;

public class AppDocVO {
	
	private AppVacationVO avvo;
	private AppDraftVO advo;
	
	private String anum;
	private String mnum;
	private String mname;
	private String subject;
	private String aapp;
	private String mposition;
	private String aref;
	private String alevel;
	private String aauth;
	private String deleteyn;
	private String atype;
	private String insertdate;
	private String updatedate;
	private String deletedate;
	private String findate;
	private String aconfirm;
	
	private String pageSize;
	private String groupSize;
	private String curPage; // curPage : 현재페이지
	private String totalCount;
	
	
	public AppDocVO() {
	
	}

	public AppDocVO(AppVacationVO avvo, AppDraftVO advo, String anum, String mnum, String mname, String subject, String aapp, 
			String mposition, String aref, String alevel, String aauth, String deleteyn, String atype, String insertdate, String updatedate,
			String deletedate, String findate, String aconfirm, String pageSize, String groupSize, String curPage, String totalCount) {
	
		this.avvo = avvo;
		this.advo = advo;
		this.anum = anum;
		this.mnum = mnum;
		this.mname = mname;
		this.subject = subject;
		this.aapp = aapp;
		this.mposition = mposition;
		this.aref = aref;
		this.alevel = alevel;
		this.aauth = aauth;
		this.deleteyn = deleteyn;
		this.atype = atype;
		this.insertdate = insertdate;
		this.updatedate = updatedate;
		this.deletedate = deletedate;
		this.findate = findate;
		this.aconfirm = aconfirm;
		
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

	public String getAconfirm() {
		return aconfirm;
	}

	public void setAconfirm(String aconfirm) {
		this.aconfirm = aconfirm;
	}

	public String getMposition() {
		return mposition;
	}

	public void setMposition(String mposition) {
		this.mposition = mposition;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public AppVacationVO getAvvo() {
		return avvo;
	}


	public void setAvvo(AppVacationVO avvo) {
		this.avvo = avvo;
	}


	public AppDraftVO getAdvo() {
		return advo;
	}


	public void setAdvo(AppDraftVO advo) {
		this.advo = advo;
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

	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
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


	public String getDeleteyn() {
		return deleteyn;
	}


	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
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

}
