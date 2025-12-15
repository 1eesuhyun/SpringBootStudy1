package com.sist.web.service;
import java.util.*;
import com.sist.web.vo.*;

public interface BoardService {
	public List<BoardVO> BoardListData(int start);
	public int boardTotalpage();
	public BoardVO boardDetailData(int no);
	public void boardInsert(BoardVO vo);
	public BoardVO boardUpdateData(int no);
	public boolean boardUpdate(BoardVO vo);
	public boolean boardDelete(int no,String pwd);
}
