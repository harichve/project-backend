package com.demo.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.demo.project.dto.GridDetail;

@Repository
public class PalindromeDaoImpl implements PalindromeDao {
	@Autowired
	JdbcTemplate jdbcTemplate; 
	
	@Override
	public List<GridDetail> getGridData(int sortOrder){
		StringBuilder query = new StringBuilder("select * from Palindrome order by list ");
		if(sortOrder == 0) {
			query.append("ASC");
		}else if(sortOrder == 1) {
			query.append("DESC");
		}
		return jdbcTemplate.query(query.toString(),new GridData());
	}
	
	@Override
	public List<GridDetail> getGridData(GridDetail gridDetail){
		return jdbcTemplate.query("select * from Palindrome where list like \"%"+gridDetail.getName()+"%\"",new GridData());
	}
	
	@Override
	public int getGridData(String keyValue){
		return jdbcTemplate.queryForObject("select count(*) from Palindrome where list like \""+keyValue+"\"",Integer.class);
	}
	
	@Override
	public Map<String, Object> addGridData(String name,int sortOrder){
		Map<String,Object> gridDataDetail = new HashMap();
		try {
		jdbcTemplate.update("insert into Palindrome (list) values(?)",name);
		gridDataDetail.put("data", getGridData(sortOrder));
		gridDataDetail.put("status", "insertion Sucessful");
		}catch(Exception e) {
			gridDataDetail.put("status", "insertion Faild");
			gridDataDetail.put("errorMsg", "Unexpected ERROR occurred while inserting data");
		}finally {
			return gridDataDetail;			
		}
	}
	
	class GridData implements RowMapper<GridDetail> {

		@Override
		public GridDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
			GridDetail palindromeData = new GridDetail();
			palindromeData.setName(rs.getString("list"));
			return palindromeData;
		}

	}

}
