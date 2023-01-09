package com.solvd.laba.iis.service.impl;

import com.solvd.laba.iis.domain.TeacherInfo;
import com.solvd.laba.iis.domain.exception.ResourceNotFoundException;
import com.solvd.laba.iis.persistence.TeacherRepository;
import com.solvd.laba.iis.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public List<TeacherInfo> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public TeacherInfo getById(long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher with id = " + id + "not found"));
    }

    @Override
    public List<TeacherInfo> getByGroup(long groupId) {
        return teacherRepository.findByGroup(groupId);
    }

    @Override
    public List<TeacherInfo> getBySubject(long subjectId) {
        return teacherRepository.findBySubject(subjectId);
    }

    @Override
    public TeacherInfo create(TeacherInfo teacherInfo) {
        return teacherRepository.create(teacherInfo);
    }

    @Override
    public TeacherInfo save(TeacherInfo teacherInfo) {
        return teacherRepository.save(teacherInfo);
    }

    @Override
    public void delete(TeacherInfo teacherInfo) {
        teacherRepository.delete(teacherInfo);
    }

    @Override
    public void deleteSubject(long teacherId, long subjectId) {
        teacherRepository.deleteSubject(teacherId, subjectId);
    }

    @Override
    public void addSubject(long teacherId, long subjectId) {
        teacherRepository.addSubject(teacherId, subjectId);
    }
}
