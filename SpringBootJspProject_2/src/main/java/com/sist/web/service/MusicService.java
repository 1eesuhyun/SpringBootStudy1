package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.MusicVO;

public interface MusicService {
	public List<MusicVO> musicListData(Map map);
	public int musicTotalPage();
}
