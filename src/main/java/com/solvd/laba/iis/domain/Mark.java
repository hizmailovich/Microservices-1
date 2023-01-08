package com.solvd.laba.iis.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Mark {
    private long id;
    private LocalDate date;
    private int value;
    private StudentInfo student;
    private TeacherInfo teacher;
    private Subject subject;
}
