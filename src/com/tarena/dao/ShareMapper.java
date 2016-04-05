package com.tarena.dao;

import java.util.List;
import java.util.Map;

import com.tarena.entity.Share;

@MyBatisRepository
public interface ShareMapper {

	void save(Share share);

	List<Share> findByPage(Map<String, Object> param);
	
	Share findById(String shareId);
	
}
