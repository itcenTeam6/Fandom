package com.example.NewJeans.dto.response;


import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListBoardResponseDTO {

    private String error;

    private List<DetailBoardResponseDTO> boards;
}
