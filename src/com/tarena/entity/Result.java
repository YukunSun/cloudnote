package com.tarena.entity;

public class Result extends Entity {

	private static final long serialVersionUID = -4027778560853812030L;

	/**
	 * 状态，0成功，1失败。
	 */
	public int status;

	/**
	 * 提示信息
	 */
	public String message;

	/**
	 * 相关数据
	 */
	public Object data;

	public Result() {
		super();
	}

	public Result(Object data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
