package com.system.service.impl;

import com.system.mapper.FeeMapper;
import com.system.mapper.FeeMapperMod;
import com.system.po.Fee;
import com.system.po.FeeExample;
import com.system.po.PagingVO;
import com.system.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeServiceImpl implements FeeService {
    @Autowired
    private FeeMapper feeMapper;

    @Autowired
    private FeeMapperMod feeMapperCustom;

    @Override
    public Boolean save(Fee fee) throws Exception {
        Fee temp = feeMapper.selectByPrimaryKey(fee.getFid());
        if(temp == null){
            feeMapper.insert(fee);
            return true;
        }
        return false;
    }

    @Override
    public void updatedById(Integer id, Fee fee) throws Exception {
        feeMapper.updateByPrimaryKey(fee);
    }

    @Override
    public Boolean removeById(Integer id) throws Exception {
        feeMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public Boolean accept(Integer id) throws Exception {
        Fee fee = feeMapper.selectByPrimaryKey(id);
        if(fee.getFstatus() == 0){
            fee.setFstatus(1);
            feeMapper.updateByPrimaryKey(fee);
            return true;
        }
        return false;
    }


    @Override
    public Fee findById(Integer id) throws Exception {
        Fee fee = feeMapper.selectByPrimaryKey(id);
        if(fee != null){
            return fee;
        }
        return null;
    }

    @Override
    public int getFeeCount() throws Exception {
        FeeExample example =  new FeeExample();
        FeeExample.Criteria criteria = example.createCriteria();
        criteria.andFidIsNotNull();

        return feeMapper.countByExample(example);
    }

    @Override
    public List<Fee> findAll() throws Exception {
        FeeExample example = new FeeExample();
        return feeMapper.selectByExample(example);
    }

    @Override
    public int getFeeCountByName(String name) throws Exception {
        FeeExample example = new FeeExample();
        FeeExample.Criteria criteria = example.createCriteria();
        criteria.andFpayerusernameEqualTo(name);

        return feeMapper.countByExample(example);
    }

    @Override
    public List<Fee> findByName(String name) throws Exception {
        FeeExample example =  new FeeExample();
        FeeExample.Criteria criteria = example.createCriteria();
        criteria.andFpayerusernameEqualTo(name);

        return feeMapper.selectByExample(example);
    }

    @Override
    public List<Fee> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<Fee> list = feeMapperCustom.findByPaging(pagingVO);
        return list;
    }

}
