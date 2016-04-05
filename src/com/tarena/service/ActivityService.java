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
import com.tarena.entity.Activity;
import com.tarena.entity.Note;
import com.tarena.entity.NoteActivity;
import com.tarena.entity.NoteBook;

@Service
public class ActivityService {

	@Resource
	private ActivityMapper activityMapper;
	
	@Resource
	private NoteMapper noteMapper;
	
	@Resource
	private NoteBookMapper noteBookMapper;

	public List<Activity> findAllActivity() {
		return activityMapper.findAll();
	}
	
	public List<NoteActivity> findNoteActivity(String activityId, int currentPage, int pageSize) {
		if(activityId == null)
			throw new BusinessException("参数为空.");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("activityId", activityId);
		param.put("begin", (currentPage-1)*pageSize);
		param.put("pageSize", pageSize);
		return activityMapper.findNoteActivityByPage(param);
	}
	
	public NoteActivity findNoteActivity(String noteActivityId) {
		if(noteActivityId == null)
			throw new BusinessException("参数为空.");
		return activityMapper.findNoteActivityById(noteActivityId);
	}
	
	public NoteActivity addNoteActivity(String activityId, String noteId, String userId) {
		if(activityId == null 
				|| noteId == null || userId == null)
			throw new BusinessException("参数为空.");
		
		Note note = noteMapper.findById(noteId);
		if(note != null) {
			NoteActivity a = new NoteActivity();
			a.setCn_note_activity_id(UUIDUtil.getUID());
			a.setCn_activity_id(activityId);
			a.setCn_note_id(noteId);
			a.setCn_note_activity_up(0);
			a.setCn_note_activity_down(0);
			a.setCn_note_activity_title(note.getCn_note_title());
			a.setCn_note_activity_body(note.getCn_note_body());
			activityMapper.saveNoteActivity(a);
			
			List<NoteBook> list = noteBookMapper.findSpecialNoteBook(userId);
			for(NoteBook noteBook : list) {
				if(noteBook.getCn_notebook_type_code().equals(
						SystemConstant.NOTEBOOK_ACTION)) {
					note.setCn_note_id(UUIDUtil.getUID());
					note.setCn_notebook_id(noteBook.getCn_notebook_id());
					note.setCn_note_create_time(System.currentTimeMillis());
					note.setCn_note_last_modify_time(System.currentTimeMillis());
					noteMapper.save(note);
					break;
				}
			}
			return a;
		}
		return null;
	}
	
	public void updateForUp(String noteActivityId) {
		NoteActivity na = this.findNoteActivity(noteActivityId);
		int up = na.getCn_note_activity_up() == null ?
				1 : na.getCn_note_activity_up();
		na.setCn_note_activity_up(up + 1);
		activityMapper.updateNoteActivity(na);
	}
	
	public void updateForDown(String noteActivityId) {
		NoteActivity na = this.findNoteActivity(noteActivityId);
		int down = na.getCn_note_activity_down() == null ? 
				1 : na.getCn_note_activity_down();
		na.setCn_note_activity_down(down + 1);
		activityMapper.updateNoteActivity(na);
	}

}
