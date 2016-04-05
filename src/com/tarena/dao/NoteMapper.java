package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Note;

@MyBatisRepository
public interface NoteMapper {

	/**
	 * 查询某笔记本下的所有笔记
	 */
	List<Note> findByNoteBook(String nodeBookId);
	
	/**
	 * 根据ID查询笔记
	 */
	Note findById(String noteId);
	
	/**
	 * 新增笔记
	 */
	void save(Note note);
	
	/**
	 * 修改笔记
	 */
	void update(Note note);
	
	/**
	 * 删除笔记
	 */
	void delete(String noteId);
	
}
