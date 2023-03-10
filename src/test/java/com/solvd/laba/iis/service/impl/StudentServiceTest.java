package com.solvd.laba.iis.service.impl;

import com.solvd.laba.iis.domain.Group;
import com.solvd.laba.iis.domain.StudentInfo;
import com.solvd.laba.iis.domain.UserInfo;
import com.solvd.laba.iis.domain.criteria.StudentSearchCriteria;
import com.solvd.laba.iis.domain.exception.ResourceDoesNotExistException;
import com.solvd.laba.iis.persistence.StudentRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void verifyRetrieveAllTest() {
        List<StudentInfo> expectedStudents = createStudents();
        when(studentRepository.findAll()).thenReturn(expectedStudents);
        List<StudentInfo> students = studentService.retrieveAll();
        assertEquals(expectedStudents, students, "Objects are not equal");
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void verifyRetrieveByIdSuccessTest() {
        StudentInfo expectedStudent = createStudent();
        when(studentRepository.findById(expectedStudent.getId())).thenReturn(Optional.of(expectedStudent));
        StudentInfo student = studentService.retrieveById(expectedStudent.getId());
        assertEquals(expectedStudent, student, "Objects are not equal");
        verify(studentRepository, times(1)).findById(expectedStudent.getId());
    }

    @Test
    public void verifyRetrieveByIdThrowsResourceDoesNotExistExceptionTest() {
        Long studentId = 1L;
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());
        assertThrows(ResourceDoesNotExistException.class, () -> studentService.retrieveById(studentId));
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    public void verifyRetrieveByUserIdSuccessTest() {
        StudentInfo expectedStudent = createStudent();
        Long userId = 1L;
        when(studentRepository.findByUserId(userId)).thenReturn(Optional.of(expectedStudent));
        StudentInfo student = studentService.retrieveByUserId(userId);
        assertEquals(expectedStudent, student, "Objects are not equal");
        verify(studentRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void verifyRetrieveByUserIdThrowsResourceDoesNotExistExceptionTest() {
        Long userId = 1L;
        when(studentRepository.findByUserId(userId)).thenReturn(Optional.empty());
        assertThrows(ResourceDoesNotExistException.class, () -> studentService.retrieveByUserId(userId));
        verify(studentRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void verifyRetrieveByGroupTest() {
        List<StudentInfo> expectedStudents = createStudents();
        Long groupId = 1L;
        when(studentRepository.findByGroup(groupId)).thenReturn(expectedStudents);
        List<StudentInfo> students = studentService.retrieveByGroup(groupId);
        assertEquals(expectedStudents, students, "Objects are not equal");
        verify(studentRepository, times(1)).findByGroup(groupId);
    }

    @Test
    public void verifyRetrieveByEmptyCriteriaTest() {
        List<StudentInfo> expectedStudents = createStudents();
        when(studentRepository.findByCriteria(new StudentSearchCriteria())).thenReturn(expectedStudents);
        List<StudentInfo> students = studentService.retrieveByCriteria(new StudentSearchCriteria());
        assertEquals(expectedStudents, students, "Objects are not equal");
        verify(studentRepository, times(1)).findByCriteria(new StudentSearchCriteria());
    }

    @Test
    public void verifyRetrieveByFacultyCriteriaTest() {
        List<StudentInfo> expectedStudents = createStudents();
        when(studentRepository.findByCriteria(new StudentSearchCriteria(null, "faculty", null))).thenReturn(expectedStudents);
        List<StudentInfo> students = studentService.retrieveByCriteria(new StudentSearchCriteria(null, "faculty", null));
        assertEquals(expectedStudents, students, "Objects are not equal");
        verify(studentRepository, times(1)).findByCriteria(new StudentSearchCriteria(null, "faculty", null));
    }

    @Test
    public void verifyRetrieveBySpecialityCriteriaTest() {
        List<StudentInfo> expectedStudents = createStudents();
        when(studentRepository.findByCriteria(new StudentSearchCriteria("speciality", null, null))).thenReturn(expectedStudents);
        List<StudentInfo> students = studentService.retrieveByCriteria(new StudentSearchCriteria("speciality", null, null));
        assertEquals(expectedStudents, students, "Objects are not equal");
        verify(studentRepository, times(1)).findByCriteria(new StudentSearchCriteria("speciality", null, null));
    }

    @Test
    public void verifyRetrieveByYearCriteriaTest() {
        List<StudentInfo> expectedStudents = createStudents();
        when(studentRepository.findByCriteria(new StudentSearchCriteria(null, null, 2019))).thenReturn(expectedStudents);
        List<StudentInfo> students = studentService.retrieveByCriteria(new StudentSearchCriteria(null, null, 2019));
        assertEquals(expectedStudents, students, "Objects are not equal");
        verify(studentRepository, times(1)).findByCriteria(new StudentSearchCriteria(null, null, 2019));
    }

    @Test
    public void verifyCreateTest() {
        Long studentId = 1L;
        StudentInfo student = createStudent();
        student.setId(null);
        doAnswer(invocation -> {
            StudentInfo receivedStudent = invocation.getArgument(0);
            receivedStudent.setId(studentId);
            return null;
        }).when(studentRepository).create(student);
        student = studentService.create(student);
        assertEquals(studentId, student.getId(), "Objects are not equal");
        verify(studentRepository, times(1)).create(any(StudentInfo.class));
    }

    @Test
    public void verifyUpdateTest() {
        StudentInfo oldStudent = createStudent();
        StudentInfo newStudent = createStudent();
        newStudent.setAdmissionYear(2020);
        when(studentRepository.findById(newStudent.getId())).thenReturn(Optional.of(oldStudent));
        StudentInfo student = studentService.update(newStudent);
        assertEquals(newStudent, student, "Objects are not equal");
        verify(studentRepository, times(1)).findById(newStudent.getId());
        verify(studentRepository, times(1)).update(newStudent);
    }

    @Test
    public void verifyDeleteTest() {
        Long studentId = 1L;
        studentService.delete(studentId);
        verify(studentRepository, times(1)).delete(studentId);
    }

    private StudentInfo createStudent() {
        return new StudentInfo(1L, 2019, "faculty", "speciality", new UserInfo(), new Group());
    }

    private List<StudentInfo> createStudents() {
        return Lists.newArrayList(
                new StudentInfo(1L, 2019, "faculty", "speciality", new UserInfo(), new Group()),
                new StudentInfo(2L, 2020, "faculty", "speciality", new UserInfo(), new Group()),
                new StudentInfo(3L, 2021, "faculty", "speciality", new UserInfo(), new Group()));
    }

}
