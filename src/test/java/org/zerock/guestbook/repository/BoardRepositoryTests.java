package org.zerock.guestbook.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook.entity.Board;
import org.zerock.guestbook.entity.Member;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoard(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder().email("user"+i+"@aaa.com").build();

            Board board = Board.builder()
                    .title("Title..."+i)
                    .content("Content...."+i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });
    }

    @Test
    public void testRead1(){
        Optional<Board> result = boardRepository.findById(100L);

        Board board = result.get();

        System.out.println(board);
        System.out.println(board.getWriter());


    }
}