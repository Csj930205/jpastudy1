package com.jpa.jpastudy1;

import com.jpa.jpastudy1.domain.entity.Board;
import com.jpa.jpastudy1.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaStudy1ApplicationTests {

    @Autowired
    BoardRepository boardRepository;
    @Test
    void contextLoads() {
        Board params = Board.builder()
                .title("1번")
                .content("1번test")
                .writer("성준")
                .hits(0)
                .deleteYn('N')
                .build();

        boardRepository.save(params);
    }

}
