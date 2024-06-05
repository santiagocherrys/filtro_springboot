package com.riwi.filtro_springboot.infraestructure.services;

import com.riwi.filtro_springboot.api.dto.request.ClasseReq;
import com.riwi.filtro_springboot.api.dto.response.ClasseResp;
import com.riwi.filtro_springboot.domain.entities.Classe;
import com.riwi.filtro_springboot.domain.repositories.ClasseRepository;
import com.riwi.filtro_springboot.infraestructure.abstract_services.IClasseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClasseService implements IClasseService {
    @Autowired
    private final ClasseRepository classeRepository;

    @Override
    public void delete(Long id) {

    }

    @Override
    public ClasseResp create(ClasseReq request) {
        Classe classe = this.requestToEntity(request);


        return this.entityToResponse(this.classeRepository.save(classe));
    }

    @Override
    public ClasseResp update(Long id, ClasseReq request) {
        return null;
    }

    @Override
    public Page<ClasseResp> getAll(int page, int size) {
        return null;
    }

    @Override
    public ClasseResp getById(Long id) {
        return null;
    }
    private Classe requestToEntity(ClasseReq classe){

        return Classe.builder()
                .name(classe.getName())
                .description(classe.getDescription())
                .created_at(classe.getCreated_at())
                .active(classe.getActive())
                .build();
    }

    private ClasseResp entityToResponse(Classe entity){
        //Se crea instancia de la respuesta
        ClasseResp classeResp = new ClasseResp();

        BeanUtils.copyProperties(entity,classeResp);

        return classeResp;
    }
}
