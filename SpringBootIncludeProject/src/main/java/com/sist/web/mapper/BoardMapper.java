package com.sist.web.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;
@Repository
@Mapper
public interface BoardMapper {
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit "
			+ "FROM springboard "
			+ "ORDER BY no DESC "
			+ "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<BoardVO> BoardListData(int start);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springboard")
	public int boardTotalpage();
	// 상세보기 @GetMapping
	@Select("SELECT no,subject,name,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday "
			+ "FROM springboard "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	@Update("UPDATE springboard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	// 작성 @PostMapping
	@Insert("INSERT INTO springboard VALUES(sb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},SYSDATE,0)")
	public void boardInsert(BoardVO vo);
	// 수정 @PutMapping
	@Select("SELECT pwd FROM springboard WHERE no=#{no}")
	public String boardGetPassword(int no);
	@Update("UPDATE springboard SET "
			+ "name=#{name},subject=#{subject},content=#{content} "
			+ "WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	// 삭제 @DeleteMapping
	@Delete("DELETE FROM springboard WHERE no=#{no}")
	public void boardDelete(int no);
	// ---------------------- RestFul
	// 게시판 / 댓글
	
	
}
