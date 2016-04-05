package com.tarena.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.common.BaseReader;
import com.tarena.entity.Note;
import com.tarena.entity.Result;
import com.tarena.entity.Share;
import com.tarena.entity.User;
import com.tarena.service.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController {

	@Resource
	private NoteService noteService;

	@RequestMapping("/findNote.do")
	@ResponseBody
	public Result findNote(String noteBookId) {
		List<Note> list = noteService.findNote(noteBookId);
		return new Result(list);
	}
	
	@RequestMapping("/findNoteDetail.do")
	@ResponseBody
	public Result findNoteDetail(String noteId) {
		Note note = noteService.findNoteDetail(noteId);
		return new Result(note);
	}
	
	@RequestMapping("/addNote.do")
	@ResponseBody
	public Result addNote(Note note, HttpSession session) {
		User user = (User) session.getAttribute("user");
		note.setCn_user_id(user.getCn_user_id());
		noteService.addNote(note);
		return new Result(note);
	}
	
	@RequestMapping("/updateNote.do")
	@ResponseBody
	public Result updateNote(Note note) {
		noteService.updateNoteBody(note);
		return new Result();
	}

	@RequestMapping("/deleteNote.do")
	@ResponseBody
	public Result deleteNote(String noteId) {
		noteService.deleteNote(noteId);
		return new Result();
	}
	
	@RequestMapping("/moveNote.do")
	@ResponseBody
	public Result moveNote(String noteId, String noteBookId) {
		noteService.moveNote(noteId, noteBookId);
		return new Result();
	}
	
	@RequestMapping("/shareNote.do")
	@ResponseBody
	public Result shareNote(String noteId) {
		noteService.addShareNote(noteId);
		return new Result();
	}
	
	@RequestMapping("/deleteRecycleNote.do")
	@ResponseBody
	public Result deleteRecycleNote(String noteId) {
		noteService.deleteNoteReally(noteId);
		return new Result();
	}
	
	@RequestMapping("/searchShareNote.do")
	@ResponseBody
	public Result searchShareNote(String searchKey, int currentPage) {
		List<Share> list = noteService.findShareNote(
				searchKey, currentPage, BaseReader.getPageSize());
		return new Result(list);
	}
	
	@RequestMapping("/findShareNoteDetail.do")
	@ResponseBody
	public Result findShareNoteDetail(String shareId) {
		Share share = noteService.findShareNoteDetail(shareId);
		return new Result(share);
	}
	
	@RequestMapping("/likeShareNote.do")
	@ResponseBody
	public Result likeShareNote(String shareId, HttpSession session) {
		User user = (User) session.getAttribute("user");
		noteService.likeShareNote(shareId, user.getCn_user_id());
		return new Result();
	}
	
	@RequestMapping("/likeActivityNote.do")
	@ResponseBody
	public Result likeActivityNote(String noteActivityId, HttpSession session) {
		User user = (User) session.getAttribute("user");
		noteService.likeActivityNote(noteActivityId, user.getCn_user_id());
		return new Result();
	}
	
}
