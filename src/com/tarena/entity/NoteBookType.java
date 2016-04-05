package com.tarena.entity;

public class NoteBookType extends Entity {

	private static final long serialVersionUID = -4845727630319767512L;

	private String cn_notebook_type_id;
	private String cn_notebook_type_code;
	private String cn_notebook_type_name;
	private String cn_notebook_type_desc;

	public String getCn_notebook_type_id() {
		return cn_notebook_type_id;
	}

	public void setCn_notebook_type_id(String cnNotebookTypeId) {
		cn_notebook_type_id = cnNotebookTypeId;
	}

	public String getCn_notebook_type_code() {
		return cn_notebook_type_code;
	}

	public void setCn_notebook_type_code(String cnNotebookTypeCode) {
		cn_notebook_type_code = cnNotebookTypeCode;
	}

	public String getCn_notebook_type_name() {
		return cn_notebook_type_name;
	}

	public void setCn_notebook_type_name(String cnNotebookTypeName) {
		cn_notebook_type_name = cnNotebookTypeName;
	}

	public String getCn_notebook_type_desc() {
		return cn_notebook_type_desc;
	}

	public void setCn_notebook_type_desc(String cnNotebookTypeDesc) {
		cn_notebook_type_desc = cnNotebookTypeDesc;
	}

}
