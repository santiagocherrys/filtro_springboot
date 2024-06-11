package com.riwi.filtro_springboot.infraestructure.services;

import com.riwi.filtro_springboot.api.dto.request.LessonReq;
import com.riwi.filtro_springboot.api.dto.response.ClasseBasicResp;
import com.riwi.filtro_springboot.api.dto.response.LessonResp;
import com.riwi.filtro_springboot.api.dto.response.MultimediaNoIdResp;
import com.riwi.filtro_springboot.api.dto.response.MultimediaResp;
import com.riwi.filtro_springboot.domain.entities.Classe;
import com.riwi.filtro_springboot.domain.entities.Lesson;
import com.riwi.filtro_springboot.domain.entities.Multimedia;
import com.riwi.filtro_springboot.domain.repositories.ClasseRepository;
import com.riwi.filtro_springboot.domain.repositories.LessonRepository;
import com.riwi.filtro_springboot.infraestructure.abstract_services.ILessonService;
import com.riwi.filtro_springboot.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService {
    @Autowired
    private final LessonRepository lessonRepository;

    @Autowired
    private final ClasseRepository classeRepository;

    @Override
    public void delete(Long id) {

    }

    @Override
    public LessonResp create(LessonReq request) {

        //Se busca la clase
        Classe classe =  this.classeRepository.findById(request.getClass_id()).orElseThrow(()-> new IdNotFoundException("Classe"));

        //Se genera la lista de multimedia
        List<Multimedia> multimedias = new ArrayList<>();

        //Se pasa de multimediaresp a multimedia
        multimedias = request.getMultimediaResps().stream().map(this::multimediaRespTomultimedia).collect(Collectors.toList());

        //Se guarda la clase, las multimedias y todos los datos en lesson
        Lesson lesson = new Lesson();
        lesson.setTitle(request.getTitle());
        lesson.setContent(request.getContent());
        lesson.setActive(request.isActive());
        lesson.setClasse(classe);
        lesson.setMultimedias(multimedias);

        //Se guarda en la base de datos
        lesson = this.lessonRepository.save(lesson);

        System.out.println("Este es tu id" + lesson.getId());


        //Despues de guardar en la base de  datos se tiene que asociar el id de lecciones con el multimedia y guardarlo
        //en las bases de datos
        for(Multimedia multimedia: lesson.getMultimedias()){
            multimedia.setLesson(lesson);
        }
        lesson = this.lessonRepository.save(lesson);
        return this.entityToResponse(lesson);
    }

    @Override
    public LessonResp update(Long id, LessonReq request) {
        return null;
    }

    @Override
    public Page<LessonResp> getAll(int page, int size) {
        return null;
    }

    @Override
    public LessonResp getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    //Se crea el método buscar by id
    private Lesson find(Long id){
        return this.lessonRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Lesson"));
    }

    //Entidad a response
    private LessonResp entityToResponse(Lesson entity){
        LessonResp lessonResp = new LessonResp();

        //Se copia los datos basicos
        BeanUtils.copyProperties(entity,lessonResp);
        lessonResp.setId(entity.getId());

        //Se lee la lista de multimedia

        List<MultimediaResp> multimedias = entity.getMultimedias().stream()
                .map(this::entityToMultimediaResp).collect(Collectors.toList());

        //Se lee la clase a la que pertenece
        ClasseBasicResp classeBasicResp = this.entityToClassBasicResp(entity.getClasse());

        //Guardamos las respuestas
        lessonResp.setMultimedias(multimedias);
        lessonResp.setClasse(classeBasicResp);
        return lessonResp;
    }

    private MultimediaResp entityToMultimediaResp(Multimedia multimedia){
        MultimediaResp multimediaResp = new MultimediaResp();

        //Se copia los datos
        multimediaResp.setId(multimedia.getId());
        multimediaResp.setUrl(multimedia.getUrl());
        multimediaResp.setType(multimedia.getType());
        multimediaResp.setActive(multimedia.isActive());
        multimediaResp.setCreated_at(multimedia.getCreated_at());

        return multimediaResp;
    }

    private ClasseBasicResp entityToClassBasicResp(Classe classe){

        return ClasseBasicResp.builder()
                .id(classe.getId())
                .name(classe.getName())
                .description(classe.getDescription())
                .active(classe.isActive())
                .created_at(classe.getCreated_at())
                .build();
    }

    @Override
    public LessonResp disableById(Long id) {
        Lesson lesson = this.find(id);

        //Se pone el estado de la lección en false
        lesson.setActive(false);

        //Se recorre todo el contenido multimedia para deshabilitarlo tambien
        for(Multimedia multimedia : lesson.getMultimedias()){
            multimedia.setActive(false);
        }
        //Se guarda en la base de datos
        this.lessonRepository.save(lesson);
        return this.entityToResponse(lesson);
    }

    private Multimedia multimediaRespTomultimedia(MultimediaNoIdResp multimediaResp){
        return Multimedia.builder()
                .type(multimediaResp.getType())
                .url(multimediaResp.getUrl())
                .active(multimediaResp.isActive())
                .build();
    }
}
