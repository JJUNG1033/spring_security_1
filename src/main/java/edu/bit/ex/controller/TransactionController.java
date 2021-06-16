package edu.bit.ex.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.bit.ex.service.TxService;
import edu.bit.ex.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@AllArgsConstructor
public class TransactionController {
	
	@Autowired
	private TxService txService;
	
	
	// secrutiy 설정파일에서 테스트를위해 아래를 설정한다
	//<intercept-url pattern="/tx/**" access="permitAll" />
	// 받아온 파라미터를 num으로 변수화 시켜서 넘기겠다는 의미이다
	@GetMapping("/tx/{num}")
	public void transion(@PathVariable("num") int num) throws SQLException {
		log.info("transion()..");
		
		if(num ==1) {
			log.info("tx test1");
			txService.txTest1();
		}else if(num==2) {
			
			log.info("tx test2");
			txService.txTest2();
		}else if(num==3) {
			
			log.info("tx test3");
			txService.txTest3();
	}else if(num==4) {
		log.info("tx test4");
		txService.txTest4();
		
	}else if(num==5) {
		log.info("tx test5");
		txService.txTest5();
		
	}else if(num==6) {
		log.info("tx test6");
		txService.txTest6();
		
	}else if(num==7) {
		log.info("tx test7");
		txService.txTest7();
		
	}
	
	}
}
	
	

