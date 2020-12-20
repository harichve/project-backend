package com.demo.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.project.dao.PalindromeDao;
import com.demo.project.entity.GridDetail;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private PalindromeDao palindromeDao;
	
	@Override
	public List<GridDetail> getGridData() {
		return palindromeDao.getGridData(0);// 0 For Initial Ascending Sort Order.
	}
	
	@Override
	public List<GridDetail> getGridData(GridDetail gridDetail) {
		return palindromeDao.getGridData(gridDetail);
	}
	
	@Override
	public Map<String, Object> addGridData(String keyValue,int sortOrder){
		Map<String,Object> errorDetail = new HashMap<>();
		if(palindromeDao.getGridData(keyValue)!=0) {
			errorDetail.put("status", "insertion Faild");
			errorDetail.put("errorMsg", "Data Already Exists");
			return errorDetail;
		}
		if(isPalindrome(keyValue)) {
			if(keyValue.length()<100) {
				if(keyValue.matches("^[a-zA-Z]*$")) {
					return palindromeDao.addGridData(keyValue,sortOrder);
				}else {
					errorDetail.put("status", "insertion Faild");
					errorDetail.put("errorMsg", "Value should be alphabetic");
					return errorDetail;
				}
			}else {
				errorDetail.put("status", "insertion Faild");
				errorDetail.put("errorMsg", "Value should be less than 100 charaters");
				return errorDetail;
			}
		}else {
			errorDetail.put("status", "insertion Faild");
			errorDetail.put("errorMsg", "Value should be a palindrome");
			return errorDetail;
		}
	}

	private boolean isPalindrome(String name) {
		for(int startindex=0,endIndex=name.length()-1;startindex<endIndex;startindex++,endIndex--) {
			if(name.charAt(startindex)!=(name.charAt(endIndex))){
				return false;
			}
		}
		return true;
	}

	
}
