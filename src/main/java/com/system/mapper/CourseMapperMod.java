package com.system.mapper;

import com.system.entity.CourseCustom;
import com.system.entity.PagingVO;

import java.util.List;

public interface CourseMapperMod {
    List<CourseCustom> findByPaging(PagingVO pagingVO) throws Exception;
}
