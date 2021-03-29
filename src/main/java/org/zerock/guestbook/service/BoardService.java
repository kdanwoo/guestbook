package org.zerock.guestbook.service;

import org.zerock.guestbook.dto.BoardDTO;
import org.zerock.guestbook.entity.Board;
import org.zerock.guestbook.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto){
        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }
}
