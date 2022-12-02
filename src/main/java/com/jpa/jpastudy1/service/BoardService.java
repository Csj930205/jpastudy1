package com.jpa.jpastudy1.service;
import com.jpa.jpastudy1.domain.dto.BoardRequestDto;
import com.jpa.jpastudy1.domain.dto.BoardResponseDto;
import com.jpa.jpastudy1.domain.entity.Board;
import com.jpa.jpastudy1.paging.CommonParams;
import com.jpa.jpastudy1.paging.Pagination;
import com.jpa.jpastudy1.repository.BoardMapper;
import com.jpa.jpastudy1.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;

    /*
    * 게시글 생성
    * */
    @Transactional
    public Long save(final BoardRequestDto params) {
        Board entity = boardRepository.save(params.toEntity());
        return entity.getSeq();
    }

    /*
    * 게시글 리스트 조회
    * */
    public List<BoardResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "seq", "createdDate");
        List<Board> boardList = boardRepository.findAll();

        /* Stream API 를 사용하지 않을 경우

        List<BoardResponseDto> boardList = new ArrayList<>();
        for ( Board board : list ) {
            boardList.add(new BoardResponseDto(board));
        }
        return boardList

         */
        return boardList.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    /*
     * 게시글 리스트 조회 -(삭제 여부 기준)
     * */
    public List<BoardResponseDto> findAllByDeleteYn(final char deleteYn) {
        Sort sort = Sort.by(Sort.Direction.DESC, "seq", "createdDate");
        List<Board> list = boardRepository.findAllByDeleteYn(deleteYn, sort);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    /*
    * 게시글 수정
    * */
    @Transactional
    public Long update(final Long seq, final BoardRequestDto boardRequestDto) {
        Board entity = boardRepository.findById(seq).orElseThrow(() -> new RuntimeException("Error"));
        entity.update(boardRequestDto.getTitle(), boardRequestDto.getContent(), boardRequestDto.getWriter());
        return seq;
    }

    /*
    * 게시글 삭제
    * */
    @Transactional
    public Long delete(final Long seq) {
        Board entity = boardRepository.findById(seq).orElseThrow(() -> new RuntimeException("Error"));
        entity.delete();
        return seq;
    }

    /*
    * 게시글 상세정보 조회
    * */
    @Transactional
    public BoardResponseDto findById(final Long seq) {
        Board entity = boardRepository.findById(seq).orElseThrow(() -> new RuntimeException("Error"));
        entity.increaseHits();
        return new BoardResponseDto(entity);
    }

    /*
    * 게시글 리스트 조회 (With pagination information)
    * */
    public Map<String, Object> findAll(CommonParams params) {
        // 게시글 수 조회
        int count = boardMapper.count(params);

        // 등록된 게시글이 없는 경우, 로직 종료
        if (count < 1) {
            return Collections.emptyMap();
        }

        // 페이지네이션 정보 계산
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // 게시글 리스트 조회
        List<BoardResponseDto> list = boardMapper.findAll(params);

        // 데이터 반환
        Map<String, Object> response = new HashMap<>();
        response.put("params", params);
        response.put("list", list);
        return response;
    }
}
