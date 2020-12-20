package com.demo.project.service;

import java.util.List;
import java.util.Map;

import com.demo.project.dto.GridDetail;

public interface ProjectService {
	
	List<GridDetail> getGridData();

	List<GridDetail> getGridData(GridDetail gridDetail);

	Map<String, Object> addGridData(String gridDetail,int sortOrder);

}
