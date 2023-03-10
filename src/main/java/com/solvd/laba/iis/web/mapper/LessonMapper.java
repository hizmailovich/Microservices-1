package com.solvd.laba.iis.web.mapper;

import com.solvd.laba.iis.domain.Lesson;
import com.solvd.laba.iis.web.dto.LessonDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GroupMapper.class, SubjectMapper.class, TeacherInfoMapper.class})
public interface LessonMapper {

    LessonDto entityToDto(Lesson lesson);

    Lesson dtoToEntity(LessonDto lessonDto);

    List<LessonDto> entityToDto(List<Lesson> lessons);

}
