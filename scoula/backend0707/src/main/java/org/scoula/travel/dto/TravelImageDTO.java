package org.scoula.travel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.travel.domain.TravelImageVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelImageDTO {

    // 환경별로 경로 알맞게 설정
    private static final String BASE = "c:/upload/travel/";

    private long no;
    private String filename;
    private long travelNo;

    // VO에서 DTO로 변환하는 정적 메서드
    public static TravelImageDTO of(TravelImageVO vo) {
        return TravelImageDTO.builder()
                .no(vo.getNo())
                .filename(vo.getFilename())
                .travelNo(vo.getTravelNo())
                .build();
    }

    @JsonIgnore // JSON 변환에서 제외
    public String getPath() {
        return BASE + filename;
    }

    // 프론트엔드에서 사용할 URL 프로퍼티
    public String getUrl() {
        return "/api/travel/image/" + no;
    }
}
