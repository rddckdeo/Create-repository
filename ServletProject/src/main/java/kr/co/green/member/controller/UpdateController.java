package kr.co.green.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.green.member.model.dto.MemberDTO;
import kr.co.green.member.model.service.MemberServiceImpl;

@WebServlet("/memberUpdate.do")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		//유저가 적은 값 받아오고, DTO객체 생성해서 넣어주고, 서비스 객체 생성해서 매개변수를DTO로 해서 넘겨줌 -> DAO에서 쿼리문으로 수정
		String name = request.getParameter("userName");
		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPwd");
		String beforeName = (String)session.getAttribute("name");
		
		MemberDTO memberDTO = new MemberDTO(id, name, pwd);
		
		MemberServiceImpl memberService = new MemberServiceImpl();
		int result = memberService.memberUpdate(memberDTO, beforeName);
		
		if(result == 0) {
			updateAlert(response, "정보 수정에 실패했습니다.");
		}else {
			//기존 세션 정보를 삭제해준다.
			session.removeAttribute("name");
			// 삭제한 세션 정보를 새로 작성한 세션 값으로 설정을 해준다.
			session.setAttribute("name", memberDTO.getName());
			
			updateAlert(response, "회원 정보가 수정되었습니다.");
		}
	}
	
	private void updateAlert(HttpServletResponse response, String msg) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script>"
				+ "location.href='/form/updateForm.do';"
				+ "alert('" + msg + "')"
				+ "</script>");
		out.flush();
		out.close();
	}

}
