package com.system.entity;

//extended class for Userlogin
public class UserloginCustom extends Userlogin {

    // add a role observation attribute
    private Role role_ob;

    public void setRole(Role role) {
        this.role_ob = role_ob;
    }

    public Role getRole_ob() {
        return role_ob;
    }

}
