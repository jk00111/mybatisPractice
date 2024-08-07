package com.kos.tr.old.member.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kos.tr.old.common.ChabunUtil;
import com.kos.tr.old.common.CommonUtils;
import com.kos.tr.old.common.FileUploadUtil;
import com.kos.tr.old.common.T_Session;
import com.kos.tr.old.common.chabun.service.TerraChabunService;
import com.kos.tr.old.member.service.TerraMemberService;
import com.kos.tr.old.member.vo.TerraMemberVO;

@Controller
public class TerraMemberController {
	Logger logger = LogManager.getLogger(TerraMemberController.class);

	// 필드 오토 와이어드
	@Autowired(required=false)
	private TerraChabunService terraChabunService;
	
	// 필드 오토 와이어드
	@Autowired(required=false)
	private TerraMemberService terraMemberService;
	
	@Autowired(required=false)
	private TerraMemberController terraMemberController;
	
	// 회원 입력 폼
	@GetMapping("tMemInsert")
	public String tMemInsert() {
		logger.info("tMemInsert() 함수 진입 >>> : ");
		
		return "terraMember/memInsertForm";
	}
	
	// 회원 등록
	@PostMapping("tMemInsertForm")
	public String tMemInsertForm(HttpServletRequest req) {
		logger.info("tMemInsertForm 함수 진입 >> :");
		
		// 채번구하기
		String mnum = ChabunUtil.getMemberChabun("D", terraChabunService.getTerraMemberChabun().getMnum());
		logger.info("kosMemberInsert 함수 mnum >>> : " + mnum);
		
		// 이미지 업로드
		FileUploadUtil fu = new FileUploadUtil(	 CommonUtils.TMEM_IMG_UPLOAD_PATH
                ,CommonUtils.TMEM_IMG_FILE_SIZE
                ,CommonUtils.TMEM_EN_CODE);
		
		// 이미지 파일 원본 사이즈
		boolean bool = fu.imgfileUpload(req);
		logger.info("terraMemberInsert bool >>> : " + bool);
		
		TerraMemberVO tmvo = null;
		tmvo = new TerraMemberVO();
		
		// 채번
		tmvo.setMnum(mnum);
		
		// 이름
		tmvo.setMname(fu.getParameter("mname"));
		
		// 아이디 아이디 중복체크 넣기
		tmvo.setMid(fu.getParameter("mid"));
		
		// 비밀번호
		tmvo.setMpw(fu.getParameter("mpw"));
		
		// 도로명 주소, 지번 주소
		tmvo.setMzipcode(fu.getParameter("mzipcode"));
		tmvo.setMroadaddr(fu.getParameter("mroadaddr"));
		tmvo.setMroaddetail(fu.getParameter("mroaddetail"));
		tmvo.setMjibun(fu.getParameter("mjibun"));
		
		// 전화번호
		String mhp = fu.getParameter("mhp");
		String mhp1 = fu.getParameter("mhp1");
		String mhp2 = fu.getParameter("mhp2");
		tmvo.setMhp(mhp.concat(mhp1).concat(mhp2));
		
		// 이메일
		String memail = fu.getParameter("memail");
		String memail1 = fu.getParameter("memail1");		
		tmvo.setMemail(memail.concat("@").concat(memail1));		
		
		// 성별
		tmvo.setMgender(fu.getParameter("mgender"));
		
		// 생년월일
		tmvo.setMbirth(fu.getParameter("mbirth"));
		
		// 사진
		tmvo.setMphoto(fu.getFileName("mphoto"));
		
		// 부서번호
		String mdeptno = "";
		String deptno[] = fu.getParameterValues("mdeptno");
		if (deptno.length > 0) {
			for (int i=0; i < deptno.length; i++) {
				mdeptno += deptno[i];
			}	
		}		
		tmvo.setMdeptno(mdeptno);
		
		// 상급자번호
		tmvo.setMgrno(fu.getParameter("mgrno"));
		
		// 직위
		String mposition = "";
		String position[] = fu.getParameterValues("mposition");
		if (position.length > 0) {
			for (int i=0; i < position.length; i++) {
				mposition += position[i];
			}	
		}		
		tmvo.setMposition(mposition);
		
		// 직무
		String mjob = "";
		String job[] = fu.getParameterValues("mjob");
		if (job.length > 0) {
			for (int i=0; i < job.length; i++) {
				mjob += job[i];
			}	
		}		
		tmvo.setMjob(mjob);
		
		// 입사일
		String hiredate = fu.getParameter("hiredate");
		tmvo.setHiredate(fu.getParameter("hiredate"));
		logger.info("hiredate >>> :" + hiredate );
		
		// 사내번호
		String mtel = fu.getParameter("mtel");
		String mtel1 = fu.getParameter("mtel1");
		String mtel2 = fu.getParameter("mtel2");
		tmvo.setMtel(mtel.concat(mtel1).concat(mtel2));
		
		// 서비스 호출
		int nCnt = terraMemberService.tMemInsert(tmvo);
				
		if (nCnt > 0) {
			logger.info("terraMemberInsert 함수 nCnt >>> : " + nCnt);
			return "terraMember/memInsert";
		}
		return "terraMember/memInsertForm";
	}
	
	// 회원 목록
	@GetMapping("tMemSelectAll")
	public String tMemSelectAll(TerraMemberVO tmvo, Model model, HttpServletRequest req) {
		logger.info("tMemSelectAll 함수 진입 >>> : ");
		
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraMemberController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		int pageSize 	= CommonUtils.BOARD_PAGE_SIZE;
		int groupSize 	= CommonUtils.BOARD_GROUP_SIZE;
		int curPage 	= CommonUtils.BOARD_CUR_PAGE;
		int totalCount 	= CommonUtils.BOARD_TOTAL_COUNT;
		
		if (tmvo.getCurPage() != null) {
			curPage = Integer.parseInt(tmvo.getCurPage());
		}
		
		tmvo.setPageSize(String.valueOf(pageSize));
		tmvo.setGroupSize(String.valueOf(groupSize));
		tmvo.setCurPage(String.valueOf(curPage));
		tmvo.setTotalCount(String.valueOf(totalCount));
		
		logger.info("tBoardSelectAll kbvo.getPageSize() >>> : " 	+ tmvo.getPageSize());
		logger.info("tBoardSelectAll kbvo.getGroupSize() >>> : " 	+ tmvo.getGroupSize());
		logger.info("tBoardSelectAll kbvo.getCurPage() >>> : " 		+ tmvo.getCurPage());
		logger.info("tBoardSelectAll kbvo.getTotalCount() >>> : " 	+ tmvo.getTotalCount());
		
		List<TerraMemberVO> listAll = terraMemberService.tMemSelectAll(tmvo);
		if (listAll.size() > 0) {
			logger.info("tMemSelectAll listAll.size() >>> : " + listAll.size());
			
			model.addAttribute("listAll", listAll);
			model.addAttribute("pagingTMVO", tmvo);
			// 검색 조회 후 리프레쉬된 페이지에 설정 유지용 데이터
			model.addAttribute("search_tmvo", tmvo);
			
			return "terraMember/memSelectAll";
		}
		
		return "terraMember/memInsertForm";
	}
	
	// 아이디 체크
	@GetMapping("tMemIdcheck")
	@ResponseBody
	public Object tMemIdcheck(TerraMemberVO tmvo) {
		logger.info("terraMemberService 함수 진입 >>> :");		
		logger.info("terraMemberService tvo.getMid()() >>> : " + tmvo.getMid());			
		
		List<TerraMemberVO> list = terraMemberService.tMemIdcheck(tmvo);			
		logger.info("tMemIdcheck list.size() >>> : " + list.size());
		
		String msg = "";		
		if (list.size() == 0) {msg = "ID_YES";}  
		else { msg = "ID_NO";}  
		
		return msg;		
	}
	
	// 회원 조회
	@PostMapping("tMemSelect")
	public String tMemSelect(TerraMemberVO tmvo, Model model) {
		logger.info("tMemSelect 함수 진입 >>> : ");
		logger.info("tMemSelect 함수 진입 kvo >>> : " + tmvo);
		logger.info("tMemSelect 함수 진입 kvo >>> : " + tmvo.getMnum());
		
		List<TerraMemberVO> listS = terraMemberService.tMemSelect(tmvo);
		if (listS.size() > 0) {
			logger.info("tMemSelect listS.size() >>> : " + listS.size());
			
			model.addAttribute("listS", listS);
			return "terraMember/memSelect";
		}
		
		return "terraMember/memSelectAll";
		
	}
	
	
	// 수정 폼
	@PostMapping("tMemUpdate")
	public String tMemUpdateForm(HttpServletRequest req, TerraMemberVO tmvo) {
		
		// 이미지 업로드
		FileUploadUtil fu = new FileUploadUtil(	 CommonUtils.TMEM_IMG_UPLOAD_PATH
                ,CommonUtils.TMEM_IMG_FILE_SIZE
                ,CommonUtils.TMEM_EN_CODE);
		
		// 이미지 파일 원본 사이즈
		boolean bool = fu.imgfileUpload(req);
		logger.info("terraMemberInsert bool >>> : " + bool);
		
		
		tmvo.setMname(fu.getParameter("mname"));
		logger.info("mname >>> " + fu.getParameter("mname"));
		
		tmvo.setMid(fu.getParameter("mid"));
		logger.info("mid >>> " + fu.getParameter("mid"));
		
		// 비밀번호
		tmvo.setMpw("mpw");
		logger.info("mpw >>> " + tmvo.getMpw());
		
		// 도로명 주소, 지번 주소
		tmvo.setMzipcode(fu.getParameter("mzipcode"));
		logger.info("mzipcode >>> " + fu.getParameter("mzipcode"));
		tmvo.setMroadaddr(fu.getParameter("mroadaddr"));
		logger.info("mroadaddr >>> " + fu.getParameter("mroadaddr"));
		tmvo.setMroaddetail(fu.getParameter("mroaddetail"));
		logger.info("mroaddetail >>> " + fu.getParameter("mroaddetail"));
		tmvo.setMjibun(fu.getParameter("mjibun"));
		logger.info("mjibun >>> " + fu.getParameter("mjibun"));
		
		// 전화번호
		String mhp = fu.getParameter("mhp");
		String mhp1 = fu.getParameter("mhp1");
		String mhp2 = fu.getParameter("mhp2");
		tmvo.setMhp(mhp.concat(mhp1).concat(mhp2));
				
		// 이메일
		String memail = fu.getParameter("memail");
		String memail1 = fu.getParameter("memail1");		
		tmvo.setMemail(memail.concat("@").concat(memail1));		
				
		// 성별
		tmvo.setMgender(fu.getParameter("mgender"));
		
		// 생년월일
		tmvo.setMbirth(fu.getParameter("mbirth"));
				
		// 부서번호
		String mdeptno = "";
		String deptno[] = fu.getParameterValues("mdeptno");
		if (deptno.length > 0) {
			for (int i=0; i < deptno.length; i++) {
				mdeptno += deptno[i];
			}	
		}		
		tmvo.setMdeptno(mdeptno);
	
		// 상급자번호
		tmvo.setMgrno(fu.getParameter("mgrno"));
				
				
		// 직위
		String mposition = "";
		String position[] = fu.getParameterValues("mposition");
		if (position.length > 0) {
			for (int i=0; i < position.length; i++) {
				mposition += position[i];
			}	
		}		
		tmvo.setMposition(mposition);
				
		// 직무
		String mjob = "";
		String job[] = fu.getParameterValues("mjob");
		if (job.length > 0) {
			for (int i=0; i < job.length; i++) {
				mjob += job[i];
			}	
		}		
		tmvo.setMjob(mjob);
				
		// 사내번호
		String mtel = fu.getParameter("mtel");
		String mtel1 = fu.getParameter("mtel1");
		String mtel2 = fu.getParameter("mtel2");
		tmvo.setMtel(mtel.concat(mtel1).concat(mtel2));
				
		// 사진
		tmvo.setMphoto(fu.getFileName("mphoto"));
		logger.info("mphoto >>> " + fu.getFileName("mphoto"));
				
		//입사일 HIREDATE
		tmvo.setHiredate(fu.getParameter("hiredate"));
		logger.info("hiredate >>> " + fu.getParameter("hiredate"));
				
		// 상급자번호
		tmvo.setMholiday(fu.getParameter("mholiday"));
		logger.info("mholiday >>> : " + tmvo.getMholiday());
				
		// 전결권
		tmvo.setMpassyn(fu.getParameter("mpassyn"));
		logger.info("mpassyn >>> " + fu.getParameter("mpassyn"));
			
		// 사번
		tmvo.setMnum(fu.getParameter("mnum"));
		logger.info("mnum >>> " + fu.getParameter("mnum"));
				
		// 서비스 호출
		int nCnt = terraMemberService.tMemUpdate(tmvo);
        
	      if (nCnt == 1) {
	         logger.info("tMemUpdate 함수 nCnt >>> : " + nCnt);
	         return "terraMember/memUpdate";
	      }
	      return "";
						
	}
	// 삭제
	@GetMapping("tMemDelete")
	public String tMemDelete(TerraMemberVO tmvo) {
		logger.info("tMemDelete 함수 진입 >>> :");
		logger.info("tMemDelete 함수 진입 tmvo.getMnum() >> :" + tmvo.getMnum());

		return"";
	}
	
	// 회원 목록
	@GetMapping("tMemAddrBook")
	public String tMemAddrBook(TerraMemberVO tmvo, Model model, HttpServletRequest req) {
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraMemberController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);

		int pageSize 	= CommonUtils.BOARD_PAGE_SIZE;
		int groupSize 	= CommonUtils.BOARD_GROUP_SIZE;
		int curPage 	= CommonUtils.BOARD_CUR_PAGE;
		int totalCount 	= CommonUtils.BOARD_TOTAL_COUNT;
		
		if (tmvo.getCurPage() != null) {
			curPage = Integer.parseInt(tmvo.getCurPage());
		}
		
		tmvo.setPageSize(String.valueOf(pageSize));
		tmvo.setGroupSize(String.valueOf(groupSize));
		tmvo.setCurPage(String.valueOf(curPage));
		tmvo.setTotalCount(String.valueOf(totalCount));
		
		logger.info("tBoardSelectAll kbvo.getPageSize() >>> : " 	+ tmvo.getPageSize());
		logger.info("tBoardSelectAll kbvo.getGroupSize() >>> : " 	+ tmvo.getGroupSize());
		logger.info("tBoardSelectAll kbvo.getCurPage() >>> : " 		+ tmvo.getCurPage());
		logger.info("tBoardSelectAll kbvo.getTotalCount() >>> : " 	+ tmvo.getTotalCount());
		
		List<TerraMemberVO> listAll = terraMemberService.tMemSelectAll(tmvo);
		if (listAll.size() > 0) {
			logger.info("tMemSelectAll listAll.size() >>> : " + listAll.size());
			
			model.addAttribute("listAll", listAll);
			model.addAttribute("pagingTMVO", tmvo);
			
			return "terraMember/memAddBook";
		}
		
		return "terraMember/memInsertForm";
	}
	
	@GetMapping(value = "pwCheck")
    @ResponseBody
    public String pwCheck(TerraMemberVO kvo) {
       
//       int nCnt = 0; // 비밀번호 길이
//       int nCnt_n = 0; // 숫자
//       int nCnt_A = 0; // 대문자
//       int nCnt_a = 0; // 소문자
//       int nCnt_x = 0; // 특수문자

       boolean a1 = false;
       boolean a2 = false;
       boolean a3 = false;
       boolean a4 = false;
       String returnpw = "";
       String pw = kvo.getMpw();
       
       char pwc[] = pw.toCharArray();
       
       for(int i=0; i<pwc.length; i++) {
          
             
             // 대문자
             if(0x41 <= pwc[i] && pwc[i] <= 0x5A) {
             
                a1 = true;
                

             }
             // 소문자
             if(0x61 <= pwc[i] && pwc[i] <= 0x7A) {
                
                a2 = true;
                

             }
             // 특수문자
             if(0x21 <= pwc[i] && pwc[i] <= 0x27 || pwc[i] == 0x40) {
                
                a3 = true;
       
                
             }
             if(pwc.length >= 8) {
                
                a4 = true;
             }
       }
          
       return String.valueOf(a1) + "+" + String.valueOf(a2) + "+" + String.valueOf(a3) + "+" + String.valueOf(a4);
    }
	
	public List<TerraMemberVO> userData(HttpServletRequest req) {
		
		List<TerraMemberVO> uList = new ArrayList<TerraMemberVO>();
		T_Session ks = T_Session.getInstance();			
		String tnum = ks.getSession(req);
		
		if (tnum == null) {
			logger.info(tnum);
			return  uList;
		}else {
		TerraMemberVO tmvo = new TerraMemberVO();
		tmvo.setMnum(tnum);
		uList = terraMemberService.tMemSelect(tmvo);
		
		return uList;
		
		}
	}
	
}// end of class
