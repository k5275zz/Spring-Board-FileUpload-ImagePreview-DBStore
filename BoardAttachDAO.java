package org.jht.mapper;

import org.jht.dto.BoardAttachDTO;

public interface BoardAttachDAO {
	// 파일업로드를 tbl_attach테이블에 insert
	public void insert(BoardAttachDTO battachdto);
}
