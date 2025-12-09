package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;
@Mapper
@Repository
public interface MusicMapper {
	/*
	 * <select id="musicListData" resultType="com.sist.web.vo.MusicVO"
		parameterType="hashmap">
		SELECT no,title,poster,singer,num
		FROM(SELECT no,title,poster,singer,rownum as num)
		FROM(SELECT no,title,poster,singer)
		FROM genie_music ORDER BT no ASC))
		WHERE num BETWEEN #{start} AND #{end}
		</select>
		<select id="musicTotalPage" resultType="int">
			SELECT CEIL(COUNT(*).12.0) FROM genie_music
		</select>
		앞에는 resultType 뒤는 resultType
	 */
	public List<MusicVO> musicListData(Map map);
	public int musicTotalPage();
}
