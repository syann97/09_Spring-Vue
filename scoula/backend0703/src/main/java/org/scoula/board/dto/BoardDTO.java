package org.scoula.board.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.springframework.web.multipart.MultipartFile;

@Data                    // getter, setter, toString, equals, hashCode 생성
@NoArgsConstructor       // 기본 생성자
@AllArgsConstructor      // 모든 필드 생성자
@Builder                 // 빌더 패턴
@ApiModel(description = "게시글 DTO")
public class BoardDTO {

    // 실제 업로드된 파일들 (form에서 전송됨)
    @ApiModelProperty(value = "업로드 파일 목록")
    private List<MultipartFile> files = new ArrayList<>();

    @ApiModelProperty(value = "게시글 ID", example = "1")
    private Long no;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "글 본문")
    private String content;

    @ApiModelProperty(value = "작성자")
    private String writer;

    @ApiModelProperty(value = "등록일")
    private Date regDate;

    @ApiModelProperty(value = "수정일")
    private Date updateDate;

    // 첨부파일 정보
    @ApiModelProperty(value = "첨부파일 목록")
    private List<BoardAttachmentVO> attaches;




    // VO → DTO 변환
    public static BoardDTO of(BoardVO vo) {
        return BoardDTO.builder()
                .no(vo.getNo())
                .title(vo.getTitle())
                .content(vo.getContent())
                .writer(vo.getWriter())
                .attaches(vo.getAttaches())
                .regDate(vo.getRegDate())
                .updateDate(vo.getUpdateDate())
                .build();
    }

    // DTO → VO 변환
    public BoardVO toVo() {
        return BoardVO.builder()
                .no(no)
                .title(title)
                .content(content)
                .writer(writer)
                .attaches(attaches)
                .regDate(regDate)
                .updateDate(updateDate)
                .build();
    }
}