package com.solvd.laba.iis.persistence;

import com.solvd.laba.iis.domain.Subject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SubjectRepository {

    List<Subject> findAll();

    Optional<Subject> findById(Long id);

    List<Subject> findByTeacher(Long teacherId);

    boolean isExist(String name);

    void create(Subject subject);

    void update(Subject subject);

    void delete(Long id);

}
