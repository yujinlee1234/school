package kr.or.dgit.bigdata.school;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.bigdata.school.dao.StudentService;
import kr.or.dgit.bigdata.school.dto.Student;

public class StudentServiceTest {
	private static StudentService instance;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		instance = StudentService.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		instance = null;
	}

	@Test
	public void testSelectByAll() {
		List<Student> list = instance.selectByAll();
		Assert.assertNotEquals(0, list.size());
		for(Student s : list){
			System.out.println(s);
		}
	}
	
	@Test
	public void testSelectByNo(){
		Student selectedItem = instance.selectByNo(1);
		Assert.assertNotNull(selectedItem);
		System.out.println(selectedItem);
	}
	
	@Test
	public void testDeleteItem(){
		int beforeSize = instance.selectByAll().size();
		
		instance.deleteItem(2);
		int afterSize = instance.selectByAll().size();
		
		Assert.assertNotEquals(beforeSize, afterSize);
	}
	
}
