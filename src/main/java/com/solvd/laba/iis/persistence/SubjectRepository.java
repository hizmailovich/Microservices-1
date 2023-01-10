package com.solvd.laba.iis.persistence;

import com.solvd.laba.iis.domain.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository {
    List<Subject> findAll();

    Optional<Subject> findById(long id);

    Subject create(Subject subject);

    Subject save(Subject subject);

    void delete(Subject subject);
}