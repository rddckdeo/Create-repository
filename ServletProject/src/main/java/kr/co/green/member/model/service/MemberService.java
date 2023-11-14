package kr.co.green.member.model.service;

import kr.co.green.member.model.dto.MemberDTO;

public interface MemberService {

	//회원가입
	public abstract int memberEnroll(MemberDTO memberDTO);
	
	//login
	public abstract MemberDTO memberLogin(String id);
	
	//아이디 중복검사
	public abstract boolean duplicateId(String id);
	
	//회원 정보 조회
	public abstract void selectMember(MemberDTO memberDTO);
	
	//회원 정보 수정
	public abstract int memberUpdate(MemberDTO memberDTO, String beforeName);

	public abstract int deleteMember(String name);
}
