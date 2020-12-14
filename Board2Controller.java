package org.jht.controller;

import org.jht.dto.BoardDTO;
import org.jht.service.Board2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("id")

@RequestMapping("/board")
public class Board2Controller {
	private static final Logger logger = LoggerFactory.getLogger(Board2Controller.class);

	@Autowired
	private Board2Service b2service;

	// GET방식 write 페이지
	@RequestMapping(value = "/write", method = RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	public void aaa() {

	}

	// POST방식 write 페이지
	@RequestMapping(value = "/writePost", method = RequestMethod.POST)
	public void bbb(BoardDTO bdto,Model model) {
		System.out.println("writePost");
		logger.info("title=" + bdto.getTitle());
		logger.info("id=" + bdto.getWriter());
		logger.info("content=" + bdto.getContent());
		b2service.boardWrite(bdto);
		model.addAttribute("result","success");
		
	}

}
