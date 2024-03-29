package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

//POJO(PlainOldJavaObject)

@Slf4j
@Controller 
public class MemberDeleteController{
	@Inject	
	private MemberService service;
	@PostMapping("/member/memberDelete.do")
	public String deleteProcess(
			Principal princpal
			,@RequestParam("password") String password
			,RedirectAttributes redirectAttributes
			) {
		String memId = princpal.getName();
		MemberVO inputData= new MemberVO(memId,password);
		ServiceResult result = service.removeMember(inputData);
		String logicalViewName=null;
		String message=null;
		switch (result) {
		case INVALIDPASSWORD:
			message="비밀 번호 오류";
			logicalViewName = "redirect:/mypage";
			redirectAttributes.addFlashAttribute("message", message);
			break;
		case OK:
			logicalViewName = "forward:/login/logOut.do";
			break;
		default:
			message="서버 오류, 좀따 다시 탈퇴하셈.";
			logicalViewName = "redirect:/mypage";
			redirectAttributes.addFlashAttribute("message", message);
			break;	
		}
		return logicalViewName;
	}
}
