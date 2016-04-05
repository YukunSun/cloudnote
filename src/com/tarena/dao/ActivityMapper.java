package com.tarena.dao;

import java.util.List;
import java.util.Map;

import com.tarena.entity.Activity;
import com.tarena.entity.NoteActivity;

@MyBatisRepository
public interface ActivityMapper {

	List<Activity> findAll();

	List<NoteActivity> findNoteActivityByPage(Map<String, Object> param);
	
	NoteActivity findNoteActivityById(String id);
	
	void saveNoteActivity(NoteActivity noteActivity);
	
	void updateNoteActivity(NoteActivity noteActivity);
	
}
