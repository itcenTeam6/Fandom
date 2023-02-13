package com.example.fandomTest.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ListIdolImgResponseDTO {
    private int size;
    private int page;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;
    private int startPage;
    private int endPage;
    private List<DetailIdolImgResponseDTO> idolImages;

}
