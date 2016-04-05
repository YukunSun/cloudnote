package com.tarena.entity;

public class NoteActivity extends Entity {

	private static final long serialVersionUID = -7658922989028860908L;

	private String cn_note_activity_id;
	private String cn_activity_id;
	private String cn_note_id;
	private Integer cn_note_activity_up;
	private Integer cn_note_activity_down;
	private String cn_note_activity_title;
	private String cn_note_activity_body;

	public String getCn_note_activity_id() {
		return cn_note_activity_id;
	}

	public void setCn_note_activity_id(String cnNoteActivityId) {
		cn_note_activity_id = cnNoteActivityId;
	}

	public String getCn_activity_id() {
		return cn_activity_id;
	}

	public void setCn_activity_id(String cnActivityId) {
		cn_activity_id = cnActivityId;
	}

	public String getCn_note_id() {
		return cn_note_id;
	}

	public void setCn_note_id(String cnNoteId) {
		cn_note_id = cnNoteId;
	}

	public Integer getCn_note_activity_up() {
		return cn_note_activity_up;
	}

	public void setCn_note_activity_up(Integer cnNoteActivityUp) {
		cn_note_activity_up = cnNoteActivityUp;
	}

	public Integer getCn_note_activity_down() {
		return cn_note_activity_down;
	}

	public void setCn_note_activity_down(Integer cnNoteActivityDown) {
		cn_note_activity_down = cnNoteActivityDown;
	}

	public String getCn_note_activity_title() {
		return cn_note_activity_title;
	}

	public void setCn_note_activity_title(String cnNoteActivityTitle) {
		cn_note_activity_title = cnNoteActivityTitle;
	}

	public String getCn_note_activity_body() {
		return cn_note_activity_body;
	}

	public void setCn_note_activity_body(String cnNoteActivityBody) {
		cn_note_activity_body = cnNoteActivityBody;
	}

}
