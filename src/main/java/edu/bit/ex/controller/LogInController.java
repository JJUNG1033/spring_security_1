package edu.bit.ex.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.bit.ex.service.LoginService;
import edu.bit.ex.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@AllArgsConstructor
public class LogInController {
	
	@Autowired
	private LoginService loginService;
	
	// 로그인
	   @PostMapping("/login")
	   public String login(UserVO userVO, HttpServletRequest request) throws Exception {

	      log.info("post login");
	      log.info(userVO);

	      // Session 처리를 위한 Session 객체 HttpServletRequest 안에 있음
	      HttpSession session = request.getSession();

	      UserVO user = loginService.selectUser(userVO);

	      log.info(user);

	      if (user == null) {
	         session.setAttribute("user", null);
	         log.info("log fail");
	      } else {
	         session.setAttribute("user", user);
	         log.info("log success");
	      }
	      return "redirect:/";
	   }

	   @GetMapping("/logout")
	   public String logout(HttpServletRequest request) throws Exception {

	      log.info("logout..");

	      // Session 처리를 위한 Session 객체 HttpServletRequest 안에 있음
	      HttpSession session = request.getSession();
	      session.invalidate();

	      return "redirect:/";
	   }
}


