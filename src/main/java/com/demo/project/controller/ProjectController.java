package com.demo.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.dto.GridDetail;
import com.demo.project.dto.InsertData;
import com.demo.project.service.ProjectService;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/index")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@GetMapping("/getGridData")
	List<GridDetail> getGridData() {
		return projectService.getGridData();
	}
	
	@PostMapping("/getGridData")
	List<GridDetail> getGridData(@RequestBody GridDetail gridDetail) {
		return projectService.getGridData(gridDetail);
	}
	
	@PostMapping("/addGridData")
	Map<String,Object> addGridData(@RequestBody InsertData insertData) {
		return projectService.addGridData(insertData.getName(),insertData.getSortOrder());
	}
	
}
