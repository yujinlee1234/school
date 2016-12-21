package kr.or.dgit.bigdata.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import kr.or.dgit.bigdata.school.dto.Student;
import kr.or.dgit.bigdata.school.util.ConnectionFactory;
import kr.or.dgit.bigdata.school.util.JdbcUtil;

public class StudentService implements InterfaceDao<Student> {
	private static final StudentService instance = new StudentService();
	
	public static StudentService getInstance() {
		return instance;
	}
	
	private StudentService() {}

	@Override
	public void insertItem(Student item) {
		String sql = "insert into student values(?,?,?,?)";
		PreparedStatement pstmt = null;
		Connection con = ConnectionFactory.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, item.getStudId());
			pstmt.setString(2, item.getName());
			pstmt.setString(3, item.getEmail());
			pstmt.setTimestamp(4, new Timestamp(item.getDob().getTime()));
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			if(e.getErrorCode()==1062){
				JOptionPane.showMessageDialog(null, "이미 존재하는 학번입니다.");
			}else{
				e.printStackTrace();
			}
		}finally{
			JdbcUtil.close(pstmt);
		}
	}

	@Override
	public void updateItem(Student item) {
		String sql = "update student set name=?, email=?, dob=? where stud_id=?";
		
		
		try (	Connection con = ConnectionFactory.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){			
			pstmt.setString(1, item.getName());
			pstmt.setString(2, item.getEmail());
			pstmt.setTimestamp(3, new Timestamp(item.getDob().getTime()));
			pstmt.setInt(4, item.getStudId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteItem(int idx) {
		String sql = "delete from student where stud_id=?";
		PreparedStatement pstmt = null;
		Connection con = ConnectionFactory.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, idx);			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			if(e.getErrorCode()==1062){
			}else{
				e.printStackTrace();
			}
		}finally{
			JdbcUtil.close(pstmt);
		}
		
	}

	@Override
	public Student selectByNo(int idx) {
		Student std = new Student();
		String sql = "select stud_id, name, email, dob from student where stud_id=?";
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()){//아무것도 존재하지 않을 경우
				std = getStudent(rs);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs, pstmt);
		}		
		return std;
	}

	@Override
	public List<Student> selectByAll() {
		List<Student> stdList = new ArrayList<>();
		String sql = "select stud_id, name, email, dob from student";
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(!rs.next()){//아무것도 존재하지 않을 경우
				return Collections.emptyList();
			}
			do{
				stdList.add(getStudent(rs));
			}while(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs, pstmt);
		}		
		
		return stdList;
	}

	private Student getStudent(ResultSet rs) throws SQLException {
//		stud_id, name, email, dob
		int studId = rs.getInt("stud_id");
		String name = rs.getString("name");
		String email = rs.getString("email");
		Date dob = rs.getDate("dob");
		return new Student(studId, name, email, dob);
	}

}
