package com.system.mapper;

import com.system.entity.Fee;
import com.system.entity.PagingVO;

import java.util.List;

public interface FeeMapperMod {

    List<Fee> findByPaging(PagingVO pagingVO) throws Exception;
}
