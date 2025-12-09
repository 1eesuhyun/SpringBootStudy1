package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.FoodVO;
@Repository
@Mapper
public interface FoodMapper {
	public List<FoodVO> foodListData(Map map);
	public int foodTotalPage();
	/*
	 * <update id="foodHitIncrement" parameterType="int">
 UPDATE menupan_food SET
 hit=hit+1
 WHERE fno=#{fno}
</update>
<select id="foodDetailData" resultType="com.sist.web.vo.FoodVO" parameterType="int">
 SELECT fno,name,poster,time,address,phone,theme,parking,score,content,price,type
 FROM menupan_food
 WHERE fno=#{fno}
</select>
	 */
	public FoodVO foodDetailData(int fno);
	public void foodHitIncrement(int fno);
	/*
	 * <select id="foodTop10" resultType="com.sist.web.vo.FoodVO">
		 SELECT fno,poster,name,address,phone,rownum
		 FROM (SELECT fno,poster,name,address,phone
		 FROM menupan_food ORDER BY hit DESC)
		 WHERE rownum&lt;=10
	   </select>
	 */
	public List<FoodVO> foodTop10();
}
