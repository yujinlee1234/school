package kr.or.dgit.bigdata.school;

import kr.or.dgit.bigdata.school.util.ConnectionFactory;

public class TestMain {
	public static void main(String[] args) {
		System.out.println(ConnectionFactory.getConnection());
	}
}
