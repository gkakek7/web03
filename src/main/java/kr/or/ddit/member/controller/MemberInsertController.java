package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Insert;
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
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원 가입 처리 1. 가입 양식 제공 (GET) 2. 양식을 통해 입력 및 전송된 데이터 처리
 *
 */
@Slf4j
@Controller
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController {
	@Inject
	private MemberService service;
	
	@ModelAttribute("member")
	public MemberVO getMemberVO() {
		return new MemberVO();
	}
	
	@GetMapping
	public String doGet() {
		return "member/memberForm";
	}

	@PostMapping
	public String doPost(
			@Validated(Insert.class)  @ModelAttribute("member") MemberVO member
			,Errors erros
			,Model model
			) {
//		1. 특수문자 디코딩 설정
//		2. 17개의 파라미터를 받고 -> Commant Object로 캡슐화(MemberVO) Commend Object
//		member.setMemId(req.getParameter("memId"));
//		member.setMemPass(req.getParameter("memPass"));

		// 2-1. 검증 작업 : 통과, 불통
		
		String logicalViewName = null;
		if (!erros.hasErrors()) {
//			3. 가입 로직 호출
			ServiceResult result = service.createMember(member);
//			4. 로직의 실행 결과에 따른 뷰 선택
			String message = null;
			switch (result) {
			case OK:
				logicalViewName = "redirect:/"; // Post-Redirect-Get
				break;
			case FAIL:
				logicalViewName = "member/memberForm";
			default:
				logicalViewName = "member/memberForm";
				message = "아이디 중복, 바꾸셈;";
				break;
			}
			model.addAttribute("message", message);
		} else {
			logicalViewName = "member/memberForm";
		}

		return logicalViewName;
	}

}
