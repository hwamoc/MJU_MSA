package com.springboot.demo.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.model.Post;
import com.springboot.demo.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(tags = { "1. boardservice" })
@RestController
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
  
	@Autowired
	private BoardService boardService;
	
	//게시글 목록
		@ApiOperation(value = "게시글 조회", notes = "모든 게시글을 조회한다")
		@RequestMapping(value="/posts", method=RequestMethod.GET)
		public ResponseEntity<List<Post>> selectAllPost() {
			logger.info("selectAllPost()");
			List<Post> postList = boardService.selectAllPost();
			ResponseEntity<List<Post>> responseEntity = new ResponseEntity<List<Post>> (postList,HttpStatus.OK);
			
			return responseEntity;
		}
		//게시글작성
		@ApiOperation(value = "게시글 작성", notes = "게시글을 작성한다")
		@RequestMapping(value="/post", method=RequestMethod.POST)
		public ResponseEntity<Post> postNew(
				@ApiParam @RequestParam("post_title") String post_title,
				@ApiParam @RequestParam("post_user_id") String post_user_id,
				@ApiParam @RequestParam("post_content")String post_content) {
				Post newPost = new Post();
				newPost.setPost_title(post_title);
				newPost.setPost_user_id(post_user_id);
				newPost.setPost_content(post_content);
			
			logger.info("postNew()");
			boardService.createPost(newPost);
			ResponseEntity<Post> response = new ResponseEntity<Post>(newPost,HttpStatus.OK);
			return response;
		}
		//게시글 자세히보기
		@ApiOperation(value = "게시글 자세히보기", notes = "선택한 게시글을 조회한다")
		@RequestMapping(value="/posts/{post_id}", method=RequestMethod.GET)
		public ResponseEntity<Post> selectOnePost(@PathVariable("post_id") Integer id) {
			logger.info("selectOnePost()");
			
			Post postOne = boardService.selectOneboard(id);
			ResponseEntity<Post> responseEntity = new ResponseEntity<Post> (postOne,HttpStatus.OK);
			return responseEntity;
		}
		//게시글 수정
		@RequestMapping(value="/posts/{post_id}", method=RequestMethod.PUT)
		public ResponseEntity<Post> postUpdate(@PathVariable("post_id") Integer id,@RequestBody Post postUpdate) {
			logger.info("postUpdate()");
			
			postUpdate.setPost_id(id);
			boardService.postUpdate(postUpdate);
			
			ResponseEntity<Post> response = new ResponseEntity<Post> (HttpStatus.OK);
			return response;
		}
		//게시글 삭제
		@RequestMapping(value="/posts/{post_id}", method=RequestMethod.DELETE)
		public void postDelete(@PathVariable("post_id") Integer id) {
			logger.info("postDelete()");
			 boardService.deletePost(id);
		}
	}
