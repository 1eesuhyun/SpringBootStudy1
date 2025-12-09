package com.sist.web.vo;

import lombok.Data;

@Data
public class FoodVO {
	/*
	 *   FNO                                       NOT NULL NUMBER(38)
		 NAME                                               VARCHAR2(4000)
		 TYPE                                               VARCHAR2(4000)
		 PHONE                                              VARCHAR2(4000)
		 ADDRESS                                            VARCHAR2(4000)
		 SCORE                                              NUMBER(38,1)
		 THEME                                              VARCHAR2(4000)
		 PRICE                                              VARCHAR2(4000)
		 TIME                                               VARCHAR2(4000)
		 PARKING                                            VARCHAR2(4000)
		 POSTER                                             VARCHAR2(4000)
		 IMAGES                                             VARCHAR2(4000)
		 CONTENT                                            VARCHAR2(4000)
		 HIT                                                NUMBER(38)
		 JJIMCOUNT                                          NUMBER
		 LIKECOUNT                                 NOT NULL NUMBER
		 REPLYCOUNT                                NOT NULL NUMBER
	 */
	private int fno,hit,jjimcount,likecount,replycount;
	private String name,type,phone,address,theme,price,time,parking,poster,images,content;
	private Double score;
	
}
