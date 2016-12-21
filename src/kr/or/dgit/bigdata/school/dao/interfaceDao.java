package kr.or.dgit.bigdata.school.dao;

import java.util.List;

public interface interfaceDao<T> {
	void insertItem(T item);
	void updateItem(T item);
	void deleteItem(int idx);
	
	T selectByNo(int idx);
	List<T> selectByAll();
}
