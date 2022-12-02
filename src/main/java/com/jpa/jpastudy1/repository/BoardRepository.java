package com.jpa.jpastudy1.repository;

import com.jpa.jpastudy1.domain.entity.Board;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    /*
    * 게시글 리스트 조회 -(삭제 여부 기준)
    * */
    List<Board> findAllByDeleteYn(final char deleteYn, final Sort sort);
}
