package com.kos.tr.app.service;

import java.util.List;

import com.kos.tr.app.vo.AppDocVO;
import com.kos.tr.app.vo.AppDraftVO;
import com.kos.tr.app.vo.AppExpandVO;
import com.kos.tr.app.vo.AppVacationVO;

public interface TerraAppService {

	public List<AppDocVO> appSelectAll(AppDocVO advo);
	public List<AppDocVO> appSelectDraft(AppDocVO advo);
	public List<AppDocVO> appSelectRef(AppDocVO advo);
	public int appDraftInsert(AppDraftVO dvo);
	public int appVacationInsert(AppVacationVO avvo);
	public int appExpandInsert(AppExpandVO aevo);
	public List<AppDocVO> appSelectAllDoc(AppDocVO advo);
	public List<AppDraftVO> appSelectContentD(AppDocVO advo);
	public List<AppDocVO> appSelectReject(AppDocVO advo);
	public int appUpdate(AppDocVO advo);
	public int appPassUpdate(AppDocVO advo);
	public int appProxyInsert(AppDocVO advo);
	public List<AppDocVO> appProxySelect(AppDocVO advo);
	public int appProxyUpdate(AppDocVO advo);
	public int appRejectUpdate(AppDocVO advo);
	public List<AppVacationVO> appSelectContentV(AppDocVO advo);
	public List<AppExpandVO> appSelectContentE(AppDocVO advo);
}
