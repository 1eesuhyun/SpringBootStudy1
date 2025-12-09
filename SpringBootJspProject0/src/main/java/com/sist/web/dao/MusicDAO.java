package com.sist.web.dao;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.web.*;
import com.sist.web.mapper.*;
import com.sist.web.vo.MusicVO;
@Repository
public class MusicDAO {
	@Autowired
	private MusicMapper mapper;
	/*
	 * @Select("SELECT no,title,poster,singer,num "
			+ "  FROM(SELECT no,title,poster,singer,rownum as num "
			+ "  FROM(SELECT no,title,poster,singer "
			+ "  FROM genie_music ORDER BT no ASC)) "
			+ "  WHERE num BETWEEN #{start} AND #{end}")
	public List<MusicVO> musicListData(@Param("start")int start,@Param("end")int end);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM genie_music")
	public int musicTotalPage();
	 */
	public List<MusicVO> musicListData(int start,int end)
	{
		return mapper.musicListData(start, end);
	}
	public int musicTotalPage()
	{
		return mapper.musicTotalPage();
	}
}
