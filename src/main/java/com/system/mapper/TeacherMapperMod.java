package com.system.mapper;

import com.system.entity.PagingVO;
import com.system.entity.TeacherCustom;

import java.util.List;

public interface TeacherMapperMod {
    List<TeacherCustom> findByPaging(PagingVO pagingVO) throws Exception;
}
