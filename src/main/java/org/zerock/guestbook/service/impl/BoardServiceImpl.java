package org.zerock.guestbook.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zerock.guestbook.dto.BoardDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Board;
import org.zerock.guestbook.entity.Member;
import org.zerock.guestbook.repository.BoardRepository;
import org.zerock.guestbook.service.BoardService;

import java.util.function.Function;

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

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        Function<Object[],BoardDTO> fn = (en -> entityToDTO(Board) en[0],(Member) en[1],en[2])

        sout

        return null;
    }
}
