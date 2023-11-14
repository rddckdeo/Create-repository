package kr.co.green.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.green.member.model.dto.MemberDTO;

public class MemberDAO {

	private PreparedStatement pstmt;
	int result = 0;

	// 회원가입 메소드
	public int memberEnroll(Connection con, MemberDTO memberDTO) {
		String query = "INSERT INTO MEMBER VALUES(?, ?, ?, SYSDATE)";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPwd());
			pstmt.setString(3, memberDTO.getName());

			result = pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 로그인 메소드
	public MemberDTO memberLogin(Connection con, String id) {
		String query = "SELECT member_id, member_pwd ,member_name, member_in_date" + "		 FROM MEMBER" + "		"
				+ "WHERE member_id = ?" ;

		MemberDTO result = new MemberDTO();
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

//			rs.next();
			while (rs.next()) {
//				String resultId = rs.getString("MEMBER_ID");
//				String resultName = rs.getString("MEMBER_NAME");
//				String resultIndate = rs.getString("MEMBER_IN_DATE");
				
//				result.setId(resultId);
//				result.setName(resultName);
//				result.setIndate(resultIndate);
				
				result.setId(rs.getString("MEMBER_ID"));
				result.setPwd(rs.getString("MEMBER_PWD"));
				result.setName(rs.getString("MEMBER_NAME"));
				result.setIndate(rs.getString("MEMBER_IN_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 중복검사 메소드
	public boolean duplicateId(Connection con, String id) {
		String query = "SELECT * FROM MEMBER" + "		WHERE Member_ID = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			return rs.next(); // 쿼리가 조회된 결과(ResultSet)이 반환되면 true, 없으면 false 반환
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	// 정보수정하기 전 로그인 된 회원의 정보들 출력하는 메소드
	public void selectMember(Connection con, MemberDTO memberDTO) {
		String query = "SELECT MEMBER_ID, MEMBER_NAME, MEMBER_IN_DATE FROM MEMBER" + "		WHERE MEMBER_NAME = ?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getName());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberDTO.setId(rs.getString("MEMBER_ID"));
				memberDTO.setName(rs.getString("MEMBER_NAME"));
				memberDTO.setIndate(rs.getString("MEMBER_IN_DATE"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//회원정보 수정 메소드
	public int memberUpdate(Connection con, MemberDTO memberDTO, String beforeName) {
		String query = "UPDATE MEMBER"
				+ "		SET MEMBER_ID = ?,"
				+ "		MEMBER_NAME = ?"
				+ "		WHERE MEMBER_NAME = ?";
		
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getName());
			pstmt.setString(3, beforeName);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteMember(Connection con, String name) {
		String query = "DELETE FROM MEMBER"
				+ "		WHERE MEMBER_NAME = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			
			int result = pstmt.executeUpdate();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}





