package kr.co.green.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.green.member.model.dto.MemberDTO;
import kr.co.green.member.model.service.MemberServiceImpl;

@WebServlet("/memberDelete.do")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("hi, delete");
		//세션에서 name 가져오기
		HttpSession session = request.getSession();
		MemberDTO member = new MemberDTO();
		String name = (String)session.getAttribute("name"); // (String으로 형변환 해야 가져올 수 있음)
		
		//서비스 객체 생성 -> 호출
		MemberServiceImpl memberService = new MemberServiceImpl();
		int result = memberService.deleteMember(name);
		//성공 시 "/"로 이동
		if(result == 0) {
			RequestDispatcher view = request.getRequestDispatcher("/");
			view.forward(request, response);
			System.out.println("실패");
		}else if (result == 1){
			session.removeAttribute("name");
			RequestDispatcher view = request.getRequestDispatcher("/");
			view.forward(request, response);
		}
		//실패 시 "/"로 이동
	}

}
