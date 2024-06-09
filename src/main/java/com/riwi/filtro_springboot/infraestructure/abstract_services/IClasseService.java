package com.riwi.filtro_springboot.infraestructure.abstract_services;

import com.riwi.filtro_springboot.api.dto.request.ClasseReq;
import com.riwi.filtro_springboot.api.dto.response.ClasseBasicResp;
import com.riwi.filtro_springboot.api.dto.response.ClasseResp;
import com.riwi.filtro_springboot.domain.entities.Classe;

import java.util.List;

public interface IClasseService extends CrudService<ClasseReq, ClasseResp, Long>{
    List<ClasseBasicResp> findByName(String name);

    //Encontrar todo
    List<ClasseBasicResp> findAll();
}
