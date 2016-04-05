package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.common.UUIDUtil;
import com.tarena.dao.UserMapper;
import com.tarena.entity.User;

public class TestUserMapper {
	
	@Test
	public void testFindByName() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(
					"applicationContext.xml");
		UserMapper mapper = ctx.getBean(UserMapper.class);
		User user = mapper.findByName("lhh");
		System.out.println(
			user.getCn_user_id() + " " + 
			user.getCn_user_name() + " " + 
			user.getCn_user_password() + " " +
			user.getCn_user_desc());
	}
	
	@Test
	public void testSave() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(
					"applicationContext.xml");
		UserMapper mapper = ctx.getBean(UserMapper.class);
		User u = new User();
		u.setCn_user_id(UUIDUtil.getUID());
		u.setCn_user_name("admin4");
		u.setCn_user_password("123");
		u.setCn_user_desc("admin4");
		mapper.save(u);
	}
	
}
