package kr.or.ddit.buyer.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.others.dao.OthersDAO;
import kr.or.ddit.vo.BuyerVO;

/**
 * screenSize : 3, blockSize : 2 를 기준으로 페이징 처리.<membercontrollerservlet>
 * 다음과 같은 검색 상황에 대한 지원.
 * P101(제조사 분류) 이라는 분류에 해당하는 제조사중 서울(buyer add1) 지역의 제조사 중 삼성전자라는 이름(buyer name)이 포함된 제조사 정보를 검색함<prod xml>
 * selct buyer (paging)	>> condition detaile<memberlistcontroller>
 */
@Controller
@RequestMapping("/buyer/buyerList.do")
public class BuyerListControllerServlet extends HttpServlet{
	@Inject
	private OthersDAO dao;
	@Inject
	private BuyerService service;
	@GetMapping
	protected String doGet(
			Model model
			,@RequestParam Map<String,Object> detailCondition
			,@RequestParam(name="page",required = false,defaultValue = "1") int currentPage){
		PaginationInfo paging = new PaginationInfo(3,2);
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition);
		
		List<BuyerVO> buyerList = service.retrieveBuyerList(paging);
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#searchForm");
		String pagingHTML = renderer.renderPagination(paging);
		
		model.addAttribute("buyerList", buyerList);
		model.addAttribute("pagingHTML", pagingHTML);
		
		return "buyer/buyerList";
	}
}
