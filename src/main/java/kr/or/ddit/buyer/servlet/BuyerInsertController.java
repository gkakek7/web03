package kr.or.ddit.buyer.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.others.advice.OthersControllerAdvice;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/buyer/buyerInsert.do")
public class BuyerInsertController{
	@Inject
	private BuyerService service;
	@GetMapping
	public String doGet(@ModelAttribute("buyer")BuyerVO buyer) {
		
		return "buyer/buyerForm";
	}
	@PostMapping
	public String doPost(@Validated(InsertGroup.class) @ModelAttribute("buyer") BuyerVO buyer, Errors errors) {
		log.info("errors : {}",errors);
		String logicalViewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createBuyer(buyer);
			switch(result) {
			case OK:
				logicalViewName = "redirect:/buyer/buyerView.do?what="+buyer.getBuyerId(); // Post-Redirect-Get
				break;
			default:
				logicalViewName = "buyer/buyerForm";
			}
		}else {
			logicalViewName = "buyer/buyerForm";
		}
		
		return logicalViewName;
	} 
}
