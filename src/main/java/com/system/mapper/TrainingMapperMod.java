package com.system.mapper;

import com.system.po.PagingVO;
import com.system.po.TrainingCustom;

import java.util.List;

public interface TrainingMapperMod {
    List<TrainingCustom> findByPaging(PagingVO pagingVO) throws Exception;
}
