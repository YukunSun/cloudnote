package com.tarena.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.tarena.common.BusinessException;
import com.tarena.common.Md5Util;
import com.tarena.common.SystemConstant;
import com.tarena.common.UUIDUtil;
import com.tarena.dao.NoteBookMapper;
import com.tarena.dao.NoteBookTypeMapper;
import com.tarena.dao.UserMapper;
import com.tarena.entity.NoteBook;
import com.tarena.entity.NoteBookType;
import com.tarena.entity.User;

@Service
public class UserService {

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private NoteBookTypeMapper noteBookTypeMapper;
	
	@Resource
	private NoteBookMapper noteBookMapper;

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	public boolean createUser(User user) {
		//校验用户名
		if(this.checkUserName(user.getCn_user_name())) {
			//校验通过，创建用户
			this.addUser(user);
			//给用户初始化笔记本
			this.initNoteBook(user.getCn_user_id());
			return true;
		} else {
			//校验失败
			return false;
		}
	}
	
	/**
	 * 校验用户名
	 * 
	 * @param userName
	 * @return
	 * @throws BusinessException
	 */
	public boolean checkUserName(String userName) {
		if (userName == null)
			throw new BusinessException("用户名不能为空.");
		User user = userMapper.findByName(userName);
		if (user == null)
			return true;
		else
			return false;
	}

	/**
	 * 创建用户
	 * 
	 * @param user
	 * @throws BusinessException
	 */
	public void addUser(User user) {
		if(user == null)
			throw new BusinessException("参数为空.");
		user.setCn_user_id(UUIDUtil.getUID());
		// 加密
		user.setCn_user_password(
			Md5Util.md5(user.getCn_user_password()));
		userMapper.save(user);
	}
	
	/**
	 * 初始化笔记本
	 */
	public void initNoteBook(String userId) {
		if(userId == null)
			throw new BusinessException("参数为空.");
		// 查询特殊的笔记本类型
		List<NoteBookType> noteBookTypes = 
			noteBookTypeMapper.findSpecialType();
		// 针对每种特殊类型,创建一个默认的笔记本
		for(NoteBookType type : noteBookTypes) {
			NoteBook book = new NoteBook();
			book.setCn_notebook_id(UUIDUtil.getUID());
			book.setCn_user_id(userId);
			book.setCn_notebook_type_id(type.getCn_notebook_type_id());
			book.setCn_notebook_name(type.getCn_notebook_type_name());
			book.setCn_notebook_createtime(
				new Timestamp(System.currentTimeMillis()));
			noteBookMapper.save(book);
		}
	}
	
	/**
	 * 校验用户名及密码
	 * 
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public Map<String, Object> checkUser(String userName, String pwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(userName == null)
			throw new BusinessException("用户名为空.");
		if(pwd == null)
			throw new BusinessException("密码为空.");
		
		User user = userMapper.findByName(userName);
		if(user == null) {
			map.put("flag", SystemConstant.LOGIN_PASSWORD_ERROR);
			map.put("msg", "用户名错误.");
		} else if (!user.getCn_user_password().equals(Md5Util.md5(pwd))) {
			map.put("flag", SystemConstant.LOGIN_PASSWORD_ERROR);
			map.put("msg", "密码错误.");
		} else {
			map.put("flag", SystemConstant.LOGIN_SUCCESS);
			map.put("msg", "登录成功.");
		}
		return map;
	}
	
	/**
	 * 查询用户
	 * 
	 * @param userName
	 * @return
	 */
	public User findUser(String userName) {
		if(userName == null)
			throw new BusinessException("用户名为空.");
		return userMapper.findByName(userName);
	}
	
	/**
	 * 修改用户
	 */
	public void update(User user) {
		userMapper.update(user);
	}
	
}
