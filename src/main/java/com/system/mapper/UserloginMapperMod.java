package com.system.mapper;

import com.system.po.UserloginCustom;
public interface UserloginMapperMod {
    UserloginCustom findOneByName(String name) throws Exception;
}
