package org.jht.mapper;

import org.jht.dto.BoardDTO;

public interface BoardDAO {
	// *DAO로직 실행클래스*
	
	// 글쓰기를 하기 위한 DAO(Insert)
	public void boardInsert(BoardDTO bdto);

}
