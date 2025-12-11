package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;
@Repository
@Mapper
public interface FoodMapper {
	@Select("SELECT fno,name,poster,address "
			+ "FROM menupan_food "
			+ "ORDER BY fno ASC "
			+ "OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY")
	public List<FoodVO> foodListData(int start);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM menupan_food")
	public int foodTotalPage();
	
	@Update("UPDATE menupan_food SET "
			+ "hit=hit+1 "
			+ "WHERE fno=#{fno}")
	public void HitIncrement(int fno);
	
	@Select("SELECT fno,name,poster,address,time,parking,theme,type,content,score,price,phone "
			+ "FROM menupan_food "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
}
