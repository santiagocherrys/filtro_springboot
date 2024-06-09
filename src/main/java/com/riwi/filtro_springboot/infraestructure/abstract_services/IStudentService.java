package com.riwi.filtro_springboot.infraestructure.abstract_services;

import com.riwi.filtro_springboot.api.dto.request.StudentReq;
import com.riwi.filtro_springboot.api.dto.response.StudentBasicRespo;
import com.riwi.filtro_springboot.api.dto.response.StudentResp;

import java.util.List;

public interface IStudentService extends CrudService<StudentReq, StudentResp, Long>{
    public StudentResp disableById(Long id);
    //Devolver por nombre
    public List<StudentBasicRespo> findStudentsByNameClass(String name);


    //Devolver por descripcion
    public List<StudentBasicRespo> findStudentsByDescriptionClass(String description);

    //Devolver todos los estudiantes
    public List<StudentResp> findAll();
}
