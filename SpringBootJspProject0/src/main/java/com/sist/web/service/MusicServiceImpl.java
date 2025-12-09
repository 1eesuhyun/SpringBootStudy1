package com.sist.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.MusicDAO;
import com.sist.web.vo.MusicVO;

@Service
public class MusicServiceImpl implements MusicService{
	@Autowired
	private MusicDAO mdao;

	@Override
	public List<MusicVO> musicListData(int start, int end) {
		// TODO Auto-generated method stub
		return mdao.musicListData(start, end);
	}

	@Override
	public int musicTotalPage() {
		// TODO Auto-generated method stub
		return mdao.musicTotalPage();
	}
}
