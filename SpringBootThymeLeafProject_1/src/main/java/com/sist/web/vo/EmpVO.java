package com.sist.web.vo;
import java.util.*;

import lombok.Data;
@Data
public class EmpVO {
	private int empno,sal,deptno,mgr,comm;
	private String ename,job,dbday;
	private Date hiredate;
}
