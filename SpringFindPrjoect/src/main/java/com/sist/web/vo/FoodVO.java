package com.sist.web.vo;
import java.util.*;

import lombok.Data;
@Data
public class FoodVO {
	private int fno,hit,jjimcount,likecount,replycount;
	private String name,type,phone,address,theme,price,time,parking,poster,images,content;
	private Double score;
}
