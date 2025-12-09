package com.sist.web.service;

import java.util.List;

import com.sist.web.vo.MusicVO;

public interface MusicService {
	public List<MusicVO> musicListData(int start,int end);
	
	public int musicTotalPage();
	
}
