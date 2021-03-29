package org.zerock.guestbook.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zerock.guestbook.dto.BoardDTO;
import org.zerock.guestbook.entity.Board;
import org.zerock.guestbook.repository.BoardRepository;
import org.zerock.guestbook.service.BoardService;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository; // 자동 주입 final

    @Override
    public Long register(BoardDTO dto) {

        Board board = dtoToEntity(dto);

        boardRepository.save(board);

        return board.getBno();
    }
}
