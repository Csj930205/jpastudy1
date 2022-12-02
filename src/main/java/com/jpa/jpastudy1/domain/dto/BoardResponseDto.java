package com.jpa.jpastudy1.domain.dto;

import com.jpa.jpastudy1.domain.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDto {
    private Long seq;                       // PK
    private String title;                   // 제목
    private String content;                 // 내용
    private String writer;                  // 작성자
    private int hits;                       // 조회수
    private char deleteYn;                  // 삭제여부
    private LocalDateTime createdDate;      // 생성일
    private LocalDateTime modifiedDate;     // 수정일

    public BoardResponseDto(Board entity) {
        this.seq = entity.getSeq();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.hits = entity.getHits();
        this.deleteYn= entity.getDeleteYn();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
