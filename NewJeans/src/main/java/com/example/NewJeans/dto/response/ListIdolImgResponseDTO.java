package com.example.NewJeans.dto.response;

import lombok.*;

import java.util.List;

@Getter @Setter @ToString
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
    private List<DetailIdolImgResponseDTO> idolImages;
}
