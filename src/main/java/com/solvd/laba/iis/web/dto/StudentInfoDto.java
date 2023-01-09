package com.solvd.laba.iis.web.dto;

import com.solvd.laba.iis.web.dto.validation.UpdateGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentInfoDto {
    @NotNull(groups = UpdateGroup.class, message = "Student's id should be filled")
    private long id;
    @NotNull(message = "Year of admission should be filled")
    private int admissionYear;
    @NotBlank(message = "Faculty should be filled")
    @Size(max = 50, message = "Max length of faculty is 50")
    private String faculty;
    @NotBlank(message = "Speciality should be filled")
    @Size(max = 50, message = "Max length of speciality is 50")
    private String speciality;
    @NotNull(message = "Student should contain user")
    @Valid
    private UserDto user;
    @NotNull(message = "Student should contain group")
    @Valid
    private GroupDto group;

}

