package com.solvd.laba.iis.persistence.jdbc.mapper;

import com.solvd.laba.iis.domain.Group;
import com.solvd.laba.iis.domain.StudentInfo;
import com.solvd.laba.iis.domain.UserInfo;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class StudentRowMapper {

    @SneakyThrows
    public static StudentInfo mapRow(ResultSet rs) {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setId(rs.getLong("student_id"));
        studentInfo.setAdmissionYear(rs.getInt("student_year"));
        studentInfo.setFaculty(rs.getString("student_faculty"));
        studentInfo.setSpeciality(rs.getString("student_speciality"));
        UserInfo student = UserRowMapper.mapRow(rs);
        studentInfo.setUser(student);
        Group group = GroupRowMapper.mapRow(rs);
        studentInfo.setGroup(group);
        return studentInfo;
    }

    @SneakyThrows
    public static List<StudentInfo> mapRows(ResultSet rs) {
        List<StudentInfo> students = new ArrayList<>();
        while (rs.next()) {
            StudentInfo studentInfo = mapRow(rs);
            students.add(studentInfo);
        }
        return students;
    }

}
