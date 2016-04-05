package com.tarena.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.common.BusinessException;
import com.tarena.common.SystemConstant;
import com.tarena.common.UUIDUtil;
import com.tarena.dao.NoteBookMapper;
import com.tarena.dao.NoteBookTypeMapper;
import com.tarena.entity.NoteBook;
import com.tarena.entity.NoteBookType;

@Service
public class NoteBookService {

	@Resource
	private NoteBookTypeMapper noteBookTypeMapper;

	@Resource
	private NoteBookMapper noteBookMapper;

	/**
	 * 查询用户所有的普通笔记本
	 */
	public List<NoteBook> findNormalNoteBook(String userId) {
		if(userId == null)
			throw new BusinessException("参数为空.");
		return noteBookMapper.findNormalNoteBook(userId);
	}
	
	/**
	 * 查询用户所有的特殊笔记本
	 */
	public Map<String, NoteBook> findSpecialNoteBook(String userId) {
		if(userId == null)
			throw new BusinessException("参数为空.");
		Map<String, NoteBook> result = new HashMap<String, NoteBook>();
		List<NoteBook> list = noteBookMapper.findSpecialNoteBook(userId);
		for(NoteBook nb : list) {
			result.put(nb.getCn_notebook_type_code(), nb);
		}
		return result;
	}
	
	/**
	 * 新增笔记本
	 */
	public void addNoteBook(NoteBook book) {
		if(book == null)
			throw new BusinessException("参数为空.");
		book.setCn_notebook_id(UUIDUtil.getUID());
		List<NoteBookType> types = noteBookTypeMapper.findAllType();
		for (NoteBookType type : types) {
			if (type.getCn_notebook_type_code().equals(SystemConstant.NOTEBOOK_NORMAL)) {
				book.setCn_notebook_type_id(type.getCn_notebook_type_id());
				break;
			}
		}
		book.setCn_notebook_createtime(
			new Timestamp(System.currentTimeMillis()));
		noteBookMapper.save(book);
	}
	
	/**
	 * 修改笔记本名称
	 */
	public void updateNoteBookName(String noteBookId, String noteBookName) {
		if(noteBookId == null || noteBookName == null)
			throw new BusinessException("参数为空.");
		NoteBook book = noteBookMapper.findById(noteBookId);
		book.setCn_notebook_name(noteBookName);
		noteBookMapper.update(book);
	}
	
	/**
	 * 删除笔记本
	 */
	public void deleteNoteBook(String noteBookId) {
		if(noteBookId == null)
			throw new BusinessException("参数为空.");
		noteBookMapper.delete(noteBookId);
	}
	
	/**
	 * 查询用户通笔记本列表
	 */
	public List<NoteBook> findNoteBookList(String userId) {
		if(userId == null)
			throw new BusinessException("参数为空.");
		Map<String, NoteBook> map = this.findSpecialNoteBook(userId);
		NoteBook push = map.get("push");
		push.setCn_notebook_name("默认笔记本");
		
		List<NoteBook> list = this.findNormalNoteBook(userId);
		list.add(0, push);
		
		return list;
	}

}
