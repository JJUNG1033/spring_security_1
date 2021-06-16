package edu.bit.ex.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
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

import edu.bit.ex.service.BoardService;
import edu.bit.ex.service.LoginService;
import edu.bit.ex.vo.MemberVO;
import edu.bit.ex.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
	   @Inject
	   BoardService boardService;
	   
	   @RequestMapping("/list")
	   public String list(HttpSession session,Model model) throws Exception{
	      System.out.println("list()");
	      
	     // MemberVO member = (MemberVO) session.getAttribute("member");
	      
	      /*
	      if(member == null) {
	         System.out.println("           ");
	         return "redirect:/";
	      }*/
	      
	      model.addAttribute("list", boardService.selectBoardList());
	      return "board/list";
	   }

	
	
}


