package kr.co.green.member.model.service;

import java.sql.Connection;

import kr.co.green.common.DatabaseConnection;
import kr.co.green.member.model.dao.MemberDAO;
import kr.co.green.member.model.dto.MemberDTO;

public class MemberServiceImpl implements MemberService{
	
	//DB연결
	private Connection con;
	private DatabaseConnection dc;
	private MemberDAO memberDAO;
	
	
	public MemberServiceImpl() {
		dc = new DatabaseConnection();
		memberDAO = new MemberDAO();
		con = dc.connDB();
	}
	
	//회원가입
	@Override
	public int memberEnroll(MemberDTO memberDTO) {
		int result = memberDAO.memberEnroll(con, memberDTO);
		return result;
	}
	
	//login
	@Override
	public MemberDTO memberLogin(String id) {
		return memberDAO.memberLogin(con, id);
	}

	//아이디 중복검사
	@Override
	public boolean duplicateId(String id) {
		return memberDAO.duplicateId(con, id);
	}

	@Override
	public void selectMember(MemberDTO memberDTO) {
		memberDAO.selectMember(con, memberDTO);
	}
	
	//정보 수정
	@Override
	public int memberUpdate(MemberDTO memberDTO, String beforeName) {
		return memberDAO.memberUpdate(con, memberDTO, beforeName);
	}
	//회원 탈퇴
	@Override
	public int deleteMember(String name) {
		return memberDAO.deleteMember(con, name);
	}
	
}
