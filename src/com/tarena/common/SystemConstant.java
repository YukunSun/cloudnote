package com.tarena.common;

/**
 * 系统常量
 */
public interface SystemConstant {

	// 登录状态
	int LOGIN_SUCCESS = 0;
	int LOGIN_USERNAME_ERROR = 1;
	int LOGIN_PASSWORD_ERROR = 2;

	// 收藏笔记本
	String NOTEBOOK_FAVORITES = "favorites";
	// 回收站笔记本
	String NOTEBOOK_RECYCLE = "recycle";
	// 活动笔记本
	String NOTEBOOK_ACTION = "action";
	// 推送笔记本
	String NOTEBOOK_PUSH = "push";
	// 普通笔记本
	String NOTEBOOK_NORMAL = "normal";
	
}
