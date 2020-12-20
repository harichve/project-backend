package com.demo.project.dao;

import java.util.List;
import java.util.Map;

import com.demo.project.dto.GridDetail;

public interface PalindromeDao {

	List<GridDetail> getGridData(int sortOrder);

	List<GridDetail> getGridData(GridDetail gridDetail);

	Map<String,Object> addGridData(String name,int sortOrder);

	int getGridData(String keyValue);

}
