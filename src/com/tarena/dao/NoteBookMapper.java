package com.tarena.dao;

import java.util.List;

import com.tarena.entity.NoteBook;

@MyBatisRepository
public interface NoteBookMapper {

	/**
	 * 新增
	 */
	void save(NoteBook noteBook);
	
	/**
	 * 查询某用户所有普通的笔记本
	 */
	List<NoteBook> findNormalNoteBook(String userId);

	/**
	 * 查询某用户所有特殊的笔记本
	 */
	List<NoteBook> findSpecialNoteBook(String userId);
	
	/**
	 * 修改
	 */
	void update(NoteBook noteBook);
	
	/**
	 * 查看笔记本
	 */
	NoteBook findById(String noteBookId);
	
	/**
	 * 删除
	 */
	void delete(String noteBookId);

}
