package com.sist.web.vo;

import lombok.Data;

@Data
public class RecipeVO {
/*
 * NO                                        NOT NULL NUMBER
 TITLE                                     NOT NULL VARCHAR2(4000)
 POSTER                                    NOT NULL VARCHAR2(260)
 CHEF                                      NOT NULL VARCHAR2(500)
 LINK                                               VARCHAR2(100)
 HIT                                                NUMBER
 */
	private int no,hit;
	private String title,poster,chef,link;
}
