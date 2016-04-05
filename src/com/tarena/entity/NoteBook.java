package com.tarena.entity;

import java.sql.Timestamp;

public class NoteBook extends Entity {

	private static final long serialVersionUID = 6910896151696578033L;

	private String cn_notebook_id;
	private String cn_user_id;
	private String cn_notebook_type_id;
	private String cn_notebook_name;
	private String cn_notebook_desc;
	private Timestamp cn_notebook_createtime;
	private String cn_notebook_type_code;

	public String getCn_notebook_id() {
		return cn_notebook_id;
	}

	public void setCn_notebook_id(String cnNotebookId) {
		cn_notebook_id = cnNotebookId;
	}

	public String getCn_user_id() {
		return cn_user_id;
	}

	public void setCn_user_id(String cnUserId) {
		cn_user_id = cnUserId;
	}

	public String getCn_notebook_type_id() {
		return cn_notebook_type_id;
	}

	public void setCn_notebook_type_id(String cnNotebookTypeId) {
		cn_notebook_type_id = cnNotebookTypeId;
	}

	public String getCn_notebook_name() {
		return cn_notebook_name;
	}

	public void setCn_notebook_name(String cnNotebookName) {
		cn_notebook_name = cnNotebookName;
	}

	public String getCn_notebook_desc() {
		return cn_notebook_desc;
	}

	public void setCn_notebook_desc(String cnNotebookDesc) {
		cn_notebook_desc = cnNotebookDesc;
	}

	public Timestamp getCn_notebook_createtime() {
		return cn_notebook_createtime;
	}

	public void setCn_notebook_createtime(Timestamp cnNotebookCreatetime) {
		cn_notebook_createtime = cnNotebookCreatetime;
	}

	public String getCn_notebook_type_code() {
		return cn_notebook_type_code;
	}

	public void setCn_notebook_type_code(String cnNotebookTypeCode) {
		cn_notebook_type_code = cnNotebookTypeCode;
	}

}
