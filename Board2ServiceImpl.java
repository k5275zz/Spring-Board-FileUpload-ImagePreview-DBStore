package org.jht.service;

import org.jht.dto.BoardDTO;
import org.jht.mapper.BoardAttachDAO;
import org.jht.mapper.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Board2ServiceImpl implements Board2Service{
	@Autowired
	BoardDAO bdao;
	@Autowired
	BoardAttachDAO battachdao;
	@Transactional // 둘다 되야지만 db에 반영되게한다.
	// 만약 트랜잭션이 없다면 글쓰기에 실패해도 파일이 업로드 되게 된다.
	public void boardWrite(BoardDTO bdto) {
		// tbl_board 테이블에 insert
		bdao.boardInsert(bdto);
		// tbl_attach 테이블에 insert
		bdto.getAttachList().forEach(attach->{
			attach.setBno(bdto.getBno());
			battachdao.insert(attach);
			System.out.println(battachdao.toString());
		});
	}

}
