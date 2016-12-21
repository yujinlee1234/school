package kr.or.dgit.bigdata.school;

import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import kr.or.dgit.bigdata.school.util.ConnectionFactory;
import kr.or.dgit.bigdata.school.util.JdbcUtil;

public class ConnectionFactoryTest {
	private static Connection instance;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		instance = ConnectionFactory.getConnection();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		JdbcUtil.close(instance);
	}

	@Test
	public void testConnection() {
		Assert.assertNotNull(instance);
	}

}
