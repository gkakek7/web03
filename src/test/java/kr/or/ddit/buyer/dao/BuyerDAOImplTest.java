package kr.or.ddit.buyer.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import kr.or.ddit.AbstractRootContextTest;
import kr.or.ddit.vo.BuyerVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class BuyerDAOImplTest extends AbstractRootContextTest{
	@Inject
	BuyerDAO dao;
	@Test
	void test() {
		BuyerVO selectBuyer = dao.selectBuyer("P10102");
		log.info("vo{}",selectBuyer);
	}

}
