package com.sist.web.service;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sist.web.mapper.FoodMapper;
import com.sist.web.vo.FoodVO;
import lombok.RequiredArgsConstructor;
/*
 *  mapper.xml => Mapper => Service => ServiceImpl => Controller
 */

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{
	private final FoodMapper mapper;

	@Override
	public List<FoodVO> foodListData(Map map) {
		// TODO Auto-generated method stub
		return mapper.foodListData(map);
	}

	@Override
	public int foodTotalPage() {
		// TODO Auto-generated method stub
		return mapper.foodTotalPage();
	}

	@Override
	public FoodVO foodDetailData(int fno) {
		// TODO Auto-generated method stub
		mapper.hitIncrement(fno);
		return mapper.foodDetailData(fno);
	}
}
