package kr.co.green.common;

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

@WebServlet("/form/*")
public class FormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FormController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		//http://localhost/form/loginform.do -> action을 콘솔에 출력하면 /loginform.do를 출력한다
		String action = request.getPathInfo();
		String nextPage = "";
		HttpSession session = request.getSession();
		
		if(action.equals("/loginForm.do")) {
			nextPage = "/views/member/login.jsp"; 
		}else if(action.equals("/registerForm.do")) {
			nextPage = "/views/member/register.jsp";
		}else if(action.equals("/home.do")) {
			nextPage = "/";
		}else if(action.equals("/updateForm.do")) {
			//SELECT로 해당하는 멤버의 정보를 조회
			String name = (String)session.getAttribute("name");	//정보수정 누르면 로그인했을 때 저장된 회원의 name을 세션에서 반환한다.
			
			MemberServiceImpl memberService = new MemberServiceImpl();
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setName(name);
			memberService.selectMember(memberDTO);
			
			//데이터 바인딩
			request.setAttribute("member", memberDTO);
			nextPage = "/views/member/update.jsp";
		} 
		
		if(nextPage != null && !nextPage.isEmpty()) {
			RequestDispatcher view = request.getRequestDispatcher(nextPage);
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
