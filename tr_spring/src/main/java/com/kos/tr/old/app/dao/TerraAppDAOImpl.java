package com.kos.tr.old.app.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kos.tr.old.app.vo.AppDocVO;
import com.kos.tr.old.app.vo.AppDraftVO;
import com.kos.tr.old.app.vo.AppExpandVO;
import com.kos.tr.old.app.vo.AppVacationVO;

@Repository
public class TerraAppDAOImpl implements TerraAppDAO {

	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<AppDocVO> appSelectAll(AppDocVO advo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("appSelectAll", advo);
	}

	@Override
	public List<AppDocVO> appSelectDraft(AppDocVO advo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("appSelectDraft", advo);
	}

	@Override
	public List<AppDocVO> appSelectRef(AppDocVO advo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("appSelectRef", advo);
	}

	@Override
	public int appDraftInsert(AppDraftVO dvo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("appDraftInsert", dvo);
	}

	@Override
	public int appVacationInsert(AppVacationVO avvo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("appVacationInsert", avvo);

	}
	
	@Override
	public int appExpandInsert(AppExpandVO aevo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("appExpandInsert", aevo);
	}
	
	@Override
	public List<AppDocVO> appSelectAllDoc(AppDocVO advo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("appSelectAllDoc", advo);
	}

	@Override
	public List<AppDraftVO> appSelectContentD(AppDocVO advo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("appSelectContentD", advo);
	}

	@Override
	public int appUpdate(AppDocVO advo) {
		// TODO Auto-generated method stub
		return sqlSession.update("appUpdate", advo);
	}

	@Override
	public int appPassUpdate(AppDocVO advo) {
		// TODO Auto-generated method stub
		return sqlSession.update("appPassUpdate", advo);
	}

	@Override
	public int appProxyInsert(AppDocVO advo) {
		// TODO Auto-generated method stub
		return sqlSession.update("appProxyInsert", advo);
	}

	@Override
	public List<AppDocVO> appProxySelect(AppDocVO advo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("appProxySelect", advo);
	}

	@Override
	public int appProxyUpdate(AppDocVO advo) {
		// TODO Auto-generated method stub
		return sqlSession.update("appProxyUpdate", advo);
	}

	@Override
	public int appRejectUpdate(AppDocVO advo) {
		// TODO Auto-generated method stub
		return sqlSession.update("appRejectUpdate", advo);
	}

	@Override
	public List<AppDocVO> appSelectReject(AppDocVO advo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("appSelectReject", advo);
	}

	@Override
	public List<AppVacationVO> appSelectContentV(AppDocVO advo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("appSelectContentV", advo);
	}

	@Override
	public List<AppExpandVO> appSelectContentE(AppDocVO advo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("appSelectContentE", advo);
	}

}
