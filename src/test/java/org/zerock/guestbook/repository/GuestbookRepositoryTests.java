package org.zerock.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.guestbook.entity.Guestbook;
import org.zerock.guestbook.entity.QGuestbook;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,300).forEach(i->{
            Guestbook guestbook = Guestbook.builder()
                    .title("Title......"+i)
                    .content("Content....."+i)
                    .writer("user"+(i%10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }

    @Test
    public void updateTest(){
        Optional<Guestbook> result = guestbookRepository.findById(300L);

        if(result.isPresent()){
            Guestbook guestbook = result.get();

            guestbook.changeTitie("Changed Titie....");
            guestbook.changeContent("Changed Content....");

            guestbookRepository.save(guestbook);

        }
    }

    @Test
    public void testQuery1(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

       /** 1. 동적으로 처리하기 위해서 Q도메인 클래스를 이용하면 엔티티클래스를 선언된 필드 이용 가능하다. */
        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        /** 2.Boolean Builder는 where절에 들어가는 조건들을 넣어주는 컨테이너다. */
        BooleanBuilder builder = new BooleanBuilder();

        /** 원하는 조건은 필드값과 결합한다.
         * BooleanBuilder안에 들어가는 값은 Predicate 타입이어야 한다.*/
        BooleanExpression expression = qGuestbook.title.contains(keyword);

        builder.and(expression);

        /** BooleanBuilder는 GuestbookRepository에 추가된 QuerydslPredicateExcutor
         * 인터페이스의 findAll() 사용 가능하다 */
        Page<Guestbook> result = guestbookRepository.findAll(builder,pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });

    }

    @Test
    public void testQuery2(){
        Pageable pageable = PageRequest.of(0,10,Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qGuestbook.title.contains(keyword);

        BooleanExpression exContent = qGuestbook.content.contains(keyword);

        BooleanExpression exAll = exTitle.or(exContent); /** Boolean E*/

        builder.and(exAll);

        builder.and(qGuestbook.gno.gt(0L));

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });


    }

}
