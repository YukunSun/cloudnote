package com.tarena.entity;

public class Activity extends Entity {

	private static final long serialVersionUID = -6473494346630104770L;

	private String cn_activity_id;
	private String cn_activity_title;
	private String cn_activity_body;
	private Long cn_activity_end_time;

	public String getCn_activity_id() {
		return cn_activity_id;
	}

	public void setCn_activity_id(String cnActivityId) {
		cn_activity_id = cnActivityId;
	}

	public String getCn_activity_title() {
		return cn_activity_title;
	}

	public void setCn_activity_title(String cnActivityTitle) {
		cn_activity_title = cnActivityTitle;
	}

	public String getCn_activity_body() {
		return cn_activity_body;
	}

	public void setCn_activity_body(String cnActivityBody) {
		cn_activity_body = cnActivityBody;
	}

	public Long getCn_activity_end_time() {
		return cn_activity_end_time;
	}

	public void setCn_activity_end_time(Long cnActivityEndTime) {
		cn_activity_end_time = cnActivityEndTime;
	}

}
