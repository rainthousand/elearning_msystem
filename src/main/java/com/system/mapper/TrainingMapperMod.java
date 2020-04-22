package com.system.mapper;

import com.system.entity.PagingVO;
import com.system.entity.TrainingCustom;

import java.util.List;

public interface TrainingMapperMod {
    List<TrainingCustom> findByPaging(PagingVO pagingVO) throws Exception;
}
