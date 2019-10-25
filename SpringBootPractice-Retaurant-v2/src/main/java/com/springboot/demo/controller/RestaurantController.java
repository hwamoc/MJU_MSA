package com.springboot.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.springboot.demo.entity.Restaurant;
import com.springboot.demo.model.response.CommonResult;
import com.springboot.demo.model.response.ListResult;
import com.springboot.demo.model.response.SingleResult;
import com.springboot.demo.repo.RestaurantJpaRepo;
import com.springboot.demo.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = { "1. Restaurant" })
@RequiredArgsConstructor
@RestController // 결과값을 JSON으로 출력합니다.
@RequestMapping(value = "/")
public class RestaurantController {
	private final RestaurantJpaRepo restaurantJpaRepo;
	private final ResponseService responseService; // 결과를 처리할 Service

	@ApiOperation(value = "식당 조회", notes = "모든 식당을 조회한다")
	@GetMapping(value = "/restaurants", produces = "text/json; charset=UTF-8")
	public String findAllRestaurant() {
		// 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
		ListResult<Restaurant> sampleList = responseService.getListResult(restaurantJpaRepo.findAll());
		
		Gson gson = new Gson(); // Gson 사용
	    return gson.toJson(sampleList); // json으로 변환 후, 리턴
	}
	

	@ApiOperation(value = "식당 하나 조회", notes = "res_index로 식당을 조회한다")
	@GetMapping(value = "/restaurant/{res_index}", produces = "text/json; charset=UTF-8")
	public String findUserById(
			@ApiParam(value = "식당ID", required = true) @PathVariable int res_index) {
		// 결과데이터가 단일건인경우 getBasicResult를 이용해서 결과를 출력한다.
		SingleResult<Restaurant> singleResult = responseService.getSingleResult(restaurantJpaRepo.findById(res_index).orElse(null));
		Gson gson = new Gson(); // Gson 사용
	    return gson.toJson(singleResult); // json으로 변환 후, 리턴
	}
	
	@ApiOperation(value = "식당 입력", notes = "식당을 입력한다.")
	@PostMapping(value = "/restaurant")
	public SingleResult<Restaurant> save(
			@ApiParam(value = "식당 이름", required = true) @RequestParam String res_name,
			@ApiParam(value = "식당 종류", required = true) @RequestParam String res_category,
			@ApiParam(value = "식당 별점", required = false) @RequestParam Integer res_grade,
			@ApiParam(value = "예상 대기 시간", required = true) @RequestParam Integer res_expected_minutes) throws UnsupportedEncodingException {
		Restaurant restaurant = Restaurant.builder()
				.res_name(new String(res_name.getBytes("8859_1"), "UTF-8"))
				.res_category(new String(res_category.getBytes("8859_1"), "UTF-8"))
				.res_grade(res_grade)
				.res_expected_minutes(res_expected_minutes)
				.build();
		
		return responseService.getSingleResult(restaurantJpaRepo.save(restaurant));
	}
	
	@ApiOperation(value = "식당 수정", notes = "식당 정보를 수정한다")
    @PutMapping(value = "/restaurant/{res_index}")
    public SingleResult<Restaurant> modify(
    		@ApiParam(value = "식당 이름", required = true) @RequestParam String res_name,
			@ApiParam(value = "식당 종류", required = true) @RequestParam String res_category,
			@ApiParam(value = "식당 별점", required = false) @RequestParam Integer res_grade,
			@ApiParam(value = "예상 대기 시간", required = true) @RequestParam Integer res_expected_minutes) throws UnsupportedEncodingException {
		Restaurant restaurant = Restaurant.builder()
				.res_name(new String(res_name.getBytes("8859_1"), "UTF-8"))
				.res_category(new String(res_category.getBytes("8859_1"), "UTF-8"))
				.res_grade(res_grade)
				.res_expected_minutes(res_expected_minutes)
				.build();
		return responseService.getSingleResult(restaurantJpaRepo.save(restaurant));
    }
	
	@ApiOperation(value = "식당 삭제", notes = "res_index로 식당 정보를 삭제한다")
	@DeleteMapping(value = "/restaurant/{res_index}")
	public CommonResult delete(@ApiParam(value = "식당 아이디", required = true) @PathVariable int res_index) {
		restaurantJpaRepo.deleteById(res_index);
		// 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
		return responseService.getSuccessResult();
	}
}