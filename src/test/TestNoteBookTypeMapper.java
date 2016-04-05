package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.NoteBookTypeMapper;
import com.tarena.entity.NoteBookType;

public class TestNoteBookTypeMapper {
	
	@Test
	public void testFindSpecialType() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(
					"applicationContext.xml");
		NoteBookTypeMapper mapper = ctx.getBean(NoteBookTypeMapper.class);
		List<NoteBookType> list = mapper.findSpecialType();
		for(NoteBookType type : list) {
			System.out.println(
				type.getCn_notebook_type_id() + " " +
				type.getCn_notebook_type_code() + " " +
				type.getCn_notebook_type_name() + " " +
				type.getCn_notebook_type_desc()
 			);
		}
	}

}
