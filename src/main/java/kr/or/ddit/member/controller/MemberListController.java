package kr.or.ddit.member.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.common.paging.SearchCondition;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member/memberList.do")
public class MemberListController {
   
   @Inject
   private MemberService service;
   
   @GetMapping
   public String list(
         @ModelAttribute("paging") PaginationInfo paging // 안에 simpleCondition이 들어있고 그 안에 검색조건이 다 들어있음
//         , @ModelAttribute SearchCondition simpleCondition //commandObject (필요없음)
         , @RequestParam(name="page", required = false, defaultValue = "1") int currentPage
         , Model model
         ) {
      
//      PaginationInfo paging = new PaginationInfo(3, 3);
      paging.setCurrentPage(currentPage);
//      paging.setSimpleCondition(simpleCondition);
      List<MemberVO> memberList = service.retrieveMeberList(paging);
      
      PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#submitForm");
      
      String pagingHTML = renderer.renderPagination(paging);
      
      model.addAttribute("memberList", memberList);
      model.addAttribute("pagingHTML", pagingHTML);
      
      return "member/memberList";
   }
}