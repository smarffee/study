package com.lin.docker.controller;

import com.lin.docker.model.User;
import com.lin.docker.model.UserAddress;
import com.lin.docker.service.UserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("userAddress")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @RequestMapping("getById")
    public UserAddress getById(@RequestParam("addressId") int addressId) {

        User user = new User();
        user.setId(addressId);
        user.setName(String.format("姓名：张三； 地址id： %d", addressId));


        return userAddressService.queryByUser(user);
    }

}
