package kr.co.green.member.controller;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import kr.co.green.member.model.dto.MemberDTO;
import kr.co.green.member.model.service.MemberServiceImpl;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 유저가 입력한 데이터 받기
		String id = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		//2. 받은 데이터(아이디,패스워드)가 일치하는지 확인
		MemberServiceImpl memberService = new MemberServiceImpl();
		
		// 암호화된 패스워드 조회
		MemberDTO member = memberService.memberLogin(id);
		//암호화된 패스워드 확인
		if(BCrypt.checkpw(pwd, member.getPwd())) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
		
		//3. 일치하면 세션에 저장후 홈페이지로 보냄 
		if(Objects.isNull(member.getId())) {	//로그인 실패
			response.sendRedirect("/views/common/error.jsp");
//			response.sendRedirect("/views/member/login.jsp");
		}else {	//로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("name", member.getName());
			RequestDispatcher view = request.getRequestDispatcher("/");
			view.forward(request, response);
		}
		
		//4. 일치하지 않으면 로그인 페이지로 보냄
	}

}









