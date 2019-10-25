package com.springboot.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.model.Post;
import com.springboot.demo.service.BoardService;

@RestController
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;
	
	//게시글 목록
		@RequestMapping(value="/posts", method=RequestMethod.GET)
		public ResponseEntity<List<Post>> selectAllPost() {
			logger.info("selectAllPost()");
			List<Post> postList = boardService.selectAllPost();
			ResponseEntity<List<Post>> responseEntity = new ResponseEntity<List<Post>> (postList,HttpStatus.OK);
			
			return responseEntity;
		}
		//게시글작성
		@RequestMapping(value="/post-new", method=RequestMethod.POST)
		public ResponseEntity<Post> postNew(RequestEntity<Post> request) {
			
			logger.info("postNew()");
			Post newPost = request.getBody();
			
			boardService.createPost(newPost);
			ResponseEntity<Post> response = new ResponseEntity<Post>(newPost,HttpStatus.OK);
			return response;
		}
		
	}
