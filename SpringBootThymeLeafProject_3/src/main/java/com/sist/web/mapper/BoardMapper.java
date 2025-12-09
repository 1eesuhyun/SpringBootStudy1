package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;
@Mapper
@Repository
public interface BoardMapper {
	/*
	 * <select id="boardListData" resultType="com.sist.web.vo.BoardVO" parameterType="hashmap">
		 SELECT no,name,subject,hit,num,TO_CHAR(regdate,'YYYY-MM-DD') as dbday
		 FROM (SELECT no,name,subject,hit,regdate,rownum as num
		 FROM (SELECT no,name,subject,hit,regdate
		 FROM springReplyBoard ORDER BY group_id DESC , group_step ASC))
		 WHERE num BETWEEN #{start} AND #{end}
		</select>
		<select id="boardRowCount" resultType="int">
		 SELECT COUNT(*) FROM springReplyBoard
		</select>
		<insert id="boardInsert" parameterType="com.sist.web.vo.BoardVO">
		 INSERT INTO springReplyBoard(no,name,subject,content,pwd,group_id)
		 VALUES(srb_no_seq.nextval,#{no},#{name},#{subject},#{content},#{pwd},(SELECT NVL(MAX(group_id)+1,1)FROM springReplyBoard))
	   </insert>
	 */
	public List<BoardVO> boardListData(Map map);
	public int boardRowCount();
	public void boardInsert(BoardVO vo);
	/*
	 * <update id="boardHitIncrement" parameterType="int">
		  UPDATE springReplyBoard SET
		  hit=hit+1
		  WHERE no=#{no}
		 </update>
		 <select id="boardDetailData" resultType="com.sist.web.vo.BoardVO" parameterType="int">
		  SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday
		  FROM springReplyBoard
		  WHERE no=#{no}
		 </select>
	 */
	public void boardHitIncrement(int no);
	public BoardVO boardDetailData(int no);
	/*
	 * <select id="boardGetPassword" resultType="string" parameterType="int">
		  SELECT pwd FROM springReplyBoard
		  WHERE no=#{no}
		 </select>
		 <update id="boardUpdateData" parameterType="com.sist.web.vo.BoardVO">
		  UPDATE springReplyBoard SET
		  name=#{name},subject=#{subject},content=#{content}
		  WHERE no=#{no}
		 </update>
	 */
	public String boardGetPassword(int no);
	public void boardUpdateData(BoardVO vo);
	/*
	 * <select id="boardParentInfoData" resultType="com.sist.web.vo.BoardVO" parameterType="int">
		  SELECT group_id,group_tab,group_step
		  FROM springReplyBoard
		  WHERE no=#{no}
		 </select>
		 <update id="boardGroupStepIncrement" parameterType="com.sist.web.vo.BoardVO">
		  UPDATE springReplyBoard SET
		  group_step=group_step+1
		  WHERE group_id=#{group_id} AND group_step=#{group_step}
		 </update>
		 <!-- 데이터 추가 -->
		<insert id="boardReplyInsert" parameterType="com.sist.web.vo.BoardVO">
		 INSERT INTO springReplyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab,root)
		 VALUES(srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},#{group_id},#{group_step},#{group_tab},#{root}
		 )
		</insert>
		<update id="boardDepthIncrement" parameterType="int">
		 UPDATE springReplyBoard SET
		 dept=depth+1
		 WHERE no=#{no}
		</update>
	 */
	public BoardVO boardParentInfoData(int no);
	public void boardGroupStepIncrement(BoardVO vo);
	public void boardReplyInsert(BoardVO vo);
	public void boardDepthIncrement(int no);
	
	/*
	 *<select id="boardDeleteInfoData" resultType="com.sist.web.vo.BoardVO" parameterType="int">
		  SELECT depth,root FROM springReplyBoard
		  WHERE no=#{no}
		 </select>
		 <update id="boardSubjectChange" parameterType="int">
		  UPDATE springReplyBoard SET
		  subject='관리자에 의해 삭제되었습니다',
		  content='관리자에 의해 삭제되었습니다'
		  WHERE no=#{no}
		 </update>
		 <delete id="boardDelete" parameterType="int">
		  DELETE FROM springReplyBoard
		  WHERE no=#{no}
		 </delete>
		 <update id="boardDepthDecrement" parameterType="int">
		  UPDATE springReplyBoard SET
		  depth=depth-1
		  WHERE no=#{no}
		 </update> 
	 */
	public BoardVO boardDeleteInfoData(int no);
	public void boardSubjectChange(int no);
	public void boardDelete(int no);
	public void boardDepthDecrement(int no);
	
}
