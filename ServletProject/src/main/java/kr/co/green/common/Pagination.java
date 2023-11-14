package kr.co.green.common;

public class Pagination {

	public static PageInfo getPageInfo(int listCount, int currentPage, int pageLimit, int boardLimit) {
		// 전체 페이지 구하기 (ceil 은 반올림 해주는 것)
		// listcount  = 101, boradLimit = 5 일때 나눌 경우 나눠지지 않음
		// double로 형변환해서 소수점까지 가지고 올 수 있도록 한다.
		// Math.ceil : 소수점 가지고 왔을 때 소수점을 올림 처리
		int maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		//시작 페이지 구하기
		// 상황) 페이지 10개씩 보여준다, 사용자가 16페이지를 보고있다.
		//currentPage = 16, pageLimit = 10;
		// (currentPage -1) : 16 - 1 : 값 15
		// (currentPage -1) / pageLimit
		// 15/10 = 1
		
		//CurrentPage -1) / pageLimit * pageLimit
		// 1 * 10 = 10
		// (currnetPage -1) / (pageLimit * pageLimit) + 1
		int startPage = (currentPage-1) / pageLimit * pageLimit + 1;
		
		//끝 페이지 구하기
		// startPage : 11, pageLimit : 10
		int endPage = startPage + pageLimit - 1;
		
		
		//끝 페이지가 전페 페이지보다 클 때
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		return null;
		
	}
	
}
