package com.sist.web.service;
import java.util.*;
import com.sist.web.mapper.*;
import com.sist.web.vo.*;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	private final BoardMapper mapper;

	@Override
	public List<BoardVO> BoardListData(int start) {
		// TODO Auto-generated method stub
		return mapper.BoardListData(start);
	}

	@Override
	public int boardTotalpage() {
		// TODO Auto-generated method stub
		return mapper.boardTotalpage();
	}

	@Override
	public BoardVO boardDetailData(int no) {
		// TODO Auto-generated method stub
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}

	@Override
	public void boardInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		mapper.boardInsert(vo);
	}
}
