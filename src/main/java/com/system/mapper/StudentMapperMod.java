package com.system.mapper;

import com.system.po.PagingVO;
import com.system.po.StudentCustom;

import java.util.List;

public interface StudentMapperMod {

    List<StudentCustom> findByPaging(PagingVO pagingVO) throws Exception;

    StudentCustom findStudentAndSelectCourseListById(Integer id) throws Exception;

}
