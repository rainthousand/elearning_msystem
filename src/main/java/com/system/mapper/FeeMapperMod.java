package com.system.mapper;

import com.system.po.Fee;
import com.system.po.PagingVO;

import java.util.List;

public interface FeeMapperMod {

    //分页查询
    List<Fee> findByPaging(PagingVO pagingVO) throws Exception;
}
