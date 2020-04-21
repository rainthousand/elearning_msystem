package com.system.service;

import com.system.po.Fee;

import java.util.List;


public interface FeeService {
    //插入收费信息
    Boolean save(Fee fee) throws Exception;

    //修改收费信息
    void updatedById(Integer id, Fee fee) throws Exception;

    //删除收费信息
    Boolean removeById(Integer id) throws Exception;

    //确认已收费
    Boolean accept(Integer id) throws Exception;

    //显示所有收费信息
    List<Fee> findByPaging(Integer toPageNo) throws Exception;

    //根据fid查询
    Fee findById(Integer id) throws  Exception;

    //获取总数
    int getFeeCount() throws Exception;

    //查询所有收费记录
    List<Fee> findAll() throws Exception;

    //获取指定学生的总数
    int getFeeCountByName(String name) throws Exception;

    //获取指定学生的列表
    List<Fee> findByName(String name) throws Exception;
}
