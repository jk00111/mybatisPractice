package com.kos.tr.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kos.tr.app.dao.TerraAppDAO;
import com.kos.tr.app.vo.AppDocVO;
import com.kos.tr.app.vo.AppDraftVO;
import com.kos.tr.app.vo.AppExpandVO;
import com.kos.tr.app.vo.AppVacationVO;

@Service
@Transactional
public class TerraAppServiceImpl implements TerraAppService {

	@Autowired(required=false)
	private TerraAppDAO terraAppDAO;
	
	@Override
	public List<AppDocVO> appSelectAll(AppDocVO advo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appSelectAll(advo);
	}

	@Override
	public List<AppDocVO> appSelectDraft(AppDocVO advo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appSelectDraft(advo);
	}

	@Override
	public List<AppDocVO> appSelectRef(AppDocVO advo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appSelectRef(advo);
	}

	@Override
	public int appDraftInsert(AppDraftVO dvo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appDraftInsert(dvo);
	}

	@Override
	public int appVacationInsert(AppVacationVO avvo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appVacationInsert(avvo);
	}
	
	@Override
	public int appExpandInsert(AppExpandVO aevo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appExpandInsert(aevo);
	}
	
	@Override
	public List<AppDocVO> appSelectAllDoc(AppDocVO advo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appSelectAllDoc(advo);
	}

	@Override
	public List<AppDraftVO> appSelectContentD(AppDocVO advo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appSelectContentD(advo);
	}

	@Override
	public int appUpdate(AppDocVO advo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appUpdate(advo);
	}

	@Override
	public int appPassUpdate(AppDocVO advo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appPassUpdate(advo);
	}

	@Override
	public int appProxyInsert(AppDocVO advo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appProxyInsert(advo);
	}

	@Override
	public List<AppDocVO> appProxySelect(AppDocVO advo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appProxySelect(advo);
	}

	@Override
	public int appProxyUpdate(AppDocVO advo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appProxyUpdate(advo);
	}

	@Override
	public int appRejectUpdate(AppDocVO advo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appRejectUpdate(advo);
	}

	@Override
	public List<AppDocVO> appSelectReject(AppDocVO advo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appSelectReject(advo);
	}

	@Override
	public List<AppVacationVO> appSelectContentV(AppDocVO advo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appSelectContentV(advo);
	}

	@Override
	public List<AppExpandVO> appSelectContentE(AppDocVO advo) {
		// TODO Auto-generated method stub
		return terraAppDAO.appSelectContentE(advo);
	}


}
