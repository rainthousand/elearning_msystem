package com.system.service;

import org.apache.ibatis.annotations.Param;
import com.system.entity.*;
import java.util.List;

public interface TrainingService {

    int countByExample(TrainingExample example);
    int deleteByExample(TrainingExample example);
    int deleteByPrimaryKey(Integer trainid);
    int insert(Training record);
    int insertSelective(Training record);
    List<Training> selectByExample(TrainingExample example);
    Training selectByPrimaryKey(Integer trainid);
    int updateByExampleSelective(@Param("record") Training record, @Param("example") TrainingExample example);
    int updateByExample(@Param("record") Training record, @Param("example") TrainingExample example);
    int updateByPrimaryKeySelective(Training record);
    int updateByPrimaryKey(Training record);
    List<Training> findAllById(Integer userid);
    int getTrainingCount();
    List<TrainingCustom> findByPaging(Integer toPageNo) throws Exception;
}
