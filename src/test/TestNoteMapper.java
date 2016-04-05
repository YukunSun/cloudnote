package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.NoteMapper;
import com.tarena.entity.Note;

public class TestNoteMapper {

	@Test
	public void testFindByNoteBook() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(
					"applicationContext.xml");
		NoteMapper mapper = ctx.getBean(NoteMapper.class);
		List<Note> list = mapper.findByNoteBook("4a5ba2df-eef8-417d-8918-7a1ad222c9b0");
		for(Note n : list) {
			System.out.println(
				n.getCn_note_id() + " " +
				n.getCn_note_title() + " " +
				n.getCn_note_body() + " " +
				n.getCn_note_create_time()
			);
		}
	}
	
}
