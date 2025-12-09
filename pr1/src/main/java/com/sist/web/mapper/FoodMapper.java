package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;
@Repository
@Mapper
public interface FoodMapper {
	/*
	 * <select id="foodListData" resultType="com.sist.web.vo.FoodVO" parameterType="hashmap">
		 SELECT fno,name,poster,hit,rownum as num
		 FROM (SELECT fno,name,poster,hit
		 FROM menupan_food ORDER BY hit DESC)
		 WHERE num BETWEEN #{start} AND #{end}
		</select>
		<select id="foodTotalPage" resultType="int">
		 SELECT CEIL(COUNT(*)/12.0) FROM menupan_food
		</select>
	 */
	public List<FoodVO> foodListData(Map map);
	public int foodTotalPage();
}
