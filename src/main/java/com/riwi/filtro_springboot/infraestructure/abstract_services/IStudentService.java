package com.riwi.filtro_springboot.infraestructure.abstract_services;

import com.riwi.filtro_springboot.api.dto.request.StudentReq;
import com.riwi.filtro_springboot.api.dto.response.StudentResp;

public interface IStudentService extends CrudService<StudentReq, StudentResp, Long>{
    public StudentResp disableById(Long id);
}
