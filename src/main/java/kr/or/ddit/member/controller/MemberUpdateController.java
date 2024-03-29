package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateController{
	@Inject
	private MemberService service;
	// 현재 로그인한 사용자의 기본정보를 초기값으로 갖고 있는 수정 양식의 제공
	@GetMapping
	protected String doGet(Principal principal, Model model) {
		MemberVO member = service.retrieveMember(principal.getName());
		model.addAttribute("member", member);
		return "member/memberEdit";
	}
	// POST 파라미터로 수정 절차 진행
	@PostMapping
	protected String doPost(
			@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member
			,Errors errors
			,Principal principal
			,Model model) throws ServletException, IOException {
//		1. 특수문자 디코딩 설정
//		2. 17개의 파라미터를 받고 -> Commant Object로 캡슐화(MemberVO) Commend Object
//		member.setMemId(req.getParameter("memId"));
//		member.setMemPass(req.getParameter("memPass"));
		member.setMemId(principal.getName());
		// 2-1. 검증 작업 : 통과, 불통
		String logicalViewName = null;
		if (!errors.hasErrors()) {
//			3. 수정 로직 호출
			ServiceResult result = service.modifyMember(member);
//			4. 로직의 실행 결과에 따른 뷰 선택
			String message = null;
			switch (result) {
			case INVALIDPASSWORD:
				logicalViewName="member/memberEdit";
				message="인증 실패";
				break;
			case OK:
				logicalViewName="redirect:/mypage";
				break;
			default:
				logicalViewName="member/memberEdit";
				message="서버 오류";
				break;
			}
			model.addAttribute("message", message);
		} else {
			logicalViewName = "member/memberEdit";
		}
		return logicalViewName;
	}
	
	 
}
