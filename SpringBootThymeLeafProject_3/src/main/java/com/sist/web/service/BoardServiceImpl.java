package com.sist.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.sist.web.mapper.*;
import com.sist.web.vo.*;
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardMapper bmapper;

	@Override
	public List<BoardVO> boardListData(Map map) {
		// TODO Auto-generated method stub
		return bmapper.boardListData(map);
	}

	@Override
	public int boardRowCount() {
		// TODO Auto-generated method stub
		return bmapper.boardRowCount();
	}

	@Override
	public void boardInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		bmapper.boardInsert(vo);
	}

	@Override
	public BoardVO boardDetailData(int no) {
		// TODO Auto-generated method stub
		bmapper.boardHitIncrement(no);
		return bmapper.boardDetailData(no);
	}

	@Override
	public BoardVO boardUpdateData(int no) {
		// TODO Auto-generated method stub
		return bmapper.boardDetailData(no);
	}

	@Override
	public boolean boardUpdateData(BoardVO vo) {
		// TODO Auto-generated method stub
		boolean bCheck=false;
		String db_pwd=bmapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd()))
		{
			bCheck=true;
			bmapper.boardUpdateData(vo);
		}
		return bCheck;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void boardReplyInsert(int pno,BoardVO vo) {
		// TODO Auto-generated method stub
		BoardVO pvo=bmapper.boardParentInfoData(pno);
		bmapper.boardGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		bmapper.boardReplyInsert(vo);
		bmapper.boardDepthIncrement(pno);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean boardDelete(int no, String pwd) {
		// TODO Auto-generated method stub
		boolean bCheck=false;
		String db_pwd=bmapper.boardGetPassword(no);
		if(db_pwd.equals(pwd))
		{
			bCheck=true;
			BoardVO vo=bmapper.boardDeleteInfoData(no);
			if(vo.getDepth()==0)
			{
				bmapper.boardDelete(no);
			}
			else
			{
				bmapper.boardSubjectChange(no);
			}
			bmapper.boardDepthDecrement(vo.getRoot());
		}
		return bCheck;
	}
}
