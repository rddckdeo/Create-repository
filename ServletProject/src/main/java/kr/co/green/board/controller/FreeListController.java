package kr.co.green.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.green.board.dto.BoardDTO;
import kr.co.green.board.model.service.BoardServiceImpl;
import kr.co.green.common.PageInfo;
import kr.co.green.common.Pagination;

/**
 * Servlet implementation class FreeListController
 */
@WebServlet("/freeList.do")
public class FreeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FreeListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		BoardDTO bo = new BoardDTO();
//		bo.setIdx(1);
//		bo.setTitle("제목");
//		bo.setContent("내용");
//		bo.setInDate("작성일");
//		bo.setViews(15);
//		bo.setWriter("김창대");
//		
//		List<BoardDTO> list = new ArrayList<>();
//		list.add(bo);
//		list.add(bo);
//		list.add(bo);
//		list.add(bo);
//		list.add(bo);
		
		BoardServiceImpl boardService = new BoardServiceImpl();
		String cpage = request.getParameter("cpage");
		// cpage = 현재 페이지
		
		//전체 게시글 수
		int listCount = boardService.boardListCount();
		//보여질 페이지 수
		 int pageLimit = 5;
		//한 페이지에 들어갈 게시글 수
		int boardLimit = 5;
		// 나머지 페이징 처리는 common
		
		// 페이징 처리
		Pagination page = new Pagination();
		PageInfo pi = page.getPageInfo();
		
		ArrayList<BoardDTO> list = boardService.boardList();
		
		request.setAttribute("list", list);
		RequestDispatcher view = request.getRequestDispatcher("/views/board/free/freeList.jsp");
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
