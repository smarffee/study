package com.lin.login.service;

import com.lin.login.thrift.LoginService;
import com.lin.login.thrift.Request;
import com.lin.login.thrift.RequestException;
import org.apache.thrift.TException;

public class LoginServiceImpl implements LoginService.Iface {

    @Override
    public String doLogin(Request request) throws RequestException, TException {
        System.out.println("hahaha");
        System.out.println("username:"+request.getUsername());
        System.out.println("password:"+request.getPassword());
        return request.getUsername()+request.getPassword();
    }
}
