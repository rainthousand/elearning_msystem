package com.system.service.impl;

import com.system.mapper.SelectedcourseMapper;
import com.system.mapper.TrainingMapper;
import com.system.mapper.TrainingMapperMod;
import com.system.po.*;
import com.system.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingMapper trainingMapper;

    @Autowired
    private TrainingMapperMod trainingMapperCustom;

    @Autowired
    private SelectedcourseMapper selectedcourseMapper;

    @Override
    public int countByExample(TrainingExample example) {
        return trainingMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TrainingExample example) {
        return trainingMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer teaid) {
        return 0;
    }

    @Override
    public int insert(Training record) {
        return trainingMapper.insert(record);
    }

    @Override
    public int insertSelective(Training record) {
        return trainingMapper.insertSelective(record);
    }

    @Override
    public List<Training> selectByExample(TrainingExample example) {
        return trainingMapper.selectByExample(example);
    }

    @Override
    public Training selectByPrimaryKey(Integer teaid) {
        return trainingMapper.selectByPrimaryKey(teaid);
    }

    @Override
    public int updateByExampleSelective(Training record, TrainingExample example) {
        return trainingMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Training record, TrainingExample example) {
        return trainingMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Training record) {
        return trainingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Training record) {
        return trainingMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Training> findAllById(Integer userid) {

        SelectedcourseExample example = new SelectedcourseExample();
        SelectedcourseExample.Criteria criteria = example.createCriteria();
        criteria.andStudentidEqualTo(userid);
        List<Selectedcourse> list = selectedcourseMapper.selectByExample(example);
        List<Integer> courseidList = new ArrayList<Integer>();
        for(int i=0;i<list.size();i++){
            courseidList.add(list.get(i).getCourseid());
        }

        TrainingExample example1 = new TrainingExample();
        TrainingExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andCourseidIn(courseidList);

        return trainingMapper.selectByExample(example1);
    }

    @Override
    public int getTrainingCount() {
        TrainingExample example = new TrainingExample();
        TrainingExample.Criteria criteria = example.createCriteria();
        criteria.andTrainidIsNotNull();

        return trainingMapper.countByExample(example);
    }

    @Override
    public List<TrainingCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<TrainingCustom> list = trainingMapperCustom.findByPaging(pagingVO);

        return list;
    }
}
