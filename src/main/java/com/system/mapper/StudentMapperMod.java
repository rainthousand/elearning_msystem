package com.system.mapper;

import com.system.entity.PagingVO;
import com.system.entity.StudentCustom;

import java.util.List;

public interface StudentMapperMod {

    List<StudentCustom> findByPaging(PagingVO pagingVO) throws Exception;

    StudentCustom findStudentAndSelectCourseListById(Integer id) throws Exception;

}
