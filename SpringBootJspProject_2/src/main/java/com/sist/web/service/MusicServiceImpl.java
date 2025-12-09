package com.sist.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.web.mapper.*;
import com.sist.web.vo.*;
@Service
public class MusicServiceImpl implements MusicService{
	@Autowired
	private MusicMapper mapper;

	@Override
	public List<MusicVO> musicListData(Map map) {
		// TODO Auto-generated method stub
		return mapper.musicListData(map);
	}

	@Override
	public int musicTotalPage() {
		// TODO Auto-generated method stub
		return mapper.musicTotalPage();
	}
}
