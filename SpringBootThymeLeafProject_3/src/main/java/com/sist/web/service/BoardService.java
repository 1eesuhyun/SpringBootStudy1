package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> boardListData(Map map);
	public int boardRowCount();
	public void boardInsert(BoardVO vo);
	public BoardVO boardDetailData(int no);
	public BoardVO boardUpdateData(int no);
	public boolean boardUpdateData(BoardVO vo);
	public void boardReplyInsert(int pno,BoardVO vo);
	public boolean boardDelete(int no,String pwd);
}
