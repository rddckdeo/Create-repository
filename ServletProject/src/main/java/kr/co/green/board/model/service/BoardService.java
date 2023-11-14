package kr.co.green.board.model.service;

import java.util.ArrayList;

import kr.co.green.board.dto.BoardDTO;
import kr.co.green.common.PageInfo;

public interface BoardService {
	//게시글 목록 조회
	public ArrayList<BoardDTO> boardList(PageInfo pi);
	
	public int boardListCount();
}
