package com.system.mapper;

import com.system.po.CourseCustom;
import com.system.po.PagingVO;

import java.util.List;

public interface CourseMapperMod {
    List<CourseCustom> findByPaging(PagingVO pagingVO) throws Exception;
}
