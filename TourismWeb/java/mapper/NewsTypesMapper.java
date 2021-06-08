package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.User;

public interface NewsTypesMapper {
	
	NewsTypes selectNewsTypesByname(String tname);

	List<NewsTypes> checkNewsTypesByname(String tname);
	
	void deleteNewsTypesById(Integer id);
	
	void insertNewsTypes(NewsTypes newsTypes);
	
	void updateNewsTypes(NewsTypes newsTypes);
	
	List<NewsTypes> selectAllNewsTypes();
	
	List<NewsTypes> selectNewsTypesCount();
	
	NewsTypes selectNewsTypesById(Integer id);
	

}






















