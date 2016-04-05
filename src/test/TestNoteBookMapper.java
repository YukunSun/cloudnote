package test;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.common.UUIDUtil;
import com.tarena.dao.NoteBookMapper;
import com.tarena.entity.NoteBook;

public class TestNoteBookMapper {
	
	@Test
	public void testSave() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(
					"applicationContext.xml");
		NoteBookMapper mapper = ctx.getBean(NoteBookMapper.class);
		NoteBook nb = new NoteBook();
		nb.setCn_notebook_id(UUIDUtil.getUID());
		nb.setCn_user_id("094a7114-f5a3-40dd-a44b-293e3771d51b");
		nb.setCn_notebook_type_id("5");
		nb.setCn_notebook_name("aaa");
		nb.setCn_notebook_createtime(
			new Timestamp(System.currentTimeMillis()));
		mapper.save(nb);
	}
	
	@Test
	public void testFindNomalNoteBook() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(
					"applicationContext.xml");
		NoteBookMapper mapper = ctx.getBean(NoteBookMapper.class);
		List<NoteBook> list = mapper.findSpecialNoteBook(
					"a6e75b5d-181c-47fd-9810-a5595785e81f");
		for(NoteBook nb : list) {
			System.out.println(
				nb.getCn_notebook_id() + " " +
				nb.getCn_notebook_name() + " " +
				nb.getCn_notebook_type_id() + " " +
				nb.getCn_notebook_type_id() + " " +
				nb.getCn_notebook_type_code() + " " +
				nb.getCn_notebook_createtime()
			);
		}
	}

}
