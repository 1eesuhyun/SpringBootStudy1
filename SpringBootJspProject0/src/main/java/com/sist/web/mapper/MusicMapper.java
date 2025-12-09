package com.sist.web.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.MusicVO;
@Mapper
public interface MusicMapper {
	@Select("SELECT no,title,poster,singer,num "
			+ "  FROM(SELECT no,title,poster,singer,rownum as num "
			+ "  FROM(SELECT no,title,poster,singer "
			+ "  FROM genie_music ORDER BY no ASC)) "
			+ "  WHERE num BETWEEN #{start} AND #{end}")
	public List<MusicVO> musicListData(@Param("start")int start,@Param("end")int end);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM genie_music")
	public int musicTotalPage();
}
