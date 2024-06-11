package com.riwi.filtro_springboot.api.dto.request;

import com.riwi.filtro_springboot.api.dto.response.MultimediaNoIdResp;
import com.riwi.filtro_springboot.api.dto.response.MultimediaResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonReq{
    private String title;
    private String content;
    private Long class_id;
    private boolean active;
    private List<MultimediaNoIdResp> multimediaResps;
}
