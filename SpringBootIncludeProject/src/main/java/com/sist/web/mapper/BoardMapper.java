package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
	// 작성 @PostMapping
	// 수정 @PutMapping
	// 삭제 @DeleteMapping
	// ---------------------- RestFul
	// 게시판 / 댓글
}
