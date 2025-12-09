package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.RecipeVO;
/*
 *  사용자 요청 === Controller === Mapper
 *               ----------       | 에러 발생
 *               영향력이 있다
 *               => 결합성이 강하다(의존성이 강한 프로그램)
 *   사용자 요청 === Controller == Service == Mapper
 *       |            |         -------
 *       --------------
 */
public interface RecipeService {
	public List<RecipeVO> recipeListData(Map map);
	public int recipeTotalPage();
	public List<RecipeVO> recipeTop10();
}
