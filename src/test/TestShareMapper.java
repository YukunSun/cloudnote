package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.ShareMapper;
import com.tarena.entity.Share;

public class TestShareMapper {
	
	@Test
	public void testFindByPage() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(
					"applicationContext.xml");
		ShareMapper mapper = ctx.getBean(ShareMapper.class);
		int currentPage = 1;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("searchKey", "1");
		param.put("begin", (currentPage-1)*10);
		param.put("pageSize", 10);
		List<Share> list = mapper.findByPage(param);
		for(Share s : list) {
			System.out.println(
				s.getCn_share_title() + " "	+
				s.getCn_share_body()
			);
		}
	}

}
 