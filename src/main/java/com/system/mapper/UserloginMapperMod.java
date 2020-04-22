package com.system.mapper;

import com.system.entity.UserloginCustom;
public interface UserloginMapperMod {
    UserloginCustom findOneByName(String name) throws Exception;
}
