package com.tarena.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.common.BusinessException;
import com.tarena.common.SystemConstant;
import com.tarena.common.UUIDUtil;
import com.tarena.dao.ActivityMapper;
import com.tarena.dao.NoteBookMapper;
import com.tarena.dao.NoteMapper;
import com.tarena.dao.ShareMapper;
import com.tarena.entity.Note;
import com.tarena.entity.NoteActivity;
import com.tarena.entity.NoteBook;
import com.tarena.entity.Share;

@Service
public class NoteService {

	@Resource
	private NoteMapper noteMapper;
	
	@Resource
	private NoteBookMapper noteBookMapper;
	
	@Resource
	private ShareMapper shareMapper;
	
	@Resource
	private ActivityMapper activityMapper;
	
	/**
	 * 查询笔记
	 */
	public List<Note> findNote(String noteBookId) {
		if (noteBookId == null)
			throw new BusinessException("参数为空.");
		return noteMapper.findByNoteBook(noteBookId);
	}
	
	/**
	 * 查看笔记
	 */
	public Note findNoteDetail(String noteId) {
		if (noteId == null)
			throw new BusinessException("参数为空.");
		return noteMapper.findById(noteId);
	}
	
	/**
	 * 新增笔记
	 */
	public void addNote(Note note) {
		if(note == null)
			throw new BusinessException("参数为空.");
		note.setCn_note_id(UUIDUtil.getUID());
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		noteMapper.save(note);
	}
	
	/**
	 * 编辑笔记，修改其标题和内容
	 */
	public void updateNoteBody(Note note) {
		if (note == null)
			throw new BusinessException("参数为空.");
		Note n = noteMapper.findById(note.getCn_note_id());
		n.setCn_note_title(note.getCn_note_title());
		n.setCn_note_body(note.getCn_note_body());
		n.setCn_note_last_modify_time(System.currentTimeMillis());
		noteMapper.update(n);
	}
	
	/**
	 * 删除笔记，将其放入回收站
	 */
	public void deleteNote(String noteId) {
		if (noteId == null)
			throw new BusinessException("参数为空.");
		Note note = noteMapper.findById(noteId);
		List<NoteBook> list = 
			noteBookMapper.findSpecialNoteBook(note.getCn_user_id());
		for (NoteBook book : list) {
			if(book.getCn_notebook_type_code()
					.equals(SystemConstant.NOTEBOOK_RECYCLE)) {
				moveNote(noteId, book.getCn_notebook_id());
				break;
			}
		}
	}

	/**
	 * 移动笔记，放到指定的笔记本下
	 */
	public void moveNote(String noteId, String noteBookId) {
		if (noteId == null || noteBookId == null)
			throw new BusinessException("参数为空.");
		Note note = noteMapper.findById(noteId);
		note.setCn_notebook_id(noteBookId);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		noteMapper.update(note);
	}
	
	/**
	 * 删除笔记，彻底删除
	 */
	public void deleteNoteReally(String noteId) {
		if (noteId == null)
			throw new BusinessException("参数为空.");
		noteMapper.delete(noteId);
	}

	/**
	 * 分享笔记
	 */
	public void addShareNote(String noteId) {
		if (noteId == null)
			throw new BusinessException("参数为空.");
		Note note = noteMapper.findById(noteId);
		Share share = new Share();
		share.setCn_share_id(UUIDUtil.getUID());
		share.setCn_note_id(noteId);
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		shareMapper.save(share);
	}
	
	/**
	 * 搜索分享笔记
	 */
	public List<Share> findShareNote(String searchKey, int currentPage, int pageSize) {
		if(searchKey == null)
			throw new BusinessException("参数为空.");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("searchKey", searchKey);
		param.put("begin", (currentPage-1)*pageSize);
		param.put("pageSize", pageSize);
		return shareMapper.findByPage(param);
	}
	
	/**
	 * 查看分享笔记
	 */
	public Share findShareNoteDetail(String shareId) {
		if(shareId == null)
			throw new BusinessException("参数为空.");
		return shareMapper.findById(shareId);
	}
	
	/**
	 * 收藏分享笔记
	 */
	public void likeShareNote(String shareId, String userId) {
		Share share = shareMapper.findById(shareId);
		Note note = new Note();
		note.setCn_user_id(userId);
		note.setCn_note_title(share.getCn_share_title());
		note.setCn_note_body(share.getCn_share_body());
		List<NoteBook> list = 
			noteBookMapper.findSpecialNoteBook(note.getCn_user_id());
		for (NoteBook book : list) {
			if(book.getCn_notebook_type_code()
					.equals(SystemConstant.NOTEBOOK_FAVORITES)) {
				note.setCn_notebook_id(book.getCn_notebook_id());
				break;
			}
		}
		
		this.addNote(note);
	}
	
	/**
	 * 收藏活动笔记
	 */
	public void likeActivityNote(String noteActivityId, String userId) {
		NoteActivity acvitity = activityMapper.findNoteActivityById(noteActivityId);
		Note note = new Note();
		note.setCn_user_id(userId);
		note.setCn_note_title(acvitity.getCn_note_activity_title());
		note.setCn_note_body(acvitity.getCn_note_activity_body());
		List<NoteBook> list = 
			noteBookMapper.findSpecialNoteBook(note.getCn_user_id());
		for (NoteBook book : list) {
			if(book.getCn_notebook_type_code()
					.equals(SystemConstant.NOTEBOOK_FAVORITES)) {
				note.setCn_notebook_id(book.getCn_notebook_id());
				break;
			}
		}
		
		this.addNote(note);
	}
	
}
