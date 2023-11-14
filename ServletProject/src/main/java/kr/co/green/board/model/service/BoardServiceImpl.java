package kr.co.green.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.green.board.dto.BoardDTO;
import kr.co.green.board.model.dao.BoardDAO;
import kr.co.green.common.DatabaseConnection;
import kr.co.green.common.PageInfo;

public class BoardServiceImpl implements BoardService{
	//DB연결
	private Connection con;
	private DatabaseConnection dc;
	private BoardDAO boardDAO;
	
	
	public BoardServiceImpl() {
		dc = new DatabaseConnection();
		boardDAO = new BoardDAO();
		con = dc.connDB();
	}
	
	
	public ArrayList<BoardDTO> boardList(PageInfo pi){
		return boardDAO.boardList(con, pi);
	}
	
	public int boardListCount() {
		return boardDAO.boardListCount(con);
	}
	
	
	
}
