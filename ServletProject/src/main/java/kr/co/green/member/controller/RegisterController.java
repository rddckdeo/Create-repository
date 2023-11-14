package kr.co.green.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import kr.co.green.member.model.dto.MemberDTO;
import kr.co.green.member.model.service.MemberServiceImpl;

@WebServlet("/register.do")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
		// INSERT
		// 입력값이 비어있는지 확인 -> 비어있으면 에러페이지로 넘김
		String id = request.getParameter("new-userid");
		String name = request.getParameter("new-username");
		String pwd = request.getParameter("new-password");
		
		//패스워드 암호화
		String salt = BCrypt.gensalt(12);
		
		//사용자가 입력한 비밀번호 + salt 암호화
		String hashedPassword = BCrypt.hashpw(pwd, salt);
		MemberDTO memberDTO = new MemberDTO(id, name, hashedPassword);
		
		//아이디 유효성 검사 (백엔드에서도 해줘야됨)
		String namePattern = "^[가-힣]+$";
		Pattern pattern = Pattern.compile(namePattern);	//우리가 만든 정규식을 컴파일하고
		Matcher nameMatcher = pattern.matcher(name);		//받은 name값 컴파일된 정규식에 테스트
		
		//패스워드 유효성 검사
		String pwdPattern = "^(?=.*[a-zA-Z])(?=.*[@$!%*?&\\#])[A-Za-z\\d@$!%*?&\\#]{6,20}$";
		Pattern passwordPattern = Pattern.compile(pwdPattern);
		Matcher passwordMatcher = passwordPattern.matcher(pwd); 
		
		if(nameMatcher.matches() && passwordMatcher.matches()) {
			//테스트값이 true를 반환할 경우
			//회원가입 진행
			MemberServiceImpl memberService = new MemberServiceImpl();
			int result = memberService.memberEnroll(memberDTO);
			
			if(result != 1 ) {
				validationAlert(response, "회원가입에 실패했습니다.");
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher("index.jsp");
				view.forward(request, response);
			}
		}else if(!nameMatcher.matches()){
			validationAlert(response, "이름은 한글만 가능합니다.");
			return;	//자바나 js에서 더 이상 다른 구문이 실행되지 않게 하기위해 return으로 돌려보냄
		}else if(!passwordMatcher.matches()) {
			validationAlert(response, "비밀번호가 정책에 맞지 않습니다.");
			return;	
		}
	}
	
	private void validationAlert(HttpServletResponse response, String msg) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script>"
				+ "location.href='/registerForm.do';"
				+ "alert('" + msg + "')"
				+ "</script>");
		out.flush();
		out.close();
	}

}









