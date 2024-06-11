package com.riwi.filtro_springboot.infraestructure.abstract_services;

import com.riwi.filtro_springboot.api.dto.request.LessonReq;
import com.riwi.filtro_springboot.api.dto.response.LessonResp;

public interface ILessonService extends CrudService<LessonReq, LessonResp,Long>{
    public LessonResp disableById(Long id);
}
